package com.dag.covidnews.ui.onboard.country

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dag.covidnews.R
import com.dag.covidnews.databinding.ItemCountryBinding
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryWrapper
import javax.inject.Inject
import javax.inject.Singleton

class CountryAdapter(var context:Context,val list:List<String>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    var listener:CountryListener? = null
    var countryList = mutableListOf<CountryWrapper>()

    inner class CountryViewHolder(val binding:ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindRow(item:String){
            binding.apply {
                countryNameTV.text = item
                val country = CountryWrapper(item,0)
                countryList.add(country)
                root.setOnClickListener {
                    listener?.itemClicked(country)
                    if (country.isSelected == 1){
                        binding.selectedRowIV.visibility = View.INVISIBLE
                        wrapper.background = null
                        country.isSelected = 0
                    }else{
                        binding.selectedRowIV.visibility = View.VISIBLE
                        wrapper.background = context.getDrawable(R.drawable.ri_recyclerview_item_background)
                        country.isSelected = 1
                    }
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindRow(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context))
        return CountryViewHolder(binding)
    }

}