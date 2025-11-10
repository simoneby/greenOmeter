package org.example.greenometer.controller

import org.example.greenometer.domain.User
import org.example.greenometer.dto.ProduceLogDto
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

    @PostMapping("/log")
    fun logProduce(
        @RequestParam userId: String,
        @RequestBody produceLogDto: ProduceLogDto,
    ): String = userService.logProduce(userId, produceLogDto)
}
