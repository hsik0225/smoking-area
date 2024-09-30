package com.hsik.boilerplate.common

open class Reply<T>(
    private val content: T,
)

fun <T> T.toReply(): Reply<T> = Reply(this)
