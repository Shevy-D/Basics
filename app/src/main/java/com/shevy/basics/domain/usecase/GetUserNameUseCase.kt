package com.shevy.basics.domain.usecase

import com.shevy.basics.domain.models.UserName

class GetUserNameUseCase {

    fun execute(): UserName {
        return UserName("Jogo", " Bonito")
    }
}