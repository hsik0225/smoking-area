package com.hsik.smoking.common

import com.fasterxml.jackson.annotation.JsonUnwrapped

open class Reply<T : Any>() {
    @JsonUnwrapped
    lateinit var content: T

    constructor(content: T) : this() {
        this.content = content
    }
}

fun <T : Any> T.toReply(): Reply<T> = Reply(this)
