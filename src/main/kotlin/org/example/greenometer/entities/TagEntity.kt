package org.example.greenometer.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tags")
data class TagEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(unique = true, nullable = false, columnDefinition = "CITEXT")
    val tag: String,
)
