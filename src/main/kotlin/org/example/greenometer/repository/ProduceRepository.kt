package org.example.greenometer.repository

import org.example.greenometer.entities.ProduceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProduceRepository : JpaRepository<ProduceEntity, UUID>
