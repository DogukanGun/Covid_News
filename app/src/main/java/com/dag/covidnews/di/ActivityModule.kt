package com.dag.covidnews.di

import com.dag.covidnews.network.ApiService
import com.dag.covidnews.network.ApiServiceImpl
import com.dag.covidnews.network.ApiSource
import com.dag.covidnews.ui.homepage.HomepageActivity
import com.dag.covidnews.ui.homepage.HomepageFragment
import com.dag.covidnews.ui.homepage.viewpager.ViewPagerFragment
import com.dag.covidnews.ui.onboard.MainActivity
import com.dag.covidnews.ui.onboard.country.CountryFragment
import com.dag.covidnews.ui.onboard.story.StoryFragment
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
    abstract fun bindsHomepageActivity(homepageActivity: HomepageActivity):HomepageActivity

    @Binds
    abstract fun bindsViewPagerFragment(viewPagerFragment: ViewPagerFragment):ViewPagerFragment

    @Binds
    abstract fun bindsHomepageFragment(homepageFragment: HomepageFragment):HomepageFragment

    @Binds
    abstract fun bindStoryFragment(storyFragment: StoryFragment):StoryFragment

    @Binds
    abstract fun bindCountryFragment(countryFragment: CountryFragment):CountryFragment

    @Binds
    abstract fun bindsApiServiceImpl(apiServiceImpl: ApiServiceImpl): ApiServiceImpl
}