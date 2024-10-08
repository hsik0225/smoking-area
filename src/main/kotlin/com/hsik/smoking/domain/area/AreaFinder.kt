package com.hsik.smoking.domain.area

import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.domain.area.repository.AreaRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AreaFinder(
    private val areaRepository: AreaRepository,
) {
    fun findById(id: String): Area = areaRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException(id, RESOURCE)

    companion object {
        const val RESOURCE = "흡연소"
    }
}
