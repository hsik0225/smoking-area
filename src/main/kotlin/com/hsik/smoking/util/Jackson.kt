package com.hsik.smoking.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object Jackson {
    private val mapper =
        ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(JavaTimeModule())
            .registerKotlinModule()

    fun getMapper(): ObjectMapper = mapper
}

fun <T> T.toJson(): String = Jackson.getMapper().writeValueAsString(this)

inline fun <reified T> String.fromJson(): T = Jackson.getMapper().readValue(this)
