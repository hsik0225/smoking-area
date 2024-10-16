package com.hsik.smoking.domain.client

import com.fasterxml.jackson.annotation.JsonAlias

class OpenDataPortalResources {
    class SmokingArea {
        data class DongdaemunGu(
            @JsonAlias("연번")
            val id: Long,
            @JsonAlias("시설형태")
            val type: String,
            @JsonAlias("설치위치")
            val address: String,
            @JsonAlias("규모")
            val size: String,
            @JsonAlias("설치년도")
            val buildAt: String,
            @JsonAlias("설치주체")
            val builtBy: String,
            @JsonAlias("운영관리")
            val manager: String,
            @JsonAlias("데이터기준일자")
            val metadataUpdateDateTime: String,
        )
    }
}
