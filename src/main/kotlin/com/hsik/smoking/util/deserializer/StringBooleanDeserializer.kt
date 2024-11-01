package com.hsik.smoking.util.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class StringBooleanDeserializer : JsonDeserializer<Boolean>() {
    override fun deserialize(
        p0: JsonParser?,
        p1: DeserializationContext?,
    ): Boolean {
        if (p0 == null) throw IllegalArgumentException("Attempting to change null value to boolean")

        return if (listOf("Y", "y", "O", "o").contains(p0.text)) {
            return true
        } else if (listOf("N", "n", "X", "x").contains(p0.text)) {
            false
        } else {
            throw IllegalArgumentException("${p0.text} is not a string boolean")
        }
    }
}
