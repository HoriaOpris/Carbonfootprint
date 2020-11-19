package com.example.carbonfootprint

import java.text.NumberFormat
import java.util.*
import kotlin.math.pow

class Mortgage(
    private val Interest: String,
    private val Loan: String,
    private val Years: String
) {
    private fun treatNullString(s: String): Double {
        return if (s.isEmpty()) 0.0 else s.toDouble()
    }

    fun calcMortgage(): String {
        var interest = treatNullString(Interest)
        var loan = treatNullString(Loan)
        var years = treatNullString(Years)
        var monthly = (loan * ((interest / 100) / 12)) *
                (((1 + ((interest / 100) / 12)).pow(years * 12) /
                        (((1 + ((interest / 100) / 12)).pow(years * 12)) - 1)))

        return NumberFormat.getNumberInstance(Locale.US).format(monthly.toInt()).toString() + " $"
    }
}