package com.android.bjapplication.di.component

import android.app.Application
import com.android.bjapplication.AppController
import com.android.bjapplication.di.module.DataSourceModule
import com.android.bjapplication.di.module.FragmentModule
import com.android.bjapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        FragmentModule::class,
        DataSourceModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<AppController> {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Application): AppComponent

    }
}