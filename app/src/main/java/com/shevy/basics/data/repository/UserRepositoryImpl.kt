package com.shevy.basics.data.repository

import com.shevy.basics.domain.models.SaveUserNameParam
import com.shevy.basics.domain.models.UserName
import com.shevy.basics.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        return true
    }

    override fun getName(): UserName {
        return UserName("Shevy", "Dmitriy")
    }
}