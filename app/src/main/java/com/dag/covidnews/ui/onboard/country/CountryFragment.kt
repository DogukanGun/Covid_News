package com.dag.covidnews.ui.onboard.country

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.covidnews.R
import com.dag.covidnews.base.AppConstant
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.FragmentCountryBinding
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryWrapper
import com.dag.covidnews.entity.intent.IntentParameter
import com.dag.covidnews.ui.homepage.HomepageActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@AndroidEntryPoint
@WithFragmentBindings
class CountryFragment : CovidFragment<CountryVM,FragmentCountryBinding>() {

    override fun getLayout(): Int = R.layout.fragment_country

    override fun getVM(): CountryVM = countryViewModel

    @Inject
    lateinit var countryViewModel:CountryVM

    lateinit var adapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel?.getCountries()
        return view
    }

    override fun onStateChange(state: CovidState) {
        when(state){
            is CountryVS.SetCountries ->{
                val list = state.list
                val adapter = CountryAdapter(requireContext(),list)
                adapter.listener = itemListener
                this.adapter = adapter
                binding?.listOfCountryRV?.apply {
                    this.adapter = adapter
                    this.layoutManager = LinearLayoutManager(requireContext())
                }
            }
            is CountryVS.SortList ->{
                val list = adapter.countryList.filter { country->
                    country.isSelected == 1
                }
                val countryIdList = Country.values().filter { country ->
                    list.map { it.name.lowercase() }.contains(country.name.lowercase())
                }.map { it.id }
                val prefs = context?.getSharedPreferences(AppConstant.PREFS_FILE_NAME,Context.MODE_PRIVATE)
                val editor = prefs?.edit()
                editor?.putString(AppConstant.PREFS_COUNTRY_KEY,countryIdList.joinToString())
                editor?.apply()
                startActivityWithValue(HomepageActivity::class.java,countryIdList.joinToString(),
                    IntentParameter.ARRAY)
            }
        }
    }

    private val itemListener = object : CountryListener{
        override fun itemClicked(item: CountryWrapper) {
        }
    }
}