package com.hsik.smoking.domain.town

import com.hsik.smoking.domain.area.SmokingArea
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * 시/군/구의 구
 * - Town에는 N개의 흡연소(SmokingArea)가 존재 => 1:N
 */
@Document
class Town(
    /**
     * 이름
     */
    var name: String,
) {
    @Id
    var id: ObjectId = ObjectId()
        private set

    /**
     * 생성 시각
     */
    var createdAt = LocalDateTime.now()
        private set

    /**
     * 수정 시각
     */
    var modifiedAt = LocalDateTime.now()
        private set

    @DBRef
    var smokingAreas: MutableList<SmokingArea> = mutableListOf()
        private set

    /**
     * 운영 상태
     */
    var status: Status = Status.SHOW
        private set

    /**
     * 운영 상태 변경 이유
     */
    var cause: String? = null

    enum class Status(
        val desc: String,
    ) {
        SHOW("지도에 노출"),
        HIDE("지도에 미노출"),
        DELETED("삭제"),
    }

    fun add(smokingArea: SmokingArea) {
        this.smokingAreas.add(smokingArea)
    }
}
