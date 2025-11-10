package org.example.greenometer.dto

import java.time.LocalDate

data class ProduceLogDto(
    val produceId: String,
    val quantity: Int,
    val date: LocalDate?,
)
