package com.hsik.smoking.common

import com.hsik.smoking.domain.client.OpenDataPortalResources
import com.hsik.smoking.util.fromJson
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OpenDataPortalReplyTest {
    @DisplayName("오픈 데이터 포탈에서 받은 응답을 OpenDataPortalResources 클래스로 정상적으로 변환하는지 확인")
    @Test
    fun jsonToOpenDataPortalResourcesTest() {
        // Given
        val json =
            """
            {
              "currentCount": 3,
              "data": [
                {
                  "규모": "5㎡",
                  "데이터기준일자": "2021-10-14",
                  "설치년도": "2016.12.",
                  "설치위치": "테스트 커피(테스트대로 20) 옥상",
                  "설치주체": "테스트 커피",
                  "시설형태": "완전폐쇄형",
                  "연번": 1,
                  "운영관리": "레프트커피"
                },
                {
                  "규모": "2㎡",
                  "데이터기준일자": "2021-10-14",
                  "설치년도": "-",
                  "설치위치": "테스트 스크린 야구",
                  "설치주체": "테스트 스크린 야구",
                  "시설형태": "완전폐쇄형",
                  "연번": 2,
                  "운영관리": "빅히트 스크린야구"
                },
                {
                  "규모": "15㎡",
                  "데이터기준일자": "2021-10-14",
                  "설치년도": "2014",
                  "설치위치": "테스트 건물",
                  "설치주체": "건물관리자",
                  "시설형태": "개방형",
                  "연번": 3,
                  "운영관리": "건물관리자"
                }
              ],
              "matchCount": 110,
              "page": 1,
              "perPage": 3,
              "totalCount": 110
            }
            """.trimIndent()

        // When
        val smokingAreasInDongdaemunGu =
            json.fromJson<OpenDataPortalReply<OpenDataPortalResources.SmokingArea.DongdaemunGu>>().data

        // Then
        smokingAreasInDongdaemunGu.shouldHaveSize(3)
    }
}
