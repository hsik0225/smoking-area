package com.hsik.boilerplate.domain

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/homes/{homeId}")
class MenuController {
    @GetMapping("/menus/{menuId}")
    fun notImplementedMenu(
        @PathVariable("homeId") homeId: String,
        @PathVariable("menuId") menuId: String,
    ): String = throw UnsupportedOperationException("Not implemented yet")

    @ExceptionHandler(UnsupportedOperationException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(e: UnsupportedOperationException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)
}
