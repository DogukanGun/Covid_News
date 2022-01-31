package com.dag.covidnews.di

import android.content.Context
import com.dag.covidnews.network.ApiSource
import com.dag.covidnews.ui.homepage.HomepageFragmentVM
import com.dag.covidnews.ui.homepage.viewpager.ViewPagerVM
import com.dag.covidnews.ui.homepage.world.WorldStatusVM
import com.dag.covidnews.ui.onboard.country.CountryVM
import com.dag.covidnews.ui.onboard.story.StoryVM
import com.dag.covidnews.ui.settingspage.SettingsFragment
import com.dag.covidnews.ui.settingspage.SettingsFragmentVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentViewModelModule {

    @Provides
    fun provideStoryVM() = StoryVM()

    @Provides
    fun provideCountryVM(apiSource: ApiSource) = CountryVM(apiSource)

    @Provides
    fun provideHomepageFragment(context: Context) = HomepageFragmentVM(context)

    @Provides
    fun provideViewPageVM(apiSource: ApiSource) = ViewPagerVM(apiSource)

    @Provides
    fun provideWorldStatusVM(apiSource: ApiSource) = WorldStatusVM(apiSource)

    @Provides
    fun provideSettingsFragmentVM(context: Context) = SettingsFragmentVM(context)
}