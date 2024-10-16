package com.hsik.smoking.domain.town

import com.hsik.smoking.domain.town.repository.TownRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class TownService(
    private val townRepository: TownRepository,
) {
    fun add(name: Town.TownName): ObjectId {
        val town = Town(name)
        return townRepository.save(town).id
    }
}
