package com.hsik.smoking.util

import com.hsik.smoking.common.CommunicationException
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler
import org.apache.hc.core5.http.ClassicHttpResponse
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.springframework.http.HttpStatus
import java.nio.charset.Charset

class RestHttpClientResponseHandler : BasicHttpClientResponseHandler() {
    override fun handleResponse(response: ClassicHttpResponse?): String {
        if (response == null) {
            return ""
        }

        if (HttpStatus.valueOf(response.code).is2xxSuccessful) {
            return super.handleResponse(response)
        }

        val entity = EntityUtils.toString(response.entity, Charset.defaultCharset())

        // 4xx, 5xx 에러 발생
        throw CommunicationException(
            message = entity,
            additionalInformation =
                mapOf(
                    "external service http status" to response.code,
                ),
        )
    }
}
