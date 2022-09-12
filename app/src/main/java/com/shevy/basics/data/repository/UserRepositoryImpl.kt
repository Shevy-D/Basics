package com.shevy.basics.data.repository

import com.shevy.basics.data.storage.models.User
import com.shevy.basics.data.storage.UserStorage
import com.shevy.basics.domain.models.SaveUserNameParam
import com.shevy.basics.domain.models.UserName
import com.shevy.basics.domain.repository.UserRepository

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

    private fun mapToDomain(user: User): UserName {
        return UserName(user.firstName, user.lastName)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(saveParam.name, "")
    }
}