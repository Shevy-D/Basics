package com.shevy.basics.di

import android.content.Context
import com.shevy.data.repository.UserRepositoryImpl
import com.shevy.data.storage.UserStorage
import com.shevy.data.storage.sharedprefs.SharedPrefUserStorage
import com.shevy.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideUserStorage(context: Context): UserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    fun provideUserRepository(userStorage: UserStorage): UserRepository{
        return UserRepositoryImpl(userStorage = userStorage)
    }
}
