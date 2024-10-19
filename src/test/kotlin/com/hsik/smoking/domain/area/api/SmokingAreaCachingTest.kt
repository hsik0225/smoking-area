package com.hsik.smoking.domain.area.api

import com.hsik.smoking.config.FlowTest
import com.hsik.smoking.domain.area.SmokingArea
import com.hsik.smoking.domain.area.SmokingAreaCacheStore
import com.hsik.smoking.domain.area.SmokingAreaKey
import com.hsik.smoking.domain.area.TownCacheStore
import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate

class SmokingAreaCachingTest : FlowTest() {
    @Autowired
    private lateinit var stringRedisTemplate: StringRedisTemplate

    @SpykBean
    private lateinit var smokingAreaCacheStore: SmokingAreaCacheStore

    @SpykBean
    private lateinit var townCacheStore: TownCacheStore

    @Nested
    inner class TownCachingTest {
        @DisplayName("전체 흡연 구역 조회 시 캐싱된 데이터가 존재하지 않을 경우 모두 Cache Miss 및 Cache Save")
        @Test
        fun allCacheMissAndSave() {
            // Given
            // 기존 캐싱 데이터 삭제
            val keys = stringRedisTemplate.keys("*")
            keys.forEach { stringRedisTemplate.delete(it) }

            // 흡연 구역 생성
            val smokingAreaControllerFlow = SmokingAreaControllerFlow(mockMvc)
            smokingAreaControllerFlow.sync(SmokingArea.TownName.DONGDAEMUN_GU)

            // When
            val smokingAreaCount = smokingAreaControllerFlow.search().count()

            // Then
            verify(exactly = smokingAreaCount) { smokingAreaCacheStore.set(any(), any()) }
        }

        @DisplayName("전체 흡연 구역 조회 시 SmokingArea가 모두 캐싱되었을 경우 모두 Cache Hit ")
        @Test
        fun allCacheHitBySmokingArea() {
            // Given
            // 기존 캐싱 데이터 삭제
            val keys = stringRedisTemplate.keys("*")
            keys.forEach { stringRedisTemplate.delete(it) }

            // 흡연 구역 생성
            val smokingAreaControllerFlow = SmokingAreaControllerFlow(mockMvc)
            smokingAreaControllerFlow.sync(SmokingArea.TownName.DONGDAEMUN_GU)

            // 데이터 캐싱
            smokingAreaControllerFlow.search()

            // When
            val smokingAreaCount = smokingAreaControllerFlow.search().count()

            // Then
            verify(exactly = smokingAreaCount) { smokingAreaCacheStore.getValue(any()) }
        }

        @DisplayName("특정 구역의 흡연 구역 조회 시 Town이 캐싱되었을 경우 모두 Cache Hit ")
        @Test
        fun allCacheHitByTown() {
            // Given
            // 기존 캐싱 데이터 삭제
            val keys = stringRedisTemplate.keys("*")
            keys.forEach { stringRedisTemplate.delete(it) }

            // 흡연 구역 생성
            val smokingAreaControllerFlow = SmokingAreaControllerFlow(mockMvc)
            val townName = SmokingArea.TownName.DONGDAEMUN_GU
            smokingAreaControllerFlow.sync(townName)

            // 데이터 캐싱
            smokingAreaControllerFlow.search(townName)

            // When
            smokingAreaControllerFlow.search(townName).count()

            // Then
            verify(exactly = 1) { townCacheStore.getValue(any()) }
        }
    }

    @Nested
    inner class SmokingAreaCachingTest {
        @DisplayName("흡연 구역 조회 시 캐싱된 데이터가 존재하지 않을 경우 모두 Cache Miss 및 Cache Save")
        @Test
        fun allCacheMissAndSave() {
            // Given
            // 기존 캐싱 데이터 삭제
            val keys = stringRedisTemplate.keys("*")
            keys.forEach { stringRedisTemplate.delete(it) }

            // 흡연 구역 생성
            val townName = SmokingArea.TownName.DONGDAEMUN_GU
            val smokingAreaControllerFlow = SmokingAreaControllerFlow(mockMvc)
            smokingAreaControllerFlow.sync(townName)

            // When
            val smokingAreaCount = smokingAreaControllerFlow.search(townName).count()

            // Then
            verify(exactly = smokingAreaCount) { smokingAreaCacheStore.set(any(), any()) }
        }

        @DisplayName("흡연 구역 조회 시 SmokingArea가 모두 캐싱되었을 경우 모두 Cache Hit ")
        @Test
        fun allCacheHitBySmokingArea() {
            // Given
            // 기존 캐싱 데이터 삭제
            val keys = stringRedisTemplate.keys("*")
            keys.forEach { stringRedisTemplate.delete(it) }

            // 흡연 구역 생성
            val townName = SmokingArea.TownName.DONGDAEMUN_GU
            val smokingAreaControllerFlow = SmokingAreaControllerFlow(mockMvc)
            smokingAreaControllerFlow.sync(townName)

            // 데이터 캐싱
            smokingAreaControllerFlow.search(townName)

            // 타운 캐시 데이터 삭제
            val townKeys = stringRedisTemplate.keys(SmokingAreaKey().getKeyOfTown(townName))
            townKeys.forEach { stringRedisTemplate.delete(it) }

            // When
            val smokingAreaCount = smokingAreaControllerFlow.search(townName).count()

            // Then
            verify(exactly = smokingAreaCount) { smokingAreaCacheStore.getValue(any()) }
        }
    }
}
