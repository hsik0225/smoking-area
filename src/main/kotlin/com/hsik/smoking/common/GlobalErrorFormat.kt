package com.hsik.smoking.common

import java.util.Date

data class GlobalErrorFormat(
    val timestamp: Date,
    val status: Int,
    val error: String,
    val path: String,
    val exception: String,
    val message: String,
    val trace: String,
    val additionalInformation: Map<String, Any>? = null,
)
