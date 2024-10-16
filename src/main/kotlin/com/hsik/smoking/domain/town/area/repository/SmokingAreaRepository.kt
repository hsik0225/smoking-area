package com.hsik.smoking.domain.town.area.repository

import com.hsik.smoking.domain.town.area.SmokingArea
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface SmokingAreaRepository : MongoRepository<SmokingArea, ObjectId>
