package com.shevy.basics.di

import com.shevy.data.repository.UserRepositoryImpl
import com.shevy.data.storage.UserStorage
import com.shevy.data.storage.sharedprefs.SharedPrefUserStorage
import com.shevy.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage> {
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }

}
