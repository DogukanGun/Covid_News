package com.dag.covidnews.di

import com.dag.covidnews.ui.homepage.HomepageActivityVM
import com.dag.covidnews.ui.onboard.MainActivityVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainActivityVM() = MainActivityVM()

    @Provides
    fun homepageActivityVM() = HomepageActivityVM()

}