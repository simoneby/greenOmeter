package org.example.greenometer.domain

import org.example.greenometer.entities.ProduceEntity
import java.util.*

data class Produce(
    val id: UUID? = null,
    val name: String,
    val emoji: String,
    val category: ProduceCategory? = null,
    val color: ProduceColor? = null,
    val description: String? = null,
    val tags: Set<String>,
) {
    companion object {
        fun fromEntity(entity: ProduceEntity): Produce =
            Produce(
                id = entity.id,
                name = entity.name,
                emoji = entity.emoji,
                category = entity.category?.let { ProduceCategory.valueOf(it) },
                color = entity.color?.let { ProduceColor.valueOf(it) },
                description = entity.description,
                tags = entity.tags.map { it.tag }.toSet(),
            )
    }
}
