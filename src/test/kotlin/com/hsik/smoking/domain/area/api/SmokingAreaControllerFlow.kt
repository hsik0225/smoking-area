package com.hsik.smoking.domain.area.api

import com.hsik.smoking.common.Replies
import com.hsik.smoking.common.Reply
import com.hsik.smoking.domain.area.SmokingArea
import com.hsik.smoking.util.fromJson
import com.hsik.smoking.util.toJson
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

class SmokingAreaControllerFlow(
    private val mockMvc: MockMvc,
) {
    fun search(townName: SmokingArea.TownName? = null): List<SmokingAreaResources.Response.Me> {
        val uri = linkTo<SmokingAreaController> { search(townName) }.toUri()
        return mockMvc
            .get(uri)
            .andExpect {
                status { is2xxSuccessful() }
            }.andReturn()
            .response
            .contentAsString
            .fromJson<Replies<SmokingAreaResources.Response.Me>>()
            .collection
            .toList()
    }

    fun findOne(areaId: String): SmokingAreaResources.Response.Me {
        val uri = linkTo<SmokingAreaController> { findOne(areaId) }.toUri()
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
        townName: SmokingArea.TownName,
        address: String,
    ): String {
        val request = SmokingAreaResources.Request.Me(townName, address)
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

    fun sync(townName: SmokingArea.TownName) {
        val uri = linkTo<SmokingAreaController> { sync(townName) }.toUri()
        mockMvc
            .put(uri)
            .andExpect {
                status { is2xxSuccessful() }
            }
    }
}
