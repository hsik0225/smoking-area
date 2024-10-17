package com.hsik.smoking.domain.area

import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SmokingAreaFinder(
    private val smokingAreaRepository: SmokingAreaRepository,
) {
    fun findAll(): List<SmokingArea> = smokingAreaRepository.findAll()

    fun findAllByTownName(name: SmokingArea.TownName): List<SmokingArea> = smokingAreaRepository.findAllByName(name)

    fun findById(id: String): SmokingArea =
        smokingAreaRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException("ID", id, RESOURCE)

    companion object {
        const val RESOURCE = "흡연소"
    }
}
