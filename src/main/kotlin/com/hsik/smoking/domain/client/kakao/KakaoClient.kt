package com.hsik.smoking.domain.client.kakao

interface KakaoClient {
    fun geocoding(address: String): List<KakaoResources.Local.Geocoding>
}
