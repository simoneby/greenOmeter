package org.example.greenometer.repository

import org.example.greenometer.entities.ProduceTranslationEntity
import org.example.greenometer.entities.ProduceTranslationId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProduceTranslationRepository : JpaRepository<ProduceTranslationEntity, ProduceTranslationId> {
    fun findByIdProduceId(produceId: UUID): List<ProduceTranslationEntity>
}
