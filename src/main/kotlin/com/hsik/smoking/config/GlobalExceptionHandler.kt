package com.hsik.smoking.config

import com.hsik.smoking.common.CommunicationException
import com.hsik.smoking.common.GlobalErrorFormat
import com.hsik.smoking.common.HumanException
import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.util.Jackson
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.ServletWebRequest

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(
        e: Exception,
        request: HttpServletRequest,
    ): GlobalErrorFormat = toGlobalErrorFormat(e, request, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(
        e: RuntimeException,
        request: HttpServletRequest,
    ): GlobalErrorFormat = toGlobalErrorFormat(e, request, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(CommunicationException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCommunicationException(
        e: CommunicationException,
        request: HttpServletRequest,
    ): GlobalErrorFormat = toGlobalErrorFormat(e, request, HttpStatus.INTERNAL_SERVER_ERROR, e.additionalInformation)

    @ExceptionHandler(HumanException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHumanException(
        e: HumanException,
        request: HttpServletRequest,
    ): GlobalErrorFormat = toGlobalErrorFormat(e, request, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceNotFoundException(
        e: ResourceNotFoundException,
        request: HttpServletRequest,
    ): GlobalErrorFormat = toGlobalErrorFormat(e, request, HttpStatus.BAD_REQUEST)

    private fun toGlobalErrorFormat(
        exception: Exception,
        request: HttpServletRequest,
        httpStatus: HttpStatus,
        additionalInformation: Map<String, Any>? = null,
    ): GlobalErrorFormat {
        logger.error("Exception occurred", exception)

        val servletWebRequest = ServletWebRequest(request)
        servletWebRequest.setAttribute(
            RequestDispatcher.ERROR_STATUS_CODE,
            httpStatus.value(),
            RequestAttributes.SCOPE_REQUEST,
        )
        val errorAttributeOptions = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.entries)
        val errorAttributes =
            DefaultErrorAttributes().getErrorAttributes(servletWebRequest, errorAttributeOptions)

        errorAttributes["path"] = request.servletPath
        additionalInformation?.let { errorAttributes["additionalInformation"] = additionalInformation }

        return Jackson.getMapper().convertValue(errorAttributes, GlobalErrorFormat::class.java)
    }
}
