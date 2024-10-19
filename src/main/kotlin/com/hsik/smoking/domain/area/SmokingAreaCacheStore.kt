package com.hsik.smoking.domain.area

import com.hsik.smoking.util.fromJson
import com.hsik.smoking.util.toJson
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class SmokingAreaCacheStore(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun hasKey(key: ObjectId): Boolean {
        val smokingAreaRedisKey = SmokingAreaKey().getKeyOfSmokingArea(key)
        try {
            return stringRedisTemplate.hasKey(smokingAreaRedisKey)
        } catch (e: Exception) {
            logger.error("[CACHE] Redis cache read failed: {}", smokingAreaRedisKey, e)
            throw Exception(e)
        }
    }

    fun getValue(key: ObjectId): SmokingArea {
        val smokingAreaRedisKey = SmokingAreaKey().getKeyOfSmokingArea(key)
        try {
            val value = stringRedisTemplate.opsForValue().get(smokingAreaRedisKey)
            if (value.isNullOrBlank()) {
                throw IllegalStateException("[CACHE] Redis does not have key : $smokingAreaRedisKey")
            }

            return value.fromJson<SmokingArea>()
        } catch (e: Exception) {
            logger.error("[CACHE] Redis cache read failed: {}", smokingAreaRedisKey, e)
            throw Exception(e)
        }
    }

    fun set(
        key: ObjectId,
        smokingArea: SmokingArea,
    ) {
        val smokingAreaRedisKey = SmokingAreaKey().getKeyOfSmokingArea(key)
        try {
            val result = stringRedisTemplate.opsForValue().setIfAbsent(smokingAreaRedisKey, smokingArea.toJson())
            logger.debug("[CACHE] Redis cache set key:{}, result: {}", smokingAreaRedisKey, result)
        } catch (e: Exception) {
            logger.error("[CACHE] Redis cache set failed: {}", smokingAreaRedisKey, e)
            throw Exception(e)
        }
    }
}
