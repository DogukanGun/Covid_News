package com.dag.covidnews.ui.homepage

import android.os.Bundle
import android.view.MenuItem
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidActivity
import com.dag.covidnews.databinding.ActivityHomepageBinding
import com.dag.covidnews.entity.bottomnavigation.BottomMenu
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.entity.intent.IntentParameter
import com.dag.covidnews.ui.homepage.world.WorldStatusFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomepageActivity : CovidActivity<HomepageActivityVM,ActivityHomepageBinding>(){
    override fun getVM(): HomepageActivityVM = homepageActivityVM
    override fun getLayoutId(): Int = R.layout.activity_homepage

    @Inject
    lateinit var homepageActivityVM: HomepageActivityVM

    var currentBottomMenu = BottomMenu.HOMEPAGE
    var list:List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentValue = intent.getStringExtra(IntentParameter.ARRAY.key)
        list = intentValue?.split(",")?.map { it.replace(" ","") }
        binding?.bottomNavigationView?.setOnItemSelectedListener(bottomNavigationListener)
        if (currentBottomMenu == BottomMenu.HOMEPAGE){
            startHomepage()
        }else if (currentBottomMenu == BottomMenu.WORLDSTATUS){
            startWorldStatus()
        }
    }

    val bottomNavigationListener =
        NavigationBarView.OnItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeButton ->{
                    if (currentBottomMenu != BottomMenu.HOMEPAGE){
                        startHomepage()
                        currentBottomMenu = BottomMenu.HOMEPAGE
                    }
                    true
                }
                R.id.worldButton ->{
                    if (currentBottomMenu != BottomMenu.WORLDSTATUS){
                        startWorldStatus()
                        currentBottomMenu = BottomMenu.WORLDSTATUS
                    }
                    true
                }
                else ->{
                    false
                }
            }

        }

    private fun startHomepage(){
        val fragment = HomepageFragment()
        list?.map { Country.values()[it.toInt()] }?.let {
            fragment.list = it.toMutableList()
        }
        replaceFragment(fragment)
    }
    private fun startWorldStatus(){
        replaceFragment(WorldStatusFragment())
    }

    override fun hasBackButton(): Boolean = false

    override fun hasSettingsButton(): Boolean = true

}