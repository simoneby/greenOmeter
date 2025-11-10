package org.example.greenometer.repository

import org.example.greenometer.entities.LogEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LogRepository : JpaRepository<LogEntity, UUID>
