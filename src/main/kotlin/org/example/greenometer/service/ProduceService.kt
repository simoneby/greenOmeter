package org.example.greenometer.service

import org.example.greenometer.domain.Produce
import org.example.greenometer.domain.Tag
import org.example.greenometer.entities.TagEntity
import org.example.greenometer.repository.ProduceRepository
import org.example.greenometer.repository.TagRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProduceService(
    val produceRepository: ProduceRepository,
    val tagRepository: TagRepository,
) {
    fun getAllProduce(): List<Produce> {
        val produceEntities = produceRepository.findAll()
        return produceEntities.map { Produce.fromEntity(it) }
    }

    fun getProduce(id: String): Produce = Produce.fromEntity(produceRepository.findById(UUID.fromString(id)).get())

    fun tagProduce(
        id: String,
        tags: List<String>,
    ): Produce {
        val produceEntity = produceRepository.findById(UUID.fromString(id)).get()
        val uniqueTags = tags.map { it.lowercase() }.toMutableSet()
        val tagEntities =
            uniqueTags.map { tagName ->
                tagRepository.findTagEntityByTag(tagName)
                    ?: tagRepository.save(TagEntity(tag = tagName))
            }
        produceEntity.tags.addAll(tagEntities)
        return Produce.fromEntity(produceRepository.save(produceEntity))
    }

    fun getTags(): List<Tag> = tagRepository.findAll().map { Tag.fromEntity(it) }
}
