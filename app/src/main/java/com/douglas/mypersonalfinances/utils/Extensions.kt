package com.douglas.mypersonalfinances.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.douglas.mypersonalfinances.data.model.itemAdapter.MonthPlannedAdapterItem
import com.douglas.mypersonalfinances.data.model.itemAdapter.MonthTransactionAdapterItem
import com.douglas.mypersonalfinances.data.model.planned.MyPlanned
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import org.joda.time.LocalDateTime

//region EDIT TEXT

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

//endregion

//region MonthOverview
//
//fun MonthOverview.getTotalMonthExpense(): Float {
//    var sumExpense = 0F
//    return if (days?.isNotEmpty() == true) {
//        for (day in days) {
//            sumExpense += day.getTotalDayExpense()
//        }
//        sumExpense
//    } else {
//        sumExpense
//    }
//}
//
//fun MonthOverview.getTotalMonthIncome(): Float {
//    var sumIncome = 0F
//    return if (days?.isNotEmpty() == true) {
//        for (day in days) {
//            sumIncome += day.getTotalDayIncome()
//        }
//        sumIncome
//    } else {
//        sumIncome
//    }
//}

//endregion

//region DayOverview

//fun DayOverview.getTotalDayExpense(): Float {
//    var sumExpense = 0F
//    return if (expenseList?.isNotEmpty() == true) {
//        for (expense in expenseList) {
//            sumExpense += expense.amount
//        }
//        sumExpense
//    } else {
//        sumExpense
//    }
//}

//fun DayOverview.getTotalDayIncome(): Float {
//    var sumIncome = 0F
//    return if (incomeList?.isNotEmpty() == true) {
//        for (income in incomeList) {
//            sumIncome += income.amount
//        }
//        sumIncome
//    } else {
//        sumIncome
//    }
//}

//endregion

////region YearOverview
//
//fun YearOverview.getTotalYearExpense(): Float {
//    var sumExpense = 0F
//    return if (months?.isNotEmpty() == true) {
//        for (month in months) {
//            sumExpense += month.getTotalMonthExpense()
//        }
//        sumExpense
//    } else {
//        sumExpense
//    }
//}
//
//fun YearOverview.getTotalYearIncome(): Float {
//    var sumIncome = 0F
//    return if (months?.isNotEmpty() == true) {
//        for (month in months) {
//            sumIncome += month.getTotalMonthIncome()
//        }
//        sumIncome
//    } else {
//        sumIncome
//    }
//}
//
////endregion

//region MONTH OVERVIEW ADAPTER ITEM

fun ArrayList<MyTransaction>.toMonthAdapterItem(
    localDate: LocalDateTime
): MonthTransactionAdapterItem {

    val newData = ArrayList<MonthTransactionAdapterItem>()
//    for (i in 1..12) {
//        val month = getOrNull(i)?.monthDataTime?.toString(YEARMONTHFORMAT, Locale.getDefault())
//            ?: localDate.withMonthOfYear(i).toString(YEARMONTHFORMAT, Locale.getDefault())
//
//        newData.add(
//            i,
//            MonthOverviewAdapterItem(
//                month = month,
//                plannedExpense = getOrNull(i)?.plannedExpense ?: 0f,
//                plannedIncome = getOrNull(i)?.plannedIncome ?: 0f,
//                myTransactionList = allTransaction.filter { it.dateTime.monthOfYear.equals(localDate.monthOfYear) } as ArrayList<MyTransaction>
//            )
//        )
//    }

    val expensesList =
        filter { expenses -> expenses.dateTime.monthOfYear == LocalDateTime.now().monthOfYear && expenses.type == TypeTransaction.Expenses }
    val incomeList =
        filter { incomes -> incomes.dateTime.monthOfYear == LocalDateTime.now().monthOfYear && incomes.type == TypeTransaction.Incomes }

    val totalAmountExpense = expensesList.map { it.amount }.sum()
    val totalAmountIncome = incomeList.map { it.amount }.sum()

    return MonthTransactionAdapterItem(
        LocalDateTime.now().getCurrentMonthString(),
        totalAmountExpense,
        totalAmountIncome,
        expensesList,
        incomeList,
        MonthPlannedAdapterItem("",0f,0f, emptyList())
    )
}

fun LocalDateTime.getCurrentMonthString(): String {
    return monthOfYear().asText
}

fun List<MyPlanned>.toMonthPlannedAdapterItem(): MonthPlannedAdapterItem {

    val totalPlannedExpense =
        filter { planned -> planned.month.monthOfYear == LocalDateTime.now().monthOfYear && planned.type == TypeTransaction.Expenses }.map { it.planned }
            .sum()
    val totalPlannedIncome =
        filter { planned -> planned.month.monthOfYear == LocalDateTime.now().monthOfYear && planned.type == TypeTransaction.Expenses }.map { it.planned }
            .sum()

    return MonthPlannedAdapterItem(
        LocalDateTime.now().getCurrentMonthString(),
        totalPlannedExpense,
        totalPlannedIncome,
        this
    )
}

//fun List<MyTransaction>.filterToDate(calendar: Calendar) {
//
//    this.filter { it.dateTime.after(calendar.time) }
//}

//endregion