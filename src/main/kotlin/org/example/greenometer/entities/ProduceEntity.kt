package org.example.greenometer.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "produce")
data class ProduceEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val emoji: String,
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "produce_tags",
        joinColumns = [JoinColumn(name = "produce_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")],
    )
    val tags: MutableSet<TagEntity> = mutableSetOf(),
)
