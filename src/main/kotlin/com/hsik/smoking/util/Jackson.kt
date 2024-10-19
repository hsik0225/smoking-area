package com.hsik.smoking.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.bson.types.ObjectId

object Jackson {
    private val mapper =
        ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModules(JavaTimeModule(), objectIdModule())
            .registerKotlinModule()

    fun getMapper(): ObjectMapper = mapper
}

private fun objectIdModule(): SimpleModule {
    val module = SimpleModule()
    module.addSerializer(ObjectId::class.java, ToStringSerializer())
    return module
}

fun <T> T.toJson(): String = Jackson.getMapper().writeValueAsString(this)

inline fun <reified T> String.fromJson(): T = Jackson.getMapper().readValue(this)
