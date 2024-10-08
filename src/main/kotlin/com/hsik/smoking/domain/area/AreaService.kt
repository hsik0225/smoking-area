package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.area.repository.AreaRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class AreaService(
    private val areaRepository: AreaRepository,
) {
    fun add(address: String): ObjectId {
        val area = Area(address)
        return areaRepository.save(area).id
    }
}
