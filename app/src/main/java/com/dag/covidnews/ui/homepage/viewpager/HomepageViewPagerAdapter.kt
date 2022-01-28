package com.dag.covidnews.ui.homepage.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dag.covidnews.base.CovidActivity
import com.dag.covidnews.entity.country.Country

class HomepageViewPagerAdapter(activity:CovidActivity<*,*>,val list:List<Country>):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = list.count()

    override fun createFragment(position: Int): Fragment {
        val element = list[position]
        val fragment = ViewPagerFragment()
        fragment.countryName = element.name
        return fragment
    }
}