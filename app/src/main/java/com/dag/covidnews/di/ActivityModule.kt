package com.dag.covidnews.di

import com.dag.covidnews.network.ApiService
import com.dag.covidnews.network.ApiServiceImpl
import com.dag.covidnews.network.ApiSource
import com.dag.covidnews.ui.onboard.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindsMainActivity(mainActivity: MainActivity):MainActivity

    @Binds
    abstract fun bindsApiServiceImpl(apiServiceImpl: ApiServiceImpl): ApiServiceImpl
}