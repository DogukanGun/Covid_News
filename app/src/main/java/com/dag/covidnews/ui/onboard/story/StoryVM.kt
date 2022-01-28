package com.dag.covidnews.ui.onboard.story

import com.dag.covidnews.base.CovidVM

class StoryVM:CovidVM() {

    fun buttonPressed(){
        state.value = StoryVS.NextPage
    }
}