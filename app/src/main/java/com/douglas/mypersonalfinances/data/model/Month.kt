package com.douglas.mypersonalfinances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Month(
    @SerializedName("Month")
    var monthDataTime: Date,
    @SerializedName("Days")
    var days: ArrayList<Day>?,
    @SerializedName("PlannedExpenses")
    var plannedExpense: Float?,
    @SerializedName("PlannedIncomes")
    var plannedIncome: Float?
) : Serializable {

    fun getTotalMonthExpense(): Float {
        var sumExpense = 0F
        return if(days?.isNotEmpty() == true) {
            for (day in days!!) {
                sumExpense += day.getTotalDayExpense()
            }
            sumExpense
        } else {
            sumExpense
        }
    }

    fun getTotalMonthIncome(): Float {
        var sumIncome = 0F
        return if(days?.isNotEmpty() == true) {
            for (day in days!!) {
                sumIncome += day.getTotalDayIncome()
            }
            sumIncome
        } else {
            sumIncome
        }
    }

    override fun toString(): String {
        return "Month(monthDataTime=$monthDataTime, days=$days, plannedExpense=$plannedExpense, plannedIncome=$plannedIncome)"
    }


}