package com.hsik.smoking.domain.area.api

import com.hsik.smoking.config.FlowTest
import com.hsik.smoking.domain.area.SmokingArea
import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import io.kotest.inspectors.shouldForAll
import io.kotest.inspectors.shouldForExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired

class SmokingAreaControllerTest : FlowTest() {
    @Autowired
    private lateinit var smokingAreaRepository: SmokingAreaRepository

    @DisplayName("흡연 구역 생성/조회 테스트")
    @Test
    fun smokingAreaCrTest() {
        // Given
        // 흡연 구역 생성
        val smokingAreaControllerFlow = SmokingAreaControllerFlow(mockMvc)
        val name = SmokingArea.TownName.DONGDAEMUN_GU
        val address1 = "서울특별시 동대문구 무학로 테스트길 1-1"
        val id1: String =
            assertDoesNotThrow { smokingAreaControllerFlow.add(name, address1) }

        val address2 = "서울특별시 동대문구 무학로 테스트길 2323-111"
        val id2: String =
            assertDoesNotThrow { smokingAreaControllerFlow.add(name, address2) }

        // 단일 조회
        val area = smokingAreaControllerFlow.findOne(id1)
        area.id shouldBe id1
        area.address shouldBe address1

        // 전체 조회
        val allOfSmokingAreas = smokingAreaControllerFlow.search()
        allOfSmokingAreas.shouldForExactly(2) { it.id == id1 || it.id == id2 }

        // 이름으로 전체 조회
        val smokingAreas = smokingAreaControllerFlow.search(name)
        allOfSmokingAreas.shouldForExactly(2) { it.id == id1 || it.id == id2 }
        smokingAreas.shouldForAll { it.townName shouldBe name }
    }

    @DisplayName("흡연 구역 싱크 테스트: 0개 -> 2개")
    @Test
    fun createSmokingAreaTest() {
        // Given
        // 기존 생성한 흡연 구역 모두 삭제
        smokingAreaRepository.deleteAll()

        // When
        SmokingAreaControllerFlow(mockMvc).sync(SmokingArea.TownName.DONGDAEMUN_GU)

        // Then
        smokingAreaRepository.findAll().shouldHaveSize(2)
    }
}
