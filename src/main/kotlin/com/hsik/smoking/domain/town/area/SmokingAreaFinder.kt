package com.hsik.smoking.domain.town.area

import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.common.relativeOrThrow
import com.hsik.smoking.domain.town.area.repository.SmokingAreaRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SmokingAreaFinder(
    private val smokingAreaRepository: SmokingAreaRepository,
) {
    fun findById(id: String): SmokingArea =
        smokingAreaRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException(id, RESOURCE)

    fun findByTownIdAndAreaId(
        townId: String,
        areaId: String,
    ): SmokingArea = findById(areaId).relativeOrThrow(townId)

    companion object {
        const val RESOURCE = "흡연소"
    }
}
