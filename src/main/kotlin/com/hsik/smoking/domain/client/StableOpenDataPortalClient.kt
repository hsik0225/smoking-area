package com.hsik.smoking.domain.client

import com.hsik.smoking.config.AppEnvironment
import com.hsik.smoking.util.RestClient
import com.hsik.smoking.util.fromJson

class StableOpenDataPortalClient(
    private val env: AppEnvironment.OpenDataPortal,
    private val restClient: RestClient,
) : OpenDataPortalClient {
    override fun findAllSmokingAreaInDongdaemunGu(): List<OpenDataPortalResources.SmokingArea.DongdaemunGu> {
        // TODO 실제 URI 작성 및 호출에 사용할 API 인증키 설정 필요
        val uri = "${env.host}/test"

        return restClient
            .get(uri)
            .fromJson<List<OpenDataPortalResources.SmokingArea.DongdaemunGu>>()
    }
}
