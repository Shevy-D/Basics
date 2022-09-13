package com.shevy.data.storage

import com.shevy.data.storage.models.User
import com.shevy.domain.models.UserName

interface UserStorage {

    fun save(user: User): Boolean

    fun get() : UserName
}