package com.dag.covidnews.ui.onboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidActivity
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.ActivityMainBinding
import com.dag.covidnews.ui.homepage.HomepageActivity
import com.dag.covidnews.ui.onboard.story.StoryFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : CovidActivity<MainActivityVM,ActivityMainBinding>(){

    override fun getVM(): MainActivityVM = mainActivityVM

    override fun getLayoutId(): Int = R.layout.activity_main

    @Inject
    lateinit var mainActivityVM: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.getCountries()

    }

    override fun stateChange(state: CovidState) {
        when(state){
            MainActivityVS.StartActivity ->{
                startActivity(HomepageActivity::class.java)
            }
            MainActivityVS.StoryMode ->{
                addFragment(StoryFragment())
            }
        }
    }

}