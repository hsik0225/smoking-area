package com.hsik.smoking.domain.area.converter

import com.hsik.smoking.domain.area.SmokingArea
import com.hsik.smoking.domain.client.government.OpenDataPortalResources

class DongdaemunGuSmokingAreaConverter(
    private val smokingAreas: List<OpenDataPortalResources.SmokingArea.DongdaemunGu>,
) : ResourceConverter {
    override fun convert(): List<SmokingArea> =
        smokingAreas.map {
            SmokingArea(
                name = SmokingArea.TownName.DONGDAEMUN_GU,
                referenceId = it.id,
                address = it.address,
                manager = it.manager,
                metadataUpdateDateTime = it.metadataUpdateDateTime,
            )
        }
}
