package com.hsik.smoking.domain.town

import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.domain.town.repository.TownRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TownFinder(
    private val townRepository: TownRepository,
) {
    fun findById(id: String): Town = townRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException(id, RESOURCE)

    companion object {
        const val RESOURCE = "타운(구)"
    }
}
