package com.hsik.smoking

import com.hsik.smoking.config.AppEnvironment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(AppEnvironment::class)
@SpringBootApplication
class SmokingApplication

fun main(args: Array<String>) {
    runApplication<SmokingApplication>(*args)
}
