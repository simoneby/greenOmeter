package org.example.greenometer.service

import org.example.greenometer.domain.Produce
import org.example.greenometer.domain.Tag
import org.example.greenometer.dto.CreateProduceDto
import org.example.greenometer.dto.UpdateProduceDto
import org.example.greenometer.entities.ProduceEntity
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

    fun getProduceWithDetails(id: UUID): ProduceEntity? = produceRepository.findWithDetailsById(id)

    fun createProduce(dto: CreateProduceDto): ProduceEntity {
        val entity = ProduceEntity(
            name = dto.name,
            emoji = dto.emoji,
            category = dto.category?.name,
            color = dto.color?.name,
            description = dto.description?.takeIf { it.isNotBlank() },
        )
        return produceRepository.save(entity)
    }

    fun updateProduce(id: UUID, dto: UpdateProduceDto): ProduceEntity {
        val entity = produceRepository.findWithDetailsById(id)
            ?: throw NoSuchElementException("Produce not found: $id")

        entity.name = dto.name
        entity.emoji = dto.emoji
        entity.category = dto.category?.name
        entity.color = dto.color?.name
        entity.description = dto.description?.takeIf { it.isNotBlank() }

        // Handle tags
        entity.tags.clear()
        val tagNames = dto.tags?.split(",")?.map { it.trim().lowercase() }?.filter { it.isNotBlank() } ?: emptyList()
        val tagEntities = tagNames.map { tagName ->
            tagRepository.findTagEntityByTag(tagName)
                ?: tagRepository.save(TagEntity(tag = tagName))
        }
        entity.tags.addAll(tagEntities)

        return produceRepository.save(entity)
    }

    fun deleteProduce(id: UUID) {
        produceRepository.deleteById(id)
    }

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
