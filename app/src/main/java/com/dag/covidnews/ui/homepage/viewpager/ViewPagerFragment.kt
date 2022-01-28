package com.dag.covidnews.ui.homepage.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject


@AndroidEntryPoint
@WithFragmentBindings
class ViewPagerFragment : CovidFragment<ViewPagerVM,FragmentViewPagerBinding>() {
    override fun getLayout(): Int = R.layout.fragment_view_pager

    override fun getVM(): ViewPagerVM = viewPagerVM

    @Inject
    lateinit var viewPagerVM: ViewPagerVM

    var countryName:String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        countryName?.let {
            viewModel?.getList(it)
        }
        return view
    }


    override fun onStateChange(state: CovidState) {
        when(state){
            is ViewPagerVS.SetViewPagerValues ->{
                print(state.response)
            }
        }
    }

}