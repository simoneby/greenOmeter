package org.example.greenometer.domain

import org.example.greenometer.entities.ProduceEntity

data class Produce(
    val name: String,
    val emoji: String,
    val tags: Set<String>,
) {
    companion object {
        fun fromEntity(entity: ProduceEntity): Produce =
            Produce(
                name = entity.name,
                emoji = entity.emoji,
                tags = entity.tags.map { it.tag }.toSet(),
            )
    }
}
