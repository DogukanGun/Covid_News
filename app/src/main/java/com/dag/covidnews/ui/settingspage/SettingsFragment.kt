package com.dag.covidnews.ui.settingspage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.FragmentSettingsBinding
import com.dag.covidnews.entity.country.Country
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject





@AndroidEntryPoint
@WithFragmentBindings
class SettingsFragment : CovidFragment<SettingsFragmentVM,FragmentSettingsBinding>(){

    @Inject
    lateinit var settingsFragmentVM: SettingsFragmentVM

    override fun getLayout(): Int = R.layout.fragment_settings

    override fun getVM(): SettingsFragmentVM = settingsFragmentVM

    override fun hasBackButton(): Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)

        binding?.apply {
            turkishButtonBTN.setOnClickListener(turkishButtonListener)
            englishButtonBTN.setOnClickListener(englishButtonListener)
        }
        viewModel?.getList()
        return view
    }

    override fun onStateChange(state: CovidState) {
        when(state){
            is SettingsFragmentVS.GetCountries ->{
                val list = state.list
                for(index in list){
                    setCountry(index)
                }
            }
        }
    }

    private fun setCountry(index:Country){
        when(index){
            Country.USA ->{
                binding?.chipUsaC?.isChecked = true
            }
            Country.TURKEY ->{
                binding?.chipTurkeyC?.isChecked = true
            }
            Country.SWEDEN ->{
                binding?.chipSwedenC?.isChecked = true
            }
            Country.SPAIN ->{
                binding?.chipSpainC?.isChecked = true
            }
            Country.NORWAY ->{
                binding?.chipNorwayC?.isChecked = true
            }
            Country.ITALY ->{
                binding?.chipItalyC?.isChecked = true
            }
            Country.GERMANY ->{
                binding?.chipGermanyC?.isChecked = true
            }
            Country.FRANCE ->{
                binding?.chipFranceC?.isChecked = true
            }
        }
    }
    private val englishButtonListener =
        View.OnClickListener {
            binding?.radioGroup?.background = ContextCompat.getDrawable(requireActivity(),
                R.drawable.bg_radio_group_selected)
            binding?.turkishButtonBTN?.background =
                ContextCompat.getDrawable(requireActivity(),R.drawable.bg_radio_button_left_button_not_selected)

        }


    private val turkishButtonListener =
        View.OnClickListener {
            binding?.turkishButtonBTN?.background =
            ContextCompat.getDrawable(requireActivity(),R.drawable.bg_radio_button_left_button_selected)

            binding?.radioGroup?.background = ContextCompat.getDrawable(requireActivity(),
            R.drawable.bg_radio_group_not_selected)
        }
}
