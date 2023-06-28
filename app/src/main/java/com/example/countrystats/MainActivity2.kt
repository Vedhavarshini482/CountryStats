package com.example.countrystats

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity2 : ComponentActivity() {

    lateinit var barChart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        barChart = findViewById(R.id.bar_chart)
       val names= intent.getStringArrayListExtra("list1")
        val populations=intent.getLongArrayExtra("list2")
        if(names!=null&&populations!=null)
            setBarChart(names=names,populations=populations.asList())

    }

    fun setBarChart(names: List<String>, populations: List<Long>) {

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f,populations[0].toFloat()))
        entries.add(BarEntry(1f,populations[1].toFloat()))
        entries.add(BarEntry(2f,populations[2].toFloat()))
        entries.add(BarEntry(3f,populations[3].toFloat()))
        entries.add(BarEntry(4f,populations[4].toFloat()))
        entries.add(BarEntry(5f,populations[5].toFloat()))
        entries.add(BarEntry(6f,populations[6].toFloat()))
        entries.add(BarEntry(7f,populations[7].toFloat()))
        entries.add(BarEntry(8f,populations[8].toFloat()))
        entries.add(BarEntry(9f,populations[9].toFloat()))


        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add(names[0])
        labels.add(names[1])
        labels.add(names[2])
        labels.add(names[3])
        labels.add(names[4])
        labels.add(names[5])
        labels.add(names[6])
        labels.add(names[7])
        labels.add(names[8])
        labels.add(names[9])


        barDataSet.valueTextColor= Color.BLACK
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        val barData=BarData(barDataSet)
        barChart.setFitBars(true)
        barChart.data=barData
        barChart.description.text="Bar Chart"
        barChart.animateY(2000);


    }
}