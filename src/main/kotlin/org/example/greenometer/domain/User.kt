package org.example.greenometer.domain

import org.example.greenometer.entities.UserEntity

data class User(
    val id: String,
    val name: String,
) {
    companion object {
        fun fromEntity(entity: UserEntity): User =
            User(
                id = entity.id.toString(),
                name = entity.name,
            )
    }
}
