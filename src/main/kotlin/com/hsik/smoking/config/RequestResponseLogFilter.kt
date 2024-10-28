package com.hsik.smoking.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.web.filter.OncePerRequestFilter

class RequestResponseLogFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val startTime = System.currentTimeMillis()
        if (shouldLog(request)) {
            try {
                beforeRequest(request)
                filterChain.doFilter(request, response)
            } finally {
                afterRequest(request, response, startTime)
            }
        } else {
            filterChain.doFilter(request, response)
        }
    }

    private fun shouldLog(request: HttpServletRequest): Boolean {
        val uri = request.requestURI

        return !(
            uri == "/" ||
                uri == "/style.css" ||
                uri == "/map.js" ||
                uri == "/api.js" ||
                uri == "/config.js" ||
                uri == "/favicon.ico"
        )
    }

    private fun beforeRequest(request: HttpServletRequest) {
        val message = StringBuilder()
        message.append("[REQUEST] ${request.method} ${getUrl(request)} \n")

        val headers = ServletServerHttpRequest(request).headers
        for (header in headers) {
            message.append("${header.key} : ${header.value} \n")
        }

        log.info(message.toString())
    }

    private fun afterRequest(
        request: HttpServletRequest,
        response: HttpServletResponse,
        startTime: Long,
    ) {
        val message = StringBuilder()
        message
            .append("[RESPONSE] ${request.method} ${getUrl(request)} ${response.status} ${HttpStatus.valueOf(response.status).name} \n")
            .append("Duration : ${System.currentTimeMillis() - startTime}ms \n")

        val headerNames = response.headerNames
        for (headerName in headerNames) {
            message.append("$headerName : ${response.getHeader(headerName)} \n")
        }

        log.info(message.toString())
    }

    private fun getUrl(request: HttpServletRequest): String {
        val query = if (request.queryString.isNullOrBlank()) "" else "?${request.queryString}"
        return "${request.requestURL}$query"
    }
}
