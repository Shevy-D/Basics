package com.shevy.basics.domain.usecase

import com.shevy.basics.data.repository.UserRepositoryImpl
import com.shevy.basics.domain.models.SaveUserNameParam
import com.shevy.basics.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {
        val result: Boolean = userRepository.saveName(param)
        return result
    }
}