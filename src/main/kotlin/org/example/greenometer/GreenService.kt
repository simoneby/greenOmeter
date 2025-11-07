package org.example.greenometer

import org.example.greenometer.domain.Produce
import org.example.greenometer.domain.User
import org.example.greenometer.entities.TagEntity
import org.example.greenometer.repository.ProduceRepository
import org.example.greenometer.repository.TagRepository
import org.example.greenometer.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GreenService(
    val userRepository: UserRepository,
    val produceRepository: ProduceRepository,
    val tagRepository: TagRepository,
) {
    fun getUser(id: String): User = userRepository.findById(UUID.fromString(id)).let { User.fromEntity(it.get()) }

    fun getAllProduce(): List<Produce> {
        val produceEntities = produceRepository.findAll()
        return produceEntities.map { Produce.fromEntity(it) }
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
}
