package org.example.greenometer.entities

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Embeddable
data class ProduceTranslationId(
    @Column(name = "produce_id")
    val produceId: UUID = UUID.randomUUID(),
    val language: String = "",
) : Serializable

@Entity
@Table(name = "produce_translations")
data class ProduceTranslationEntity(
    @EmbeddedId
    val id: ProduceTranslationId = ProduceTranslationId(),

    val name: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produce_id", insertable = false, updatable = false)
    val produce: ProduceEntity? = null,
)
