package com.hsik.smoking.domain.town.api

import com.hsik.smoking.common.Reply
import com.hsik.smoking.domain.town.Town
import com.hsik.smoking.util.fromJson
import com.hsik.smoking.util.toJson
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class TownControllerFlow(
    private val mockMvc: MockMvc,
) {
    fun findOne(id: String): TownResources.Response.Me {
        val uri = linkTo<TownController> { findOne(id) }.toUri()
        return mockMvc
            .get(uri)
            .andExpect {
                status { is2xxSuccessful() }
            }.andReturn()
            .response
            .contentAsString
            .fromJson<Reply<TownResources.Response.Me>>()
            .content
    }

    fun add(name: Town.TownName): String {
        val request = TownResources.Request.Me(name = name)
        val uri = linkTo<TownController> { add(request) }.toUri()
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
