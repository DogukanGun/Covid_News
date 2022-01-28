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
import javax.inject.Inject
import javax.inject.Singleton

class CountryAdapter(var context:Context,val list:List<CountryEntity>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    var listener:CountryListener? = null

    inner class CountryViewHolder(val binding:ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindRow(item:CountryEntity){
            binding.apply {
                countryNameTV.text = item.name
                root.setOnClickListener {
                    listener?.itemClicked(item)
                    if (item.isSelected == 1){
                        binding.selectedRowIV.visibility = View.INVISIBLE
                        wrapper.background = null
                        item.isSelected = 0
                    }else{
                        binding.selectedRowIV.visibility = View.VISIBLE
                        wrapper.background = context.getDrawable(R.drawable.ri_recyclerview_item_background)
                        item.isSelected = 1
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