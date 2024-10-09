package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import com.hsik.smoking.domain.town.TownFinder
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class SmokingAreaService(
    private val smokingAreaRepository: SmokingAreaRepository,
    private val townFinder: TownFinder,
) {
    fun add(
        townId: String,
        address: String,
    ): ObjectId {
        val area = SmokingArea(address)
        val town = townFinder.findById(townId)
        town.add(area)
        return smokingAreaRepository.save(area).id
    }
}
