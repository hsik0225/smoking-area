package com.hsik.smoking.domain.area

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 흡연소
 */
@Document
class Area {

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

    /**
     * 공공 데이터 포탈에서 데이터를 수집/수정한 일자
     */
    var metadataUpdateDate: LocalDate? = null

    /**
     * 주소
     */
    var address: String? = null

    /**
     * 위도
     */
    var langitude: Double? = null

    /**
     * 경도
     */
    var longitude: Double? = null

    /**
     * 운영 관리자
     */
    var manager: String? = null

    /**
     * 운영 상태
     */
    var status: Status? = null

    /**
     * 장소에 대한 설명
     */
    var description: String? = null

    enum class Status(val desc: String) {
        OPEN("지도에 노출"),
        HIDE("지도에 미노출"),
        CLOSED("폐쇄")
    }
}
