package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.client.kakao.KakaoClient
import org.springframework.stereotype.Component

@Component
class Geocoder(
    private val smokingAreaCacheStore: SmokingAreaCacheStore,
    private val kakaoClient: KakaoClient,
) {
    fun searchByAddress(smokingAreas: List<SmokingArea>): List<SmokingArea> {
        for (smokingArea in smokingAreas) {
            // Use cache data if exists
            if (smokingAreaCacheStore.hasKey(smokingArea.id)) {
                val cachedSmokingArea = smokingAreaCacheStore.getValue(smokingArea.id)
                smokingArea.latitude = cachedSmokingArea.latitude!!.toDouble()
                smokingArea.longitude = cachedSmokingArea.longitude!!.toDouble()
                continue
            }

            // Call kakao client
            kakaoClient
                .geocoding(smokingArea.address)
                ?.let {
                    smokingArea.latitude = it.latitude.toDouble()
                    smokingArea.longitude = it.longitude.toDouble()
                }

            // Store data in cache
            smokingAreaCacheStore.set(smokingArea.id, smokingArea)
        }
        return smokingAreas
    }
}
