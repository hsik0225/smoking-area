package com.hsik.smoking.domain.town.api

import com.hsik.smoking.common.Reply
import com.hsik.smoking.common.toReply
import com.hsik.smoking.domain.town.TownFinder
import com.hsik.smoking.domain.town.TownService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/towns")
class TownController(
    private val townFinder: TownFinder,
    private val townService: TownService,
) {
    @GetMapping("/{id}")
    fun findOne(
        @PathVariable("id") id: String,
    ): Reply<TownResources.Response.Me> {
        val town = townFinder.findById(id)
        return TownResources.Response.Me
            .from(town)
            .toReply()
    }

    @PostMapping
    fun add(
        @RequestBody request: TownResources.Request.Me,
    ): Reply<String> =
        townService
            .add(request.name)
            .toString()
            .toReply()
}
