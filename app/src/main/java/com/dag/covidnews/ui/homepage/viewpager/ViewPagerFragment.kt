package com.dag.covidnews.ui.homepage.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dag.covidnews.R
import com.dag.covidnews.base.CovidFragment
import com.dag.covidnews.base.CovidState
import com.dag.covidnews.databinding.FragmentViewPagerBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.ColorTemplate
import android.graphics.Color
import android.graphics.Typeface

@AndroidEntryPoint
@WithFragmentBindings
class ViewPagerFragment : CovidFragment<ViewPagerVM,FragmentViewPagerBinding>() {
    override fun getLayout(): Int = R.layout.fragment_view_pager

    override fun getVM(): ViewPagerVM = viewPagerVM

    @Inject
    lateinit var viewPagerVM: ViewPagerVM

    var countryName:String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)

        setupPieChart()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryName?.let {
            viewModel?.getList(it)
        }
    }


    private fun setupPieChart(){
        binding?.chart?.let {
            it.setExtraOffsets(5F, 20F, 5F, 20F)

            it.dragDecelerationFrictionCoef = 0.95f

            it.isDrawHoleEnabled = true
            it.setHoleColor(Color.WHITE)

            it.setTransparentCircleColor(Color.WHITE)
            it.setTransparentCircleAlpha(110)

            it.holeRadius = 58f
            it.transparentCircleRadius = 61f

            it.setDrawCenterText(true)

            it.rotationAngle = 0F
            it.isRotationEnabled = false
            it.isHighlightPerTapEnabled = true
        }

    }
    override fun onStateChange(state: CovidState) {
        when(state){
            is ViewPagerVS.SetViewPagerValues ->{
                binding?.chart?.let { chart->
                    val spaceForBar = 10f
                    val list = state.response
                    val pieChartList = arrayListOf<PieEntry>()
                    list.deaths.total.toFloat().apply {
                        val pieEntryConfirmed = PieEntry(this,spaceForBar)
                        pieEntryConfirmed.label = getString(R.string.deaths)
                        pieChartList.add(pieEntryConfirmed)
                    }
                    list.cases.total.toFloat().apply {
                        val pieEntryConfirmed = PieEntry(this,spaceForBar)
                        pieEntryConfirmed.label = getString(R.string.recovered)
                        pieChartList.add(pieEntryConfirmed)
                    }

                    val dataset = PieDataSet(pieChartList,list.country)
                    dataset.sliceSpace = 30f
                    dataset.iconsOffset = MPPointF(0F, 40F)
                    dataset.selectionShift = 50f

                    val colors: ArrayList<Int> = ArrayList()

                    for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

                    for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

                    for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

                    for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

                    for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

                    colors.add(ColorTemplate.getHoloBlue())

                    dataset.colors = colors
                    val data = PieData(dataset)
                    data.setValueTypeface(Typeface.MONOSPACE)
                    data.setValueTextSize(11f)
                    data.setValueTextColor(Color.BLACK)
                    chart.data = data
                    chart.setEntryLabelColor(Color.BLACK)
                    chart.notifyDataSetChanged()
                    chart.invalidate()
                }
                binding?.titleTV?.text = state.response.country.uppercase()
            }
        }
    }

    override fun hasSettingsButton(): Boolean = true

}