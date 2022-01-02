package com.douglas.mypersonalfinances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Year(
    @SerializedName("Year")
    var yearDateTime: Date,
    @SerializedName("Months")
    var months: ArrayList<Month>?
) : Serializable {
    fun getTotalYearExpense(): Float {
        var sumExpense = 0F
        return if(months?.isNotEmpty() == true) {
            for (month in months!!) {
                sumExpense += month.getTotalMonthExpense()
            }
            sumExpense
        } else {
            sumExpense
        }
    }

    fun getTotalYearIncome(): Float {
        var sumIncome = 0F
        return if(months?.isNotEmpty() == true) {
            for (month in months!!) {
                sumIncome += month.getTotalMonthIncome()
            }
            sumIncome
        } else {
            sumIncome
        }
    }

    override fun toString(): String {
        return "Year(yearDateTime=$yearDateTime, months=$months)"
    }


}