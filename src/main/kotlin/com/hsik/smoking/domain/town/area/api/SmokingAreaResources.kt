package com.hsik.smoking.domain.town.area.api

import com.hsik.smoking.domain.town.area.SmokingArea
import java.time.LocalDateTime

class SmokingAreaResources {
    class Request {
        data class Me(
            val address: String,
        )
    }

    class Response {
        data class Me(
            val id: String,
            val createdAt: LocalDateTime,
            val modifiedAt: LocalDateTime,
            val status: SmokingArea.Status,
            val address: String,
            val metaUpdateDateTime: String? = null,
            val latitude: Double? = null,
            val longitude: Double? = null,
            val manager: String? = null,
            val description: String? = null,
            val cause: String? = null,
        ) {
            companion object {
                fun from(area: SmokingArea): Me =
                    area.run {
                        Me(
                            id = id.toString(),
                            createdAt = createdAt,
                            modifiedAt = modifiedAt,
                            status = status,
                            address = address,
                            metaUpdateDateTime = metadataUpdateDateTime,
                            latitude = latitude,
                            longitude = longitude,
                            manager = manager,
                            description = description,
                            cause = cause,
                        )
                    }
            }
        }
    }
}
