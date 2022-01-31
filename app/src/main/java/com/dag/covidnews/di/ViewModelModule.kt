package com.dag.covidnews.di

import android.content.Context
import com.dag.covidnews.ui.homepage.HomepageActivityVM
import com.dag.covidnews.ui.onboard.MainActivityVM
import com.dag.covidnews.ui.settingspage.SettingsActivity
import com.dag.covidnews.ui.settingspage.SettingsVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainActivityVM(context: Context) = MainActivityVM(context)

    @Provides
    fun homepageActivityVM() = HomepageActivityVM()

    @Provides
    fun settingsVM() = SettingsVM()

}