package com.hsik.smoking.domain.area

import com.hsik.smoking.util.fromJson
import com.hsik.smoking.util.toJson
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class TownCacheStore(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun hasKey(key: SmokingArea.TownName): Boolean {
        val townRedisKey = SmokingAreaKey().getKeyOfTown(key)
        try {
            return stringRedisTemplate.hasKey(townRedisKey)
        } catch (e: Exception) {
            logger.error("[CACHE] Redis cache read failed: {}", townRedisKey, e)
            throw Exception(e)
        }
    }

    fun getValue(key: SmokingArea.TownName): List<SmokingArea> {
        val townRedisKey = SmokingAreaKey().getKeyOfTown(key)
        try {
            val value = stringRedisTemplate.opsForValue().get(townRedisKey)
            if (value.isNullOrBlank()) {
                throw IllegalStateException("[CACHE] Redis does not have key : $townRedisKey")
            }

            return value.fromJson<List<SmokingArea>>()
        } catch (e: Exception) {
            logger.error("[CACHE] Redis cache read failed: {}", townRedisKey, e)
            throw Exception(e)
        }
    }

    fun set(
        key: SmokingArea.TownName,
        smokingAreasWithCoordinate: List<SmokingArea>,
    ) {
        val townRedisKey = SmokingAreaKey().getKeyOfTown(key)
        try {
            val result = stringRedisTemplate.opsForValue().setIfAbsent(townRedisKey, smokingAreasWithCoordinate.toJson())
            logger.debug("[CACHE] Redis cache set key:{}, result: {}", townRedisKey, result)
        } catch (e: Exception) {
            logger.error("[CACHE] Redis cache set failed: {}", townRedisKey, e)
            throw Exception(e)
        }
    }
}
