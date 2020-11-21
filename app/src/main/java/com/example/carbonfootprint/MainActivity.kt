package com.example.carbonfootprint

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.compound.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.mortgage.*


class CarbonOut(private val miles: Int) {
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
                val miles = inputMiles.text.toString()

                if (miles.isEmpty()) {
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
                val c = Compound(
                    comp_initial_balance.text.toString(),
                    comp_years.text.toString(),
                    comp_percent.text.toString(),
                    comp_monthly.text.toString()
                )

                comp_final_balance.setText(c.calcBalance())
            }
        }

        button_mortgage.setOnClickListener()
        {
            setContentView(R.layout.mortgage)

            button_mortgage_calc.setOnClickListener()
            {
                val m = Mortgage(
                    mortgage_interest.text.toString(),
                    mortgage_loan.text.toString(),
                    mortgage_years.text.toString()
                )

                mortgage_monthly.setText(m.calcMortgage())
            }
        }
    }

    private var backPressCnt = 0

    override fun onBackPressed() {
        if (backPressCnt >= 1) {
            super.onBackPressed()
        } else {
            setContentView(R.layout.fragment_main)
            backPressCnt++
        }
    }
}


