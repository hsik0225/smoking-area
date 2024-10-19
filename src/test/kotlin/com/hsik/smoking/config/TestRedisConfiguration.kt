package com.hsik.smoking.config

import com.redis.testcontainers.RedisContainer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@TestConfiguration
class TestRedisConfiguration {
    private val port = REDIS_CONTAINER.getMappedPort(REDIS_PORT)

    @Bean
    fun stringRedisTemplate(): StringRedisTemplate {
        val configuration = RedisStandaloneConfiguration("localhost", port)
        val factory = LettuceConnectionFactory(configuration).apply { afterPropertiesSet() }
        return StringRedisTemplate(factory)
    }

    companion object {
        private const val REDIS_PORT = 6379
        private val REDIS_CONTAINER = RedisContainer("redis:7.4.1-alpine")

        init {
            REDIS_CONTAINER.start()
        }
    }
}
