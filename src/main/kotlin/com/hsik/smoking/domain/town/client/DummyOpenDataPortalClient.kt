package com.hsik.smoking.domain.town.client

import kotlin.random.Random

class DummyOpenDataPortalClient : OpenDataPortalClient {
    override fun findAllSmokingAreaByDongDaemungu(): List<OpenDataPortalResources.SmokingArea.DongDaeMunGu> =
        listOf(
            OpenDataPortalResources.SmokingArea.DongDaeMunGu(
                id = Random.nextLong(),
                type = "완전폐쇄형",
                address = "무학로 테스트길 36로 옥상",
                size = "5m^2",
                buildAt = "2016",
                builtBy = "테스트 카페",
                manager = "테스트 카페",
                metadataUpdateDateTime = "2021-10-27",
            ),
            OpenDataPortalResources.SmokingArea.DongDaeMunGu(
                id = Random.nextLong(),
                type = "개방형",
                address = "무학로 테스트길 26로 1층",
                size = "10m^2",
                buildAt = "2018",
                builtBy = "테스트 아파트",
                manager = "테스트 아파트",
                metadataUpdateDateTime = "2022-01-27",
            ),
        )
}
