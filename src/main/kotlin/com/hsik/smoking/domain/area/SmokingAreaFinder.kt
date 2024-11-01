package com.hsik.smoking.domain.area

import com.hsik.smoking.common.ResourceNotFoundException
import com.hsik.smoking.domain.area.repository.SmokingAreaRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SmokingAreaFinder(
    private val smokingAreaRepository: SmokingAreaRepository,
    private val townCacheStore: TownCacheStore,
    private val geocoder: Geocoder,
) {
    fun search(townName: SmokingArea.TownName? = null): List<SmokingArea> {
        if (townName == null) {
            val smokingAreas = smokingAreaRepository.findAll()
            return geocoder.searchByAddress(smokingAreas)
        }

        if (townCacheStore.hasKey(townName)) {
            return townCacheStore.getValue(townName)
        }

        val smokingAreas = smokingAreaRepository.findAllByName(townName)
        val smokingAreasWithCoordinate = geocoder.searchByAddress(smokingAreas)
        townCacheStore.set(townName, smokingAreasWithCoordinate)
        return smokingAreasWithCoordinate
    }

    fun findById(id: String): SmokingArea =
        smokingAreaRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException("ID", id, RESOURCE)

    companion object {
        const val RESOURCE = "흡연소"
    }
}
