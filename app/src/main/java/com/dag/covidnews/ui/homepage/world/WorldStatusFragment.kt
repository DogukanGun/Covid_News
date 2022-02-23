package com.dag.covidnews.ui.homepage.world

import android.R.attr
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.FragmentWorldStatusBinding
import com.dag.covidnews.entity.country.CountryInformation
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject
import com.github.mikephil.charting.components.YAxis

import com.github.mikephil.charting.components.XAxis.XAxisPosition

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter

import android.R.attr.data
import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dag.covidnews.base.AppConstant
import com.dag.covidnews.entity.country.Country

@AndroidEntryPoint
@WithFragmentBindings
class WorldStatusFragment : CovidFragment<WorldStatusVM,FragmentWorldStatusBinding>() {
    override fun getLayout(): Int = R.layout.fragment_world_status

    override fun getVM(): WorldStatusVM = worldStatusVM

    @Inject
    lateinit var worldStatusVM: WorldStatusVM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        setBarChart()
        viewModel?.getWorldData("")
        setSpinner()
        return view
    }

    override fun onStateChange(state: CovidState) {
        when(state){
            is WorldStatusVS.SetWordStatus ->{
                val data = state.countryInformation
                setData(data)
            }
        }
    }

    private fun setSpinner(){
        val prefs = context?.getSharedPreferences(AppConstant.PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val valueByNumber = prefs?.getString(AppConstant.PREFS_COUNTRY_KEY,"")?.split(",")
        val value = mutableListOf<String>()
        valueByNumber?.forEach {
           value.add(Country.values()[it.replace(" ","").toInt()].name)
        }
        binding?.countries?.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_expandable_list_item_1,value.toList())
        binding?.countries?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel?.getWorldData(p0?.getItemAtPosition(p2) as String)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel?.getWorldData(value[0])
            }
        }
    }

    private fun setData(countryInformation: CountryInformation){
        val entries1 = arrayListOf<BarEntry>()
        countryInformation.cases.total.toFloat().apply {
            val element = BarEntry(1F,this)
            entries1.add(element)
        }
        val barDataSet1 = BarDataSet(entries1,getString(R.string.confirmed))
        barDataSet1.color = Color.RED

        val entries2 = arrayListOf<BarEntry>()

        countryInformation.deaths.total.toFloat().apply {
            val element = BarEntry(10F,this)
            entries2.add(element)
        }
        val barDataSet2 = BarDataSet(entries2,getString(R.string.deaths))
        barDataSet2.color = Color.BLACK

        val entries3 = arrayListOf<BarEntry>()

        countryInformation.tests.total.toFloat().apply {
            val element = BarEntry(20F,this)
            entries3.add(element)
        }
        val barDataSet3 = BarDataSet(entries3,getString(R.string.recovered))
        barDataSet3.color = Color.YELLOW


        val barData = BarData(barDataSet1,barDataSet2,barDataSet3)
        barData.setValueTextSize(10f)
        barData.setValueTypeface(Typeface.MONOSPACE)
        barData.setValueFormatter(LargeValueFormatter())

        barData.barWidth = 9F
        binding?.chart?.apply {
            data = barData
            notifyDataSetChanged()
            invalidate()
        }

    }

    private fun setBarChart(){
        binding?.chart?.let { chart ->
            chart.setDrawBarShadow(false)
            chart.setDrawValueAboveBar(true)
            chart.description.isEnabled = false
            chart.setMaxVisibleValueCount(60)
            chart.setPinchZoom(false)
            chart.setDrawGridBackground(false)
            chart.isHighlightPerTapEnabled = false

            val xl = chart.xAxis
            xl.position = XAxisPosition.BOTTOM
            xl.typeface = Typeface.MONOSPACE
            xl.setDrawAxisLine(true)
            xl.setDrawGridLines(false)
            xl.granularity = 10f

            val yl = chart.axisLeft
            yl.typeface = Typeface.MONOSPACE
            yl.setDrawAxisLine(true)
            yl.setDrawGridLines(true)
            yl.axisMinimum = 0f

            val yr = chart.axisRight
            yr.typeface = Typeface.MONOSPACE
            yr.setDrawAxisLine(true)
            yr.setDrawGridLines(false)
            yr.axisMinimum = 0f
            chart.setFitBars(true)
            chart.animateY(2500)
            chart.axisRight.isEnabled = false

        }
    }

    override fun hasSettingsButton(): Boolean = true

}