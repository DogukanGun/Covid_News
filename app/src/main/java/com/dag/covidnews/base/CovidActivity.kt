package com.dag.covidnews.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.preference.PreferenceFragmentCompat
import com.dag.covidnews.BR
import com.dag.covidnews.R
import com.dag.covidnews.entity.intent.IntentParameter
import com.dag.covidnews.ui.settingspage.SettingsActivity


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