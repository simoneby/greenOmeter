package org.example.greenometer.service

import org.example.greenometer.domain.User
import org.example.greenometer.dto.ProduceLogDto
import org.example.greenometer.entities.LogEntity
import org.example.greenometer.repository.LogRepository
import org.example.greenometer.repository.ProduceRepository
import org.example.greenometer.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class UserService(
    val userRepository: UserRepository,
    val produceRepository: ProduceRepository,
    val logRepository: LogRepository,
) {
    fun getUser(id: String): User = userRepository.findById(UUID.fromString(id)).let { User.fromEntity(it.get()) }

    fun logProduce(
        userId: String,
        produceLogDto: ProduceLogDto,
    ): String {
        val userEntity = userRepository.findById(UUID.fromString(userId)).get()
        val produceEntity = produceRepository.findById(UUID.fromString(produceLogDto.produceId)).get()

        val logEntity =
            LogEntity(
                userEntity = userEntity,
                quantity = produceLogDto.quantity,
                produceEntity = produceEntity,
                entryDate = produceLogDto.date ?: LocalDate.now(),
            )

        logRepository.save(logEntity)
        return "Success"
    }
}
