package com.hsik.smoking.client

import com.hsik.smoking.config.FlowTest
import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MongoDbClientTest : FlowTest() {
    @Autowired
    private lateinit var smokingAreaRepository: SmokingAreaRepository

    @DisplayName("MongoDB 커넥션 테스트")
    @Test
    fun connect() {
        smokingAreaRepository.findAll()
    }
}
