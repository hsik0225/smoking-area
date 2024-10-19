package com.hsik.smoking.config

import com.hsik.smoking.SmokingApplication
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
    classes = [SmokingApplication::class, MockMvcCustomizer::class, TestRedisConfiguration::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
abstract class FlowTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    companion object {
        private val MONGO_CONTAINER: MongoDBContainer = MongoDBContainer("mongo:latest")

        init {
            MONGO_CONTAINER.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun mongoDbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { MONGO_CONTAINER.replicaSetUrl }
        }
    }
}
