package com.shevy.domain.usecase

import com.shevy.domain.models.UserName
import com.shevy.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {

        return userRepository.getName()
    }
}