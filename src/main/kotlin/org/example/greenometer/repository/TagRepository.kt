package org.example.greenometer.repository

import org.example.greenometer.entities.TagEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TagRepository : JpaRepository<TagEntity, UUID> {
    fun findTagEntityByTag(tag: String): TagEntity?
}
