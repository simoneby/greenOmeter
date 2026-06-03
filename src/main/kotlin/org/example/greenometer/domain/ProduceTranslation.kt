package org.example.greenometer.domain

import org.example.greenometer.entities.ProduceTranslationEntity

data class ProduceTranslation(
    val language: String,
    val name: String,
) {
    companion object {
        fun fromEntity(entity: ProduceTranslationEntity): ProduceTranslation =
            ProduceTranslation(
                language = entity.id.language,
                name = entity.name,
            )
    }
}
