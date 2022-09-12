package com.shevy.basics.domain.repository

import com.shevy.basics.domain.models.SaveUserNameParam
import com.shevy.basics.domain.models.UserName

interface UserRepository {

    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): UserName
}