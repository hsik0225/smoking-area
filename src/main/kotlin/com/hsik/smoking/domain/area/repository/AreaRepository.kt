package com.hsik.smoking.domain.area.repository

import com.hsik.smoking.domain.area.Area
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface AreaRepository : MongoRepository<Area, ObjectId>
