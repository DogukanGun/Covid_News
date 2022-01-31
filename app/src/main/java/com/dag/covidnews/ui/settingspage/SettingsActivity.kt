package com.dag.covidnews.ui.settingspage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidActivity
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : CovidActivity<CovidVM,ActivitySettingsBinding>() {


    override fun getVM(): CovidVM = settingsVM

    override fun getLayoutId(): Int = R.layout.activity_settings

    @Inject
    lateinit var settingsVM: SettingsVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(SettingsFragment())
    }



}