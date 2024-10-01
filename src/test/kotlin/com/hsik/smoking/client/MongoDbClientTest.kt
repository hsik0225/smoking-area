package com.hsik.smoking.client

import com.hsik.smoking.config.FlowTest
import com.hsik.smoking.domain.area.repository.AreaRepository
import io.kotest.matchers.collections.shouldBeEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MongoDbClientTest : FlowTest() {

    @Autowired
    private lateinit var areaRepository: AreaRepository

    @DisplayName("MongoDB 커넥션 테스트")
    @Test
    fun connect() {
        areaRepository.findAll().shouldBeEmpty()
    }
}
