package com.hsik.smoking.domain.area

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Area {

    @Id
    var id: ObjectId = ObjectId()
        private set

    var createdAt = LocalDateTime.now()
        private set

    var modifiedAt = LocalDateTime.now()
        private set

    var langitude: Double? = null
    var longitude: Double? = null
}
