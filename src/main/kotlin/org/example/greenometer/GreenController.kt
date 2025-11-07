package org.example.greenometer

import org.example.greenometer.domain.Produce
import org.example.greenometer.domain.User
import org.springframework.web.bind.annotation.*

@RestController
class GreenController(
    val greenService: GreenService,
) {
    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: String,
    ): User = greenService.getUser(id)

    @GetMapping("/produce")
    fun getProduce(): List<Produce> = greenService.getAllProduce()

    @PostMapping("/produce/{id}/tag")
    fun tagProduce(
        @PathVariable id: String,
        @RequestBody tags: List<String>,
    ): Produce {
        println(tags)
        return greenService.tagProduce(id, tags)
    }
}
