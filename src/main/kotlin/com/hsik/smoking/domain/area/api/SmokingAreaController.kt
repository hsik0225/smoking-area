package com.hsik.smoking.domain.area.api

import com.hsik.smoking.common.Reply
import com.hsik.smoking.common.toReply
import com.hsik.smoking.domain.area.SmokingAreaFinder
import com.hsik.smoking.domain.area.SmokingAreaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/towns/{townId}/areas")
class SmokingAreaController(
    private val smokingAreaFinder: SmokingAreaFinder,
    private val smokingAreaService: SmokingAreaService,
) {
    @GetMapping("/{areaId}")
    fun findOne(
        @PathVariable("townId") townId: String,
        @PathVariable("areaId") areaId: String,
    ): Reply<SmokingAreaResources.Response.Me> {
        val area = smokingAreaFinder.findByTownIdAndAreaId(townId, areaId)
        return SmokingAreaResources.Response.Me
            .from(area)
            .toReply()
    }

    @PostMapping
    fun add(
        @PathVariable("townId") townId: String,
        @RequestBody request: SmokingAreaResources.Request.Me,
    ): Reply<String> =
        smokingAreaService
            .add(townId, request.address)
            .toString()
            .toReply()
}
