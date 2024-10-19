package com.hsik.smoking.domain.area.api

import com.hsik.smoking.common.Replies
import com.hsik.smoking.common.Reply
import com.hsik.smoking.common.toReplies
import com.hsik.smoking.common.toReply
import com.hsik.smoking.domain.area.SmokingArea
import com.hsik.smoking.domain.area.SmokingAreaFinder
import com.hsik.smoking.domain.area.SmokingAreaService
import com.hsik.smoking.domain.area.SmokingAreaSyncService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/areas")
class SmokingAreaController(
    private val smokingAreaFinder: SmokingAreaFinder,
    private val smokingAreaService: SmokingAreaService,
    private val smokingAreaSyncService: SmokingAreaSyncService,
) {
    @GetMapping
    fun search(
        @RequestParam townName: SmokingArea.TownName? = null,
    ): Replies<SmokingAreaResources.Response.Me> {
        val areas = smokingAreaFinder.search(townName)
        return SmokingAreaResources.Response.Me
            .from(areas)
            .toReplies()
    }

    @GetMapping("/{areaId}")
    fun findOne(
        @PathVariable("areaId") areaId: String,
    ): Reply<SmokingAreaResources.Response.Me> {
        val area = smokingAreaFinder.findById(areaId)
        return SmokingAreaResources.Response.Me
            .from(area)
            .toReply()
    }

    @PostMapping
    fun add(
        @RequestBody request: SmokingAreaResources.Request.Me,
    ): Reply<String> =
        smokingAreaService
            .add(request.name, request.address)
            .toString()
            .toReply()

    @PutMapping("/name/{name}/sync")
    fun sync(
        @PathVariable name: SmokingArea.TownName,
    ): Reply<Unit> {
        smokingAreaSyncService.sync(name)
        return Unit.toReply()
    }
}
