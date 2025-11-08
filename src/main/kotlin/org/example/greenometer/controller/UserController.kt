package org.example.greenometer.controller

import org.example.greenometer.domain.User
import org.example.greenometer.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService,
) {
    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: String,
    ): User = userService.getUser(id)
}
