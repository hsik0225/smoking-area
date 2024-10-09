package com.hsik.smoking.domain.area.api

import com.hsik.smoking.config.FlowTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class AreaControllerTest : FlowTest() {
    @DisplayName("흡연소 생성/조회 테스트")
    @Test
    fun areaCrTest() {
        val areaControllerFlow = AreaControllerFlow(mockMvc)

        // 생성
        val address = "서울특별시 동대문구 무학로 테스트길 1-1"
        val id: String = assertDoesNotThrow { areaControllerFlow.add(address) }

        // 단일 조회
        val area = areaControllerFlow.findOne(id)
        area.id shouldBe id
        area.address shouldBe address
    }
}
