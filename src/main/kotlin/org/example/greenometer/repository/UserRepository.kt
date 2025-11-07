package org.example.greenometer.repository

import org.example.greenometer.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, UUID>
