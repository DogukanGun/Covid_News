package com.dag.covidnews.di

import androidx.viewpager.widget.ViewPager
import com.dag.covidnews.network.ApiSource
import com.dag.covidnews.ui.homepage.HomepageFragment
import com.dag.covidnews.ui.homepage.HomepageFragmentVM
import com.dag.covidnews.ui.homepage.viewpager.ViewPagerVM
import com.dag.covidnews.ui.onboard.country.CountryVM
import com.dag.covidnews.ui.onboard.story.StoryVM
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
    fun provideHomepageFragment() = HomepageFragmentVM()

    @Provides
    fun provideViewPageVM(apiSource: ApiSource) = ViewPagerVM(apiSource)
}