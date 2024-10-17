package com.hsik.smoking.domain.area.repository

import com.hsik.smoking.domain.area.SmokingArea
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface SmokingAreaRepository : MongoRepository<SmokingArea, ObjectId> {
    fun findAllByName(townName: SmokingArea.TownName): List<SmokingArea>
}
