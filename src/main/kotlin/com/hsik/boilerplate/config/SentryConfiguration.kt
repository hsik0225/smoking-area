package com.hsik.boilerplate.config

import io.sentry.IScopes
import io.sentry.SentryOptions
import io.sentry.spring.boot.jakarta.SentryProperties
import io.sentry.spring.jakarta.EnableSentry
import io.sentry.spring.jakarta.SentryExceptionResolver
import io.sentry.spring.jakarta.tracing.TransactionNameProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered

@EnableSentry(
    exceptionResolverOrder = Ordered.HIGHEST_PRECEDENCE,
    sendDefaultPii = true,
    maxRequestBodySize = SentryOptions.RequestSize.ALWAYS,
)
@Configuration
class SentryConfiguration {
    @Bean
    fun sentryExceptionResolver(
        sentryScopes: IScopes,
        transactionNameProvider: TransactionNameProvider,
        options: SentryProperties,
    ): SentryExceptionResolver = SentryExceptionEventResolver(sentryScopes, transactionNameProvider, options)
}
