package com.hsik.smoking.domain.area.api

import com.hsik.smoking.domain.area.SmokingArea
import java.time.LocalDateTime

class SmokingAreaResources {
    class Request {
        data class Me(
            val name: SmokingArea.TownName,
            val address: String,
        )
    }

    class Response {
        data class Me(
            val id: String,
            val createdAt: LocalDateTime,
            val modifiedAt: LocalDateTime,
            val status: SmokingArea.Status,
            val townName: SmokingArea.TownName,
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
                            townName = name,
                            address = address,
                            metaUpdateDateTime = metadataUpdateDateTime,
                            latitude = latitude,
                            longitude = longitude,
                            manager = manager,
                            description = description,
                            cause = cause,
                        )
                    }

                fun from(areas: List<SmokingArea>): List<Me> =
                    areas.map {
                        from(it)
                    }
            }
        }
    }
}
