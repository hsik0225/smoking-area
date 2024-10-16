package com.hsik.smoking.domain.town.api

import com.hsik.smoking.domain.town.Town
import java.time.LocalDateTime

class TownResources {
    class Request {
        data class Me(
            val name: Town.TownName,
        )
    }

    class Response {
        data class Me(
            val id: String,
            val createdAt: LocalDateTime,
            val modifiedAt: LocalDateTime,
            val name: Town.TownName,
            val status: Town.Status,
            val cause: String? = null,
        ) {
            companion object {
                fun from(town: Town): Me =
                    town.run {
                        Me(
                            id = id.toString(),
                            createdAt = createdAt,
                            modifiedAt = modifiedAt,
                            name = name,
                            status = status,
                            cause = cause,
                        )
                    }
            }
        }
    }
}
