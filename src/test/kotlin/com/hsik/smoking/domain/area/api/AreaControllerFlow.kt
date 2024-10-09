package com.hsik.smoking.domain.area.api

import com.hsik.smoking.common.Reply
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
    fun findOne(id: String): SmokingAreaResources.Response.Me {
        val uri = linkTo<SmokingAreaController> { findOne(id) }.toUri()
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

    fun add(address: String): String {
        val request = SmokingAreaResources.Request.Me(address)
        val uri = linkTo<SmokingAreaController> { add(request) }.toUri()
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
