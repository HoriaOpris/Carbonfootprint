package com.example.carbonfootprint

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.compound.*
import kotlinx.android.synthetic.main.fragment_main.*

class CarbonOut(val miles: Int) {
    fun run(): String {
        return miles.toString()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears+
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                var miles = inputMiles.text.toString()

                if (miles.isNullOrEmpty()) {
                    val carbon = CarbonOut(500)
                    outputCarbon.setText(carbon.run())
                } else {
                    val carbon = CarbonOut(miles.toInt())
                    outputCarbon.setText(carbon.run())
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        button_compound.setOnClickListener()
        {
            setContentView(R.layout.compound)

            comp_calculate.setOnClickListener()
            {
                var initBalance = comp_initial_balance.text.toString()
                var years = comp_years.text.toString()
                var percent = comp_percent.text.toString()
                var monthly = comp_monthly.text.toString()

                if (initBalance.isNullOrEmpty())
                    initBalance = 0.toString()

                if (years.isNullOrEmpty())
                    years = 0.toString()

                if (percent.isNullOrEmpty())
                    percent = 0.toString()

                if (monthly.isNullOrEmpty())
                    monthly = 0.toString()

                var finalBalance: Double
                finalBalance = initBalance.toDouble()

                for (i in 0 until years.toInt()) {
                    finalBalance += ((percent.toDouble() / 100.0) * finalBalance)
                    finalBalance += monthly.toDouble() * 12.0
                }

                comp_final_balance.setText(finalBalance.toInt().toString() + " $")
            }
        }
    }
}


