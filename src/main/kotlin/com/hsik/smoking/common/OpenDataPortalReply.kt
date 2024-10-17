package com.hsik.smoking.common

open class OpenDataPortalReply<T : Any>(
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
    val currentCount: Int,
    val matchCount: Int,
    val data: List<T>,
)
