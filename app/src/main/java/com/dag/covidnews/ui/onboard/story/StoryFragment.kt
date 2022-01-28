package com.dag.covidnews.ui.onboard.story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.FragmentStoryBinding
import com.dag.covidnews.ui.onboard.country.CountryFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject


@AndroidEntryPoint
@WithFragmentBindings
class StoryFragment : CovidFragment<StoryVM,FragmentStoryBinding>() {

    override fun getLayout(): Int = R.layout.fragment_story

    override fun getVM(): StoryVM = storyViewModel

    @Inject
    lateinit var storyViewModel:StoryVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onStateChange(state: CovidState) {
        when(state){
            is StoryVS.NextPage->{
                addFragment(CountryFragment())
            }
        }
    }

}