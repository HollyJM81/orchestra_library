package com.orchlib.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ComposerController {
    @GetMapping
    @RequestMapping("/composers")
    fun composers(): String? {
        return "Here are our composers"
    }
}