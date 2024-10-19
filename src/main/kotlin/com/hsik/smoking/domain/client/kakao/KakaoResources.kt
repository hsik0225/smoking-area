package com.hsik.smoking.domain.client.kakao

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.hsik.smoking.util.deserializer.StringBooleanDeserializer

class KakaoResources {
    class Local {
        data class Geocoding(
            /**
             * 전체 지번 주소 또는 전체 도로명 주소
             */
            @JsonAlias("address_name")
            val addressName: String,
            /**
             * 위도
             * - Example "35.97664845766847"
             */
            @JsonAlias("y")
            val latitude: String,
            /**
             * 경도
             * - Example "126.99597295767953"
             */
            @JsonAlias("x")
            val longitude: String,
            /**
             * address_name의 값의 타입(Type)
             *
             * 다음 중 하나:
             * - REGION(지명)
             * - ROAD(도로명)
             * - REGION_ADDR(지번 주소)
             * - ROAD_ADDR(도로명 주소)
             */
            @JsonAlias("address_type")
            val addressType: String,
            /**
             * 지번 주소 상세 정보
             */
            @JsonAlias("address")
            val lotNumberAddress: LotNumberAddress,
            /**
             * 도로명 주소 상세 정보
             */
            @JsonAlias("road_address")
            val roadAddress: RoadAddress,
        ) {
            data class LotNumberAddress(
                /**
                 * 전체 지번 주소
                 */
                @JsonAlias("address_name")
                val addressName: String,
                /**
                 * 시/도 명칭
                 */
                @JsonAlias("region_1depth_name")
                val siDo: String,
                /**
                 * 구 명칭
                 */
                @JsonAlias("region_2depth_name")
                val gu: String,
                /**
                 * 동 명칭
                 */
                @JsonAlias("region_3depth_name")
                val dong: String,
                /**
                 * 행정동 명칭
                 */
                @JsonAlias("region_3depth_h_name")
                val administrationDong: String,
                /**
                 * 행정 코드
                 */
                @JsonAlias("h_code")
                val hCode: String,
                /**
                 * 법정 코드
                 */
                @JsonAlias("b_code")
                val bCode: String,
                /**
                 * 산 여부, Y 또는 N
                 */
                @JsonDeserialize(using = StringBooleanDeserializer::class)
                @JsonAlias("mountain_yn")
                val isMountain: Boolean,
                /**
                 * 지번 주번지
                 */
                @JsonAlias("main_address_no")
                val mainAddressNumber: String,
                /**
                 * 지번 주번지
                 */
                @JsonAlias("sub_address_no")
                val subAddressNumber: String,
                /**
                 * 위도
                 */
                @JsonAlias("y")
                val latitude: String,
                /**
                 * 경도
                 */
                @JsonAlias("x")
                val longitude: String,
            )

            data class RoadAddress(
                /**
                 * 전체 도로명 주소
                 */
                @JsonAlias("address_name")
                val addressName: String,
                /**
                 * 시/도 명칭
                 */
                @JsonAlias("region_1depth_name")
                val siDo: String,
                /**
                 * 구 명칭
                 */
                @JsonAlias("region_2depth_name")
                val gu: String,
                /**
                 * 동 명칭
                 */
                @JsonAlias("region_3depth_name")
                val dong: String,
                /**
                 * 도로명
                 */
                @JsonAlias("road_name")
                val roadName: String,
                /**
                 * 지하 여부
                 */
                @JsonDeserialize(using = StringBooleanDeserializer::class)
                @JsonAlias("underground_yn")
                val isUnderground: Boolean,
                /**
                 * 건물 본번
                 */
                @JsonAlias("main_building_no")
                val mainBuildingNumber: String,
                /**
                 * 건물 부번
                 */
                @JsonAlias("sub_building_no")
                val subBuildingNumber: String,
                /**
                 * 건물 이름
                 */
                @JsonAlias("building_name")
                val buildingName: String,
                /**
                 * 우편 번호(5자리)
                 */
                @JsonAlias("zone_no")
                val zipCode: String,
                /**
                 * 위도
                 */
                @JsonAlias("y")
                val latitude: String,
                /**
                 * 경도
                 */
                @JsonAlias("x")
                val longitude: String,
            )
        }
    }
}
