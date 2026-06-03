package org.example.greenometer.dto

import org.example.greenometer.domain.ProduceCategory
import org.example.greenometer.domain.ProduceColor
import java.util.*

data class CreateProduceDto(
    val name: String = "",
    val emoji: String = "",
    val category: ProduceCategory? = null,
    val color: ProduceColor? = null,
    val description: String? = null,
)

data class UpdateProduceDto(
    val name: String = "",
    val emoji: String = "",
    val category: ProduceCategory? = null,
    val color: ProduceColor? = null,
    val description: String? = null,
    val tags: String? = null,
)

data class TranslationDto(
    val language: String = "",
    val name: String = "",
)
