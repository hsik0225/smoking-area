package com.hsik.smoking.domain.area.synchronizer

import com.hsik.smoking.domain.area.SmokingAreaTemplate
import com.hsik.smoking.domain.area.converter.DongdaemunGuSmokingAreaConverter
import com.hsik.smoking.domain.client.OpenDataPortalClient

class DongdaemunGuSynchronizer(
    private val openDataPortalClient: OpenDataPortalClient,
    private val smokingAreaTemplate: SmokingAreaTemplate,
) : Synchronizer {
    override fun synchronize() {
        val resource = openDataPortalClient.findAllSmokingAreaInDongdaemunGu()
        val smokingAreas = DongdaemunGuSmokingAreaConverter(resource).convert()
        smokingAreaTemplate.upsertAll(smokingAreas)
    }
}
