package com.hsik.smoking.common

import com.hsik.smoking.domain.client.kakao.KakaoResources
import com.hsik.smoking.util.fromJson
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class KakaoReplyTest {
    @DisplayName("카카오 로컬 API에서 받은 응답을 KakaoResources 클래스로 정상적으로 변환하는지 확인")
    @Test
    fun jsonToKakaoResourcesTest() {
        // Given
        val json =
            """
            {
              "meta": {
                "total_count": 4,
                "pageable_count": 4,
                "is_end": true
              },
              "documents": [
                {
                  "address_name": "전북 익산시 부송동 100",
                  "y": "35.97664845766847",
                  "x": "126.99597295767953",
                  "address_type": "REGION_ADDR",
                  "address": {
                    "address_name": "전북 익산시 부송동 100",
                    "region_1depth_name": "전북",
                    "region_2depth_name": "익산시",
                    "region_3depth_name": "부송동",
                    "region_3depth_h_name": "삼성동",
                    "h_code": "4514069000",
                    "b_code": "4514013400",
                    "mountain_yn": "N",
                    "main_address_no": "100",
                    "sub_address_no": "",
                    "x": "126.99597295767953",
                    "y": "35.97664845766847"
                  },
                  "road_address": {
                    "address_name": "전북 익산시 망산길 11-17",
                    "region_1depth_name": "전북",
                    "region_2depth_name": "익산시",
                    "region_3depth_name": "부송동",
                    "road_name": "망산길",
                    "underground_yn": "N",
                    "main_building_no": "11",
                    "sub_building_no": "17",
                    "building_name": "",
                    "zone_no": "54547",
                    "y": "35.976749396987046",
                    "x": "126.99599512792346"
                  }
                }
              ]
            }
            """.trimIndent()

        // When
        val geocoding =
            json
                .fromJson<KakaoReply<KakaoResources.Local.Geocoding>>()
                .documents
                .first()

        // Then
        geocoding.lotNumberAddress.addressName shouldBe "전북 익산시 부송동 100"
        geocoding.lotNumberAddress.isMountain shouldBe false
        geocoding.roadAddress.isUnderground shouldBe false
    }
}
