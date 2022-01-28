package com.dag.covidnews.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidActivity
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.databinding.FragmentHomepageBinding
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.ui.homepage.viewpager.HomepageViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@AndroidEntryPoint
@WithFragmentBindings
class HomepageFragment : CovidFragment<HomepageFragmentVM,FragmentHomepageBinding>() {
    override fun getLayout(): Int = R.layout.fragment_homepage

    override fun getVM(): HomepageFragmentVM = homepageFragmentVM

    lateinit var list: List<Country>

    @Inject
    lateinit var homepageFragmentVM: HomepageFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val adapter = HomepageViewPagerAdapter((activity as CovidActivity<*, *>),list)
        binding?.viewPagerVP?.adapter = adapter
        return view
    }
}