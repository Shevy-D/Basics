package com.shevy.basics.domain.usecase

import com.shevy.basics.domain.models.UserName
import com.shevy.basics.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {
        return userRepository.getName()
    }
}