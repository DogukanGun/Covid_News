package com.dag.covidnews.ui.homepage

import android.os.Bundle
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidActivity
import com.dag.covidnews.databinding.ActivityHomepageBinding
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.entity.intent.IntentParameter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomepageActivity : CovidActivity<HomepageActivityVM,ActivityHomepageBinding>(){
    override fun getVM(): HomepageActivityVM = homepageActivityVM

    override fun getLayoutId(): Int = R.layout.activity_homepage

    @Inject
    lateinit var homepageActivityVM: HomepageActivityVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentValue = intent.getStringExtra(IntentParameter.ARRAY.key)
        val list = intentValue?.split(",")?.map { it.replace(" ","") }
        list?.map { Country.values().get(it.toInt()) }?.let {
            val fragment = HomepageFragment()
            fragment.list = it
            addFragment(fragment)
        }
    }


}