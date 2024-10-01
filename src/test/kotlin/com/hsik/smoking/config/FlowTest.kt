package com.hsik.smoking.config

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@Import(value = [MongoTestContainerConfiguration::class])
@ExtendWith(SpringExtension::class)
@SpringBootTest
abstract class FlowTest
