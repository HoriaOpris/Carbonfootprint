package com.example.carbonfootprint

import java.text.NumberFormat
import java.util.*

class Compound(
    private val Balance: String,
    private val Years: String,
    private val Percent: String,
    private val Monthly: String
) {
    private fun treatNullString(s: String): Double {
        return if (s.isEmpty()) 0.0 else s.toDouble()
    }

    fun calcBalance(): String {
        var balance = treatNullString(Balance)
        var years = treatNullString(Years)
        var percent = treatNullString(Percent)
        var monthly = treatNullString(Monthly)

        for (i in 0 until years.toInt())
            balance += ((percent / 100.0) * balance) + (monthly * 12.0)

        return NumberFormat.getNumberInstance(Locale.US).format(balance.toInt()).toString() + " $"
    }
}