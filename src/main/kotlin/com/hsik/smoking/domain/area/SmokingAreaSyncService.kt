package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.area.synchronizer.DongdaemunGuSynchronizer
import com.hsik.smoking.domain.client.OpenDataPortalClient
import org.springframework.stereotype.Service

@Service
class SmokingAreaSyncService(
    private val openDataPortalClient: OpenDataPortalClient,
    private val smokingAreaTemplate: SmokingAreaTemplate,
) {
    fun sync(name: SmokingArea.TownName) {
        val synchronizer =
            when (name) {
                SmokingArea.TownName.DONGDAEMUN_GU -> DongdaemunGuSynchronizer(openDataPortalClient, smokingAreaTemplate)
            }
        synchronizer.synchronize()
    }
}
