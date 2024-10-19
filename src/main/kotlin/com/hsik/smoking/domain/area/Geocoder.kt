package com.hsik.smoking.domain.area

import com.hsik.smoking.domain.client.kakao.KakaoClient

class Geocoder(
    private val kakaoClient: KakaoClient,
    private val smokingAreas: List<SmokingArea>,
) {
    fun searchByAddress(): List<SmokingArea> {
        for (smokingArea in smokingAreas) {
            kakaoClient
                .geocoding(smokingArea.address)
                ?.let {
                    smokingArea.latitude = it.latitude.toDouble()
                    smokingArea.longitude = it.longitude.toDouble()
                }
        }
        return smokingAreas
    }
}
