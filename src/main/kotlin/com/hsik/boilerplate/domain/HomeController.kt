package com.hsik.boilerplate.domain

import com.hsik.boilerplate.common.Reply
import com.hsik.boilerplate.common.toReply
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/home")
    fun home(): Reply<String> = "Home page".toReply()
}
