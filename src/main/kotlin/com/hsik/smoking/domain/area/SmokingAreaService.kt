package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class SmokingAreaService(
    private val smokingAreaRepository: SmokingAreaRepository,
) {
    fun add(address: String): ObjectId {
        val area = SmokingArea(address)
        return smokingAreaRepository.save(area).id
    }
}
