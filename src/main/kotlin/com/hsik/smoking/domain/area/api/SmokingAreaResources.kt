package com.hsik.smoking.domain.area.api

import com.hsik.smoking.domain.area.SmokingArea
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
            val metaUpdateDateTime: LocalDateTime? = null,
            val latitude: Double? = null,
            val longitude: Double? = null,
            val manager: String? = null,
            val description: String? = null,
            val cause: String? = null,
        ) {
            companion object {
                fun from(area: SmokingArea): Me =
                    Me(
                        id = area.id.toString(),
                        createdAt = area.createdAt,
                        modifiedAt = area.modifiedAt,
                        status = area.status,
                        address = area.address,
                        metaUpdateDateTime = area.metadataUpdateDateTime,
                        latitude = area.latitude,
                        longitude = area.longitude,
                        manager = area.manager,
                        description = area.description,
                        cause = area.cause,
                    )
            }
        }
    }
}
