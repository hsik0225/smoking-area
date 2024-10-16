package com.hsik.smoking.domain.area.api

import com.hsik.smoking.common.Reply
import com.hsik.smoking.domain.town.area.api.SmokingAreaController
import com.hsik.smoking.domain.town.area.api.SmokingAreaResources
import com.hsik.smoking.util.fromJson
import com.hsik.smoking.util.toJson
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class AreaControllerFlow(
    private val mockMvc: MockMvc,
) {
    fun findOne(
        townId: String,
        areaId: String,
    ): SmokingAreaResources.Response.Me {
        val uri = linkTo<SmokingAreaController> { findOne(townId, areaId) }.toUri()
        return mockMvc
            .get(uri)
            .andExpect {
                status { is2xxSuccessful() }
            }.andReturn()
            .response
            .contentAsString
            .fromJson<Reply<SmokingAreaResources.Response.Me>>()
            .content
    }

    fun add(
        townId: String,
        address: String,
    ): String {
        val request = SmokingAreaResources.Request.Me(address)
        val uri = linkTo<SmokingAreaController> { add(townId, request) }.toUri()
        return mockMvc
            .post(uri) {
                contentType = MediaType.APPLICATION_JSON
                content = request.toJson()
            }.andExpect {
                status { is2xxSuccessful() }
            }.andReturn()
            .response
            .contentAsString
            .fromJson<Reply<String>>()
            .content
    }
}
