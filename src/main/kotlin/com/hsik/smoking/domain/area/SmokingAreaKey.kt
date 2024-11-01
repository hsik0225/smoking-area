package com.hsik.smoking.domain.area

import org.bson.types.ObjectId

class SmokingAreaKey {
    fun getKeyOfTown(townName: SmokingArea.TownName): String = "$KEY:town:$townName"

    fun getKeyOfSmokingArea(smokingAreaId: ObjectId): String = "$KEY:smoking-area:$smokingAreaId"

    companion object {
        const val KEY = "smoking-area-api"
    }
}
