package com.shevy.domain.repository

import com.shevy.domain.models.SaveUserNameParam
import com.shevy.domain.models.UserName

interface UserRepository {

    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): UserName
}