package com.hsik.smoking.domain.client.kakao

class DummyKakaoClient : KakaoClient {
    override fun geocoding(address: String): List<KakaoResources.Local.Geocoding> =
        listOf(
            KakaoResources.Local.Geocoding(
                addressName = "전북 익산시 부송동 100",
                latitude = "35.97664845766847",
                longitude = "126.99597295767953",
                addressType = "REGION_ADDR",
                lotNumberAddress =
                    KakaoResources.Local.Geocoding.LotNumberAddress(
                        addressName = "전북 익산시 부송동 100",
                        siDo = "전북",
                        gu = "익산시",
                        dong = "부송동",
                        administrationDong = "삼성동",
                        hCode = "4514069000",
                        bCode = "4514013400",
                        isMountain = false,
                        mainAddressNumber = "100",
                        subAddressNumber = "",
                        latitude = "126.99597295767953",
                        longitude = "35.97664845766847",
                    ),
                roadAddress =
                    KakaoResources.Local.Geocoding.RoadAddress(
                        addressName = "전북 익산시 망산길 11-17",
                        siDo = "전북",
                        gu = "익산시",
                        dong = "부송동",
                        roadName = "망산길",
                        isUnderground = false,
                        mainBuildingNumber = "11",
                        subBuildingNumber = "17",
                        buildingName = "",
                        zipCode = "54547",
                        latitude = "126.99597295767953",
                        longitude = "35.97664845766847",
                    ),
            ),
            KakaoResources.Local.Geocoding(
                addressName = "충북 청주시 용암동 100",
                latitude = "36.97664845766847",
                longitude = "126.995972234767953",
                addressType = "ROAD_ADDR",
                lotNumberAddress =
                    KakaoResources.Local.Geocoding.LotNumberAddress(
                        addressName = "충북 청주시 용암동 100",
                        siDo = "충북",
                        gu = "청주시",
                        dong = "용암동",
                        administrationDong = "용암동",
                        hCode = "55123069000",
                        bCode = "55143013400",
                        isMountain = false,
                        mainAddressNumber = "200",
                        subAddressNumber = "",
                        latitude = "126.995972234767953",
                        longitude = "36.97664845766847",
                    ),
                roadAddress =
                    KakaoResources.Local.Geocoding.RoadAddress(
                        addressName = "충북 청주시 용암길 22-17",
                        siDo = "충북",
                        gu = "청주시",
                        dong = "용암동",
                        roadName = "용암길",
                        isUnderground = false,
                        mainBuildingNumber = "11",
                        subBuildingNumber = "17",
                        buildingName = "한라 비발디",
                        zipCode = "02022",
                        latitude = "126.995972234767953",
                        longitude = "36.97664845766847",
                    ),
            ),
        )
}
