package com.hsik.smoking.domain.area.repository

import com.hsik.smoking.domain.area.SmokingArea
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface SmokingAreaRepository : MongoRepository<SmokingArea, ObjectId> {
    fun findAllByName(name: SmokingArea.TownName): List<SmokingArea>
}
