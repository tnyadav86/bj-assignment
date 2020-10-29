package com.android.bjapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.bjapplication.di.ViewModelKey
import com.android.bjapplication.viewmodel.AllNewsFragmentViewModel
import com.android.bjapplication.viewmodel.SourceFragmentViewModel
import com.android.bjapplication.di.factory.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SourceFragmentViewModel::class)
    abstract fun bindSourceFragmentViewModel(viewmodel: SourceFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllNewsFragmentViewModel::class)
    abstract fun bindAllNewsFragmentViewModel(viewmodel: AllNewsFragmentViewModel): ViewModel

}