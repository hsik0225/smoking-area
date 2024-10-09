package com.hsik.smoking.domain.area.api

import com.hsik.smoking.common.Reply
import com.hsik.smoking.common.toReply
import com.hsik.smoking.domain.area.AreaFinder
import com.hsik.smoking.domain.area.AreaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/areas")
class AreaController(
    private val areaFinder: AreaFinder,
    private val areaService: AreaService,
) {
    @GetMapping("/{id}")
    fun findOne(
        @PathVariable("id") id: String,
    ): Reply<AreaResources.Response.Me> {
        val area = areaFinder.findById(id)
        return AreaResources.Response.Me
            .from(area)
            .toReply()
    }

    @PostMapping
    fun add(
        @RequestBody request: AreaResources.Request.Me,
    ): Reply<String> =
        areaService
            .add(request.address)
            .toString()
            .toReply()
}
