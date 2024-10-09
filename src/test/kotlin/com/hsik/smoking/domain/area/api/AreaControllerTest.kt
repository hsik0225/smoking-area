package com.hsik.smoking.domain.area.api

import com.hsik.smoking.config.FlowTest
import com.hsik.smoking.domain.town.api.TownControllerFlow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class AreaControllerTest : FlowTest() {
    @DisplayName("흡연 구역 생성/조회 테스트")
    @Test
    fun areaCrTest() {
        // Given
        // 도시 생성
        val townControllerFlow = TownControllerFlow(mockMvc)
        val townId = townControllerFlow.add("테스트구")

        // 흡연 구역 생성
        val areaControllerFlow = AreaControllerFlow(mockMvc)
        val address = "서울특별시 동대문구 무학로 테스트길 1-1"
        val id: String = assertDoesNotThrow { areaControllerFlow.add(townId, address) }

        // 단일 조회
        val area = areaControllerFlow.findOne(townId, id)
        area.id shouldBe id
        area.address shouldBe address
    }
}
