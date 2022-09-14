package com.shevy.basics.app

import android.app.Application
import com.shevy.basics.di.AppComponent
import com.shevy.basics.di.AppModule
import com.shevy.basics.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}