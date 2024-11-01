package com.hsik.smoking.domain.client.government

import com.hsik.smoking.common.OpenDataPortalReply
import com.hsik.smoking.config.AppEnvironment
import com.hsik.smoking.util.RestClient
import com.hsik.smoking.util.fromJson

class StableOpenDataPortalClient(
    private val env: AppEnvironment.OpenDataPortal,
    private val restClient: RestClient,
) : OpenDataPortalClient {
    override fun findAllSmokingAreaInDongdaemunGu(): List<OpenDataPortalResources.SmokingArea.DongdaemunGu> {
        val params =
            listOf(
                // Authorization 헤더에 인증키를 담아 보낼 경우 오픈 데이터 포털에서 인식하지 못하므로 Query로 전달
                "serviceKey" to env.apiKey,
                // 동대문구 흡연 시설 정보는 110개이며, perPage 기본값은 10
                // 전체 110개를 받을 수 있도록 perPage를 1000으로 설정 후 전송
                "perPage" to 1000,
            )
        val uri = "${env.host}/api/15070168/v1/uddi:04b20c76-3628-4095-93bb-8e9523a1241a"
        return restClient
            .get(uri, null, params)
            .fromJson<OpenDataPortalReply<OpenDataPortalResources.SmokingArea.DongdaemunGu>>()
            .data
    }
}
