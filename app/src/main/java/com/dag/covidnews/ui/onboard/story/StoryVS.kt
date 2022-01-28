package com.dag.covidnews.ui.onboard.story

import com.dag.covidnews.base.CovidState

sealed class StoryVS:CovidState {
    object NextPage:StoryVS()
}