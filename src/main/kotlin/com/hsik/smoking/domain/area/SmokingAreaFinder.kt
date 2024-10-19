package com.hsik.smoking.domain.area

import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import com.hsik.smoking.domain.client.kakao.KakaoClient
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SmokingAreaFinder(
    private val smokingAreaRepository: SmokingAreaRepository,
    private val mongoTemplate: MongoTemplate,
    private val kakaoClient: KakaoClient,
) {
    fun search(townName: SmokingArea.TownName? = null): List<SmokingArea> {
        val query =
            Query().apply {
                townName?.let { Criteria.where("name").`is`(it) }
            }

        val smokingAreas = mongoTemplate.find(query, SmokingArea::class.java)
        return Geocoder(kakaoClient, smokingAreas).searchByAddress()
    }

    fun findById(id: String): SmokingArea =
        smokingAreaRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException("ID", id, RESOURCE)

    companion object {
        const val RESOURCE = "흡연소"
    }
}
