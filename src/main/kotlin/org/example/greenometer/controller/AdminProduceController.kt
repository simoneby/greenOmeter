package org.example.greenometer.controller

import org.example.greenometer.domain.*
import org.example.greenometer.domain.ProduceCategory
import org.example.greenometer.dto.*
import org.example.greenometer.service.ProduceService
import org.example.greenometer.service.ProduceTranslationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/admin/produce")
class AdminProduceController(
    private val produceService: ProduceService,
    private val translationService: ProduceTranslationService,
) {
    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("produceList", produceService.getAllProduce())
        return "admin/produce/list"
    }

    @GetMapping("/new")
    fun newForm(model: Model): String {
        model.addAttribute("produce", CreateProduceDto())
        model.addAttribute("categories", ProduceCategory.entries)
        model.addAttribute("colors", ProduceColor.entries)
        return "admin/produce/form"
    }

    @PostMapping
    fun create(@ModelAttribute dto: CreateProduceDto): String {
        produceService.createProduce(dto)
        return "redirect:/admin/produce"
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id: UUID, model: Model): String {
        val entity = produceService.getProduceWithDetails(id)
            ?: throw NoSuchElementException("Produce not found: $id")

        model.addAttribute("produce", Produce.fromEntity(entity))
        model.addAttribute("tags", entity.tags.joinToString(", ") { it.tag })
        model.addAttribute("translations", entity.translations.map { ProduceTranslation.fromEntity(it) })

        // Form DTOs
        model.addAttribute("updateDto", UpdateProduceDto(
            name = entity.name,
            emoji = entity.emoji,
            category = entity.category?.let { ProduceCategory.valueOf(it) },
            color = entity.color?.let { ProduceColor.valueOf(it) },
            description = entity.description,
            tags = entity.tags.joinToString(", ") { it.tag },
        ))
        model.addAttribute("translationDto", TranslationDto())
        model.addAttribute("categories", ProduceCategory.entries)
        model.addAttribute("colors", ProduceColor.entries)

        return "admin/produce/detail"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: UUID, @ModelAttribute dto: UpdateProduceDto): String {
        produceService.updateProduce(id, dto)
        return "redirect:/admin/produce/$id"
    }

    @PostMapping("/{id}/delete")
    fun delete(@PathVariable id: UUID): String {
        produceService.deleteProduce(id)
        return "redirect:/admin/produce"
    }

    @PostMapping("/{id}/translations")
    fun saveTranslation(@PathVariable id: UUID, @ModelAttribute dto: TranslationDto): String {
        translationService.saveTranslation(id, dto)
        return "redirect:/admin/produce/$id"
    }

    @PostMapping("/{id}/translations/{language}/delete")
    fun deleteTranslation(@PathVariable id: UUID, @PathVariable language: String): String {
        translationService.deleteTranslation(id, language)
        return "redirect:/admin/produce/$id"
    }
}
