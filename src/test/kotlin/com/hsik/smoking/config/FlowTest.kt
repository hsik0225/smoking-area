package com.hsik.smoking.config

import com.hsik.smoking.SmokingApplication
import com.redis.testcontainers.RedisContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(
    classes = [SmokingApplication::class, MockMvcCustomizer::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
abstract class FlowTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    companion object {
        private val MONGO_CONTAINER: MongoDBContainer = MongoDBContainer("mongo:8.0.3")

        private const val REDIS_PORT = 6379
        private val REDIS_CONTAINER = RedisContainer("redis:7.4.1-alpine")

        init {
            MONGO_CONTAINER.start()
            REDIS_CONTAINER.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun mongoDbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { MONGO_CONTAINER.replicaSetUrl }
        }

        @DynamicPropertySource
        @JvmStatic
        fun redisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.host") { REDIS_CONTAINER.host }
            registry.add("spring.data.redis.port") { REDIS_CONTAINER.getMappedPort(REDIS_PORT).toString() }
        }
    }
}
