package com.dag.covidnews.base

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.dag.covidnews.BR
import com.dag.covidnews.R
import com.dag.covidnews.entity.intent.IntentParameter
import com.dag.covidnews.ui.settingspage.SettingsActivity
import java.util.*


abstract class CovidActivity<VM:CovidVM,DB:ViewDataBinding>: AppCompatActivity() {

    abstract fun getVM():VM

    abstract fun getLayoutId():Int

    open fun hasBackButton() = false

    open fun hasSettingsButton() = false

    private fun getContainer() = R.id.container

    protected var viewModel:VM? = null
    protected var binding:DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
        binding = DataBindingUtil.setContentView(this,getLayoutId())
        binding!!.setVariable(BR.viewModel,viewModel)
        (viewModel as CovidVM).let { vm->
            if (!vm.state.hasActiveObservers()){
                vm.state.observe(this, Observer {
                    stateChange(it)
                })
            }
        }
    }

    private fun createAppBar(cancelButtonState:Boolean,settingButtonState:Boolean){
        findViewById<Toolbar?>(R.id.action_bar)?.let {
            val cancelButton = findViewById<ImageButton>(R.id.cancelIBTN)
            val settingButton = findViewById<ImageButton>(R.id.settingIMB)
            if (!cancelButtonState){
                cancelButton.visibility = View.INVISIBLE
            }else{
                cancelButton.visibility = View.VISIBLE
                cancelButton.setOnClickListener(cancelButtonListener)
            }
            if (!settingButtonState){
                settingButton.visibility = View.INVISIBLE
            }else{
                settingButton.visibility = View.VISIBLE
                settingButton.setOnClickListener(settingButtonListener)
            }
        }
    }

    private val cancelButtonListener = View.OnClickListener {
        finish()
    }

    private val settingButtonListener = View.OnClickListener {
        startActivity(SettingsActivity::class.java)
    }

    fun setAppBar(cancelButtonState:Boolean,settingButtonState:Boolean){
        createAppBar(cancelButtonState,settingButtonState)
    }

    fun setLocale(context: Context, language: String): Context? {
        persist(context, language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
    }

    private fun persist(context: Context, language: String) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(AppConstant.SELECTED_LANGUAGE, language)
        editor.apply()
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }


    private fun updateResourcesLegacy(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.getConfiguration()
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        return context
    }

    fun startActivity(classAI:Class<*>){
        val intent = Intent(this,classAI)
        startActivity(intent)
    }

    private fun getCurrentFragment():CovidFragment<*,*>?{
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment != null){
            return if (currentFragment is CovidFragment<*, *>){
                currentFragment as CovidFragment<*, *>
            }else{
                finish()
                null
            }

        }
        return null
    }

    override fun onBackPressed() {
        val result = back()
        if (result == -1){
            super.onBackPressed()
        }
    }

    private fun back():Int{
        val currentFragment = getCurrentFragment()
        if (currentFragment != null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.remove(currentFragment)
            fragmentTransaction.commitAllowingStateLoss()
            supportFragmentManager.popBackStackImmediate()
            if (supportFragmentManager.backStackEntryCount == 0) {
                return -1
            }
            return 0
        }else{
            return -1
        }

    }

    fun startActivityWithValue(classAI: Class<*>,variable:String,parameter:IntentParameter){
        val intent = Intent(this,classAI)
        intent.putExtra(parameter.key,variable)
        startActivity(intent)
    }

    fun addFragment(fragment:CovidFragment<*,*>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(getContainer(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun addSettingFragment(fragment: PreferenceFragmentCompat){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(getContainer(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun replaceFragment(fragment:CovidFragment<*,*>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(getContainer(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    open fun stateChange(state:CovidState){

    }
}