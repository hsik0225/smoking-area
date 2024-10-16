package com.hsik.smoking.domain.town.api

import com.hsik.smoking.config.FlowTest
import com.hsik.smoking.domain.town.Town
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class TownControllerTest : FlowTest() {
    @DisplayName("타운(구) 생성/조회 테스트")
    @Test
    fun areaCrTest() {
        val townControllerFlow = TownControllerFlow(mockMvc)

        // 생성
        val name = Town.TownName.DONGDAEMUN_GU
        val id: String = assertDoesNotThrow { townControllerFlow.add(name) }

        // 단일 조회
        val town = townControllerFlow.findOne(id)
        town.id shouldBe id
        town.name shouldBe name
    }
}
