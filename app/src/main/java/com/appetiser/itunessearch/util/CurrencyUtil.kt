package com.appetiser.itunessearch.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object CurrencyUtil {

    fun convertToProperMoney(amount: Double): String {
        val dFormat = DecimalFormat("###,###,##0.00")
        return dFormat.format(amount)
    }

    fun convertToProperMoney(amount: String): String {
        val dFormat = DecimalFormat("###,###,##0.00")

        val format = NumberFormat.getInstance(Locale.US)
        val number = format.parse(amount)
        val d = number.toDouble()

        return dFormat.format(d)
    }


    fun checkIfLessThanZero(amount: Double, currency: String): String {
        val dAmount = BigDecimal(amount)

        return if (dAmount.compareTo(BigDecimal.ZERO) != 0) {
            currency + " " + convertToProperMoney(amount)
        } else {
            "N/A"
        }

    }

}