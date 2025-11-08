package org.example.greenometer.service

import org.example.greenometer.domain.User
import org.example.greenometer.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    val userRepository: UserRepository,
) {
    fun getUser(id: String): User = userRepository.findById(UUID.fromString(id)).let { User.fromEntity(it.get()) }
}
