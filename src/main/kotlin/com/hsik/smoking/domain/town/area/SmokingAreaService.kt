package com.hsik.smoking.domain.town.area

import com.hsik.smoking.domain.town.area.repository.SmokingAreaRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class SmokingAreaService(
    private val smokingAreaRepository: SmokingAreaRepository,
) {
    fun add(address: String): ObjectId {
        val area = SmokingArea(address = address)
        return smokingAreaRepository.save(area).id
    }
}
