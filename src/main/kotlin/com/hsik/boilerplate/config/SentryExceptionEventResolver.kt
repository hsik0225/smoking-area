package com.hsik.boilerplate.config

import io.sentry.Hint
import io.sentry.IScopes
import io.sentry.ScopeCallback
import io.sentry.SentryEvent
import io.sentry.SentryLevel
import io.sentry.TypeCheckHint
import io.sentry.exception.ExceptionMechanismException
import io.sentry.protocol.Mechanism
import io.sentry.spring.boot.jakarta.SentryProperties
import io.sentry.spring.jakarta.SentryExceptionResolver
import io.sentry.spring.jakarta.tracing.TransactionNameProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.ModelAndView
import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties

class SentryExceptionEventResolver(
    private val sentryScopes: IScopes,
    private val transactionNameProvider: TransactionNameProvider,
    options: SentryProperties,
) : SentryExceptionResolver(
        sentryScopes,
        transactionNameProvider,
        options.exceptionResolverOrder,
    ) {
    private val log = LoggerFactory.getLogger(SentryExceptionEventResolver::class.java)

    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception,
    ): ModelAndView? {
        val mechanism = Mechanism()
        mechanism.isHandled = false
        mechanism.type = MECHANISM_TYPE
        val throwable: Throwable = ExceptionMechanismException(mechanism, ex, Thread.currentThread())
        val event = SentryEvent(throwable)
        event.level = SentryLevel.FATAL
        event.transaction = transactionNameProvider.provideTransactionName(request)

        val hint = Hint()
        hint[TypeCheckHint.SPRING_RESOLVER_REQUEST] = request
        hint[TypeCheckHint.SPRING_RESOLVER_RESPONSE] = response

        appendErrorCodeToTag(event, ex)

        val scopeHandler =
            ScopeCallback { scope ->
                val headers = request.headerNames
                if (scope.request != null) {
                    while (headers.hasMoreElements()) {
                        val name = headers.nextElement()
                        val scopeHeader = scope.request!!.headers ?: mutableMapOf()
                        scopeHeader[name] = request.getHeader(name)
                        scope.request!!.headers = scopeHeader
                    }
                }
            }

        sentryScopes.captureEvent(event, hint, scopeHandler)

        // null = run other HandlerExceptionResolvers to actually handle the exception
        return null
    }

    private fun appendErrorCodeToTag(
        event: SentryEvent,
        throwable: Throwable?,
    ) {
        // captured throwable이 없으면 exception에 대한 처리가 불가능 하므로 검사하지 않음
        if (throwable == null) return

        // exception으로부터 errorCode를 추출하여 tag로 설정, 에러 발생시 무시함
        try {
            getErrorSource(throwable)?.let {
                val tags = event.tags ?: mutableMapOf()
                it["code"]?.run { tags["errorCode"] = this.toString() }
                event.tags = tags
            }
        } catch (ex: Exception) {
            log.error("failed to enrich event :: errorCode", ex)
        }
    }

    private fun getErrorSource(throwable: Throwable): Map<*, *>? {
        // reflection을 사용하여 throwable의 error를 가져온다
        val errorProperties = throwable::class.memberProperties
        for (property in errorProperties) {
            val field = getFieldInClassHierarchy(throwable.javaClass, property.name)
            return mapOf(field?.type?.simpleName to throwable)
        }
        return null
    }

    private fun getFieldInClassHierarchy(
        clazz: Class<*>?,
        name: String,
    ): Field? {
        // class hierarchy를 탐색하여 field를 가져온다
        var mutableClazz = clazz
        var field: Field? = null
        while (mutableClazz != null && field == null) {
            try {
                field = mutableClazz.getDeclaredField(name)
            } catch (e: Exception) {
                // do nothing
            }
            mutableClazz = mutableClazz.superclass
        }
        return field
    }
}
