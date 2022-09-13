package com.shevy.data.repository

import com.shevy.data.storage.UserStorage
import com.shevy.data.storage.models.User
import com.shevy.domain.models.SaveUserNameParam
import com.shevy.domain.models.UserName
import com.shevy.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        val user = mapToStorage(saveParam)
        val result = userStorage.save(user)
        return result
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToDomain(user: UserName): UserName {
        return UserName(user.firstName, user.lastName)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(saveParam.name, "")
    }
}