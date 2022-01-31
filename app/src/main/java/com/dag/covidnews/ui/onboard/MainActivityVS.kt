package com.dag.covidnews.ui.onboard

import com.dag.covidnews.base.CovidState

sealed class MainActivityVS:CovidState {
    object StoryMode:MainActivityVS()
    object StartActivity:MainActivityVS()
}