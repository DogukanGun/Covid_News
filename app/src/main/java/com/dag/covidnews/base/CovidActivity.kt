package com.dag.covidnews.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dag.covidnews.BR
import com.dag.covidnews.R
import com.dag.covidnews.entity.intent.IntentParameter


abstract class CovidActivity<VM:CovidVM,DB:ViewDataBinding>: AppCompatActivity() {

    abstract fun getVM():VM

    abstract fun getLayoutId():Int

    fun getContainer() = R.id.container

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

    fun startActivity(classAI:Class<*>){
        val intent = Intent(this,classAI)
        startActivity(intent)
    }

    fun getCurrentFragment():CovidFragment<*,*>?{
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment != null){
            return  currentFragment as CovidFragment<*, *>
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

    open fun stateChange(state:CovidState){

    }
}