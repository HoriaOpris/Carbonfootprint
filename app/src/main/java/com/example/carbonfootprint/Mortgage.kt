package com.example.carbonfootprint

import java.text.NumberFormat
import java.util.*

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
        var total  = loan / years

        return NumberFormat.getNumberInstance(Locale.US).format(total).toString() + " $"
    }
}