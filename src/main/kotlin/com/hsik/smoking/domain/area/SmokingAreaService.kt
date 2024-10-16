package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class SmokingAreaService(
    private val smokingAreaRepository: SmokingAreaRepository,
) {
    fun add(
        name: SmokingArea.TownName,
        address: String,
    ): ObjectId {
        val area =
            SmokingArea(
                name = name,
                address = address,
            )
        return smokingAreaRepository.save(area).id
    }
}
