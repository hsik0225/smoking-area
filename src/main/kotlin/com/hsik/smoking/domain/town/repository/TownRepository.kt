package com.hsik.smoking.domain.town.repository

import com.hsik.smoking.domain.town.Town
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TownRepository : MongoRepository<Town, ObjectId>
