package com.dag.covidnews.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dag.covidnews.BR
import com.dag.covidnews.entity.intent.IntentParameter

abstract class CovidFragment<VM:CovidVM,DB:ViewDataBinding>:Fragment() {

    abstract fun getLayout():Int

    abstract fun getVM():VM

    protected var viewModel:VM? = null
    protected var binding:DB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getLayout(),container,false)
        binding!!.setVariable(BR.viewModel,viewModel)
        (viewModel as CovidVM).let {  vm->
                if(!vm.state.hasActiveObservers()){
                    vm.state.observe(viewLifecycleOwner,{
                        onStateChange(vm.state.value!!)
                    })
                }

        }
        return binding!!.root

    }

    fun startActivityWithValue(classAI: Class<*>,variable:String,parameter: IntentParameter){
        (activity as CovidActivity<*,*>).startActivityWithValue(classAI,variable,parameter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    open fun onStateChange(state:CovidState){

    }

    fun addFragment(fragment:CovidFragment<*,*>){
        (activity as CovidActivity<*,*>).addFragment(fragment)
    }

    fun startActivity(classAI:Class<*>){
        (activity as CovidActivity<*,*>).startActivity(classAI)
    }
}