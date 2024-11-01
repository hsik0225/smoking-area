package com.hsik.smoking.common

import com.fasterxml.jackson.annotation.JsonAlias

open class KakaoReply<T : Any>(
    val meta: Meta,
    val documents: List<T>,
) {
    data class Meta(
        @JsonAlias("total_count")
        val totalCount: Int,
        @JsonAlias("pageable_count")
        val pageableCount: Int,
        @JsonAlias("is_end")
        val isEnd: Boolean,
    )
}
