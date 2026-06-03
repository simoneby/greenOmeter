package org.example.greenometer.service

import org.example.greenometer.dto.TranslationDto
import org.example.greenometer.entities.ProduceTranslationEntity
import org.example.greenometer.entities.ProduceTranslationId
import org.example.greenometer.repository.ProduceTranslationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProduceTranslationService(
    private val translationRepository: ProduceTranslationRepository,
) {
    fun getTranslations(produceId: UUID) =
        translationRepository.findByIdProduceId(produceId)

    fun saveTranslation(produceId: UUID, dto: TranslationDto) {
        val id = ProduceTranslationId(produceId = produceId, language = dto.language)
        val entity = ProduceTranslationEntity(id = id, name = dto.name)
        translationRepository.save(entity)
    }

    fun deleteTranslation(produceId: UUID, language: String) {
        val id = ProduceTranslationId(produceId = produceId, language = language)
        translationRepository.deleteById(id)
    }
}
