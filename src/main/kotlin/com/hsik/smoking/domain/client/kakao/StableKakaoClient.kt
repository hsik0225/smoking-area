package com.hsik.smoking.domain.client.kakao

import com.hsik.smoking.common.KakaoReply
import com.hsik.smoking.config.AppEnvironment
import com.hsik.smoking.util.RestClient
import com.hsik.smoking.util.fromJson
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.net.URLEncoder

class StableKakaoClient(
    private val env: AppEnvironment.Kakao,
    private val restClient: RestClient,
) : KakaoClient {
    override fun geocoding(address: String): KakaoResources.Local.Geocoding? {
        val headers =
            listOf(
                HttpHeaders.AUTHORIZATION to "KakaoAK ${env.apiKey}",
                HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON_VALUE,
            )
        val uri = "${env.host}/v2/local/search/address?query=${URLEncoder.encode(address, Charsets.UTF_8)}"
        return restClient
            .get(uri, headers)
            .fromJson<KakaoReply<KakaoResources.Local.Geocoding>>()
            .documents
            .firstOrNull()
    }
}
