package com.shevy.basics.di

import android.content.Context
import com.shevy.basics.presentation.MainViewModelFactory
import com.shevy.domain.usecase.GetUserNameUseCase
import com.shevy.domain.usecase.SaveUserNameUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext() : Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(
        getUserNameUseCase: GetUserNameUseCase,
        saveUserNameUseCase: SaveUserNameUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )
    }

}