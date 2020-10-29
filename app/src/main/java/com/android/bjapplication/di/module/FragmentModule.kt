package com.android.bjapplication.di.module

import com.android.bjapplication.ui.AllNewsFragment
import com.android.bjapplication.ui.SourceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun sourceFragment(): SourceFragment

    @ContributesAndroidInjector
    abstract fun allNewsFragment(): AllNewsFragment

}