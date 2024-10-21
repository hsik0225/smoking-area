package com.hsik.smoking.util

import com.hsik.smoking.config.AppEnvironment
import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.core5.http.io.HttpClientResponseHandler
import java.util.concurrent.TimeUnit

class RestClient(
    private val httpClient: CloseableHttpClient,
    private val httpClientResponseHandler: HttpClientResponseHandler<String>,
) {
    fun get(
        url: String,
        headers: List<Pair<String, String>>? = null,
        params: List<Pair<String, Any>>? = null,
    ): String {
        val query = params?.joinToString("&") { "${it.first}=${it.second}" }
        val uri = if (query.isNullOrBlank()) url else "$url?$query"
        val get =
            HttpGet(uri).apply {
                headers?.forEach { addHeader(it.first, it.second) }
            }

        return httpClient.execute(get, httpClientResponseHandler)
    }

    companion object {
        fun from(options: AppEnvironment.ConnectionOptions): RestClient {
            val manager =
                PoolingHttpClientConnectionManagerBuilder
                    .create()
                    .setDefaultConnectionConfig(
                        ConnectionConfig
                            .custom()
                            .setConnectTimeout(options.connectionTimeout, TimeUnit.SECONDS)
                            .build(),
                    ).build()

            val httpClient =
                HttpClients
                    .custom()
                    .setConnectionManager(manager)
                    .setDefaultRequestConfig(
                        RequestConfig
                            .custom()
                            .setResponseTimeout(options.readTimeout, TimeUnit.SECONDS)
                            .build(),
                    ).build()

            return RestClient(httpClient, RestHttpClientResponseHandler())
        }
    }
}
