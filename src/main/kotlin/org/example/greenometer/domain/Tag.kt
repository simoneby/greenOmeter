package org.example.greenometer.domain

import org.example.greenometer.entities.TagEntity

data class Tag(
    val name: String,
) {
    companion object {
        fun fromEntity(tagEntity: TagEntity): Tag =
            Tag(
                name = tagEntity.tag,
            )
    }
}
