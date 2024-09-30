package com.hsik.smoking.domain

import com.hsik.smoking.common.Reply
import com.hsik.smoking.common.toReply
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/home")
    fun home(): Reply<String> = "Home page".toReply()
}
