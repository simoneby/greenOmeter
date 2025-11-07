package org.example.greenometer.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val name: String,
    @Column(unique = true, nullable = false)
    val email: String,
)
