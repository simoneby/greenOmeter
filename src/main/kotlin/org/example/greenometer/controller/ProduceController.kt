package org.example.greenometer.controller

import org.example.greenometer.domain.Produce
import org.example.greenometer.service.ProduceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produce")
class ProduceController(
    val produceService: ProduceService,
) {
    @GetMapping()
    fun getProduce(): List<Produce> = produceService.getAllProduce()

    @PostMapping("/{id}/tag")
    fun tagProduce(
        @PathVariable id: String,
        @RequestBody tags: List<String>,
    ): Produce = produceService.tagProduce(id, tags)
}
