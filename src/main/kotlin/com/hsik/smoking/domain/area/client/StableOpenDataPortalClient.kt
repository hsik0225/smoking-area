package com.hsik.smoking.domain.area.client

import com.hsik.smoking.config.AppEnvironment
import com.hsik.smoking.util.RestClient
import com.hsik.smoking.util.fromJson

class StableOpenDataPortalClient(
    private val env: AppEnvironment.OpenDataPortal,
    private val restClient: RestClient,
) : OpenDataPortalClient {
    override fun findAllSmokingAreaByDongDaemungu(): List<OpenDataPortalResources.SmokingArea.DongDaeMunGu> {
        val uri = "${env.host}/test"

        return restClient
            .get(uri)
            .fromJson<List<OpenDataPortalResources.SmokingArea.DongDaeMunGu>>()
    }
}
