package org.example.greenometer.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "produce")
data class ProduceEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var emoji: String = "",
    var category: String? = null,
    var color: String? = null,
    var description: String? = null,
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "produce_tags",
        joinColumns = [JoinColumn(name = "produce_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")],
    )
    val tags: MutableSet<TagEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "produce", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val translations: MutableSet<ProduceTranslationEntity> = mutableSetOf(),
)
