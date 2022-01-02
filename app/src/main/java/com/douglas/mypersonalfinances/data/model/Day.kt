package com.douglas.mypersonalfinances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Day(
    @SerializedName("Day")
    var numDay: Date,
    @SerializedName("Expenses")
    var expenseList: ArrayList<Expense>?,
    @SerializedName("Incomes")
    var incomeList: ArrayList<Income>?
) : Serializable {

    fun getTotalDayExpense() : Float {
        var sumExpense = 0F
        return if(expenseList?.isNotEmpty() == true) {
            for (expense in expenseList!!) {
                sumExpense += expense.amount
            }
            sumExpense
        } else {
            sumExpense
        }
    }
    fun getTotalDayIncome() : Float {
        var sumIncome = 0F
        return if(incomeList?.isNotEmpty() == true) {
            for (income in incomeList!!) {
                sumIncome += income.amount
            }
            sumIncome
        } else {
            sumIncome
        }
    }

    override fun toString(): String {
        return "Day(numDay=$numDay, expenseList=$expenseList, incomeList=$incomeList)"
    }
}