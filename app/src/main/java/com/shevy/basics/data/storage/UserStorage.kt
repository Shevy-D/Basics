package com.shevy.basics.data.storage

import com.shevy.basics.data.storage.models.User
import com.shevy.basics.domain.models.UserName

interface UserStorage {

    fun save(user: User): Boolean

    fun get() : User
}