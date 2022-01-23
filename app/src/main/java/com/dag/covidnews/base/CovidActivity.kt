package com.dag.covidnews.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dag.covidnews.R


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
        (viewModel as CovidVM).let { vm->
            if (!vm.state.hasActiveObservers()){
                vm.state.observe(this, Observer {
                    stateChange(it)
                })
            }
        }
    }

    open fun stateChange(state:CovidState){

    }
}