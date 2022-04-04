package com.douglas.mypersonalfinances.utils

import android.icu.util.Calendar
import com.douglas.mypersonalfinances.data.model.*
import java.util.*

class CommonUtils {

    companion object {
        const val YEARFORMAT = "yyyy"
        const val MONTHFORMAT = "MMMM"
        const val DAYWEEKFORMAT = "EEE, d"
        const val YEARMONTHFORMAT = "yyyy MMM"
        const val DATEFORMAT = "dd/MM/yyyy"


        private fun getCalendar(): Calendar {
            val c = Calendar.getInstance()
            c[Calendar.MONTH] = 0
            c[Calendar.YEAR] = 2022
            c[Calendar.DAY_OF_MONTH] = 1
            return c
        }

        private fun getDate(): Date? {
            return getCalendar().time
        }

//        fun getYear(): YearOverview? {
//            return YearOverview(getDate()!!, getMonth())
//        }
//
//        fun getMonth(): ArrayList<MonthOverview>? {
//            val months = ArrayList<MonthOverview>()
//            for (i in 0..5) {
//                val c = getCalendar()
//                c[Calendar.MONTH] = i
//                months.add(MonthOverview(c.time, 100f, 100f))
//            }
//            return months
//        }

//        fun getDays(): ArrayList<DayOverview>? {
//            val days = ArrayList<DayOverview>()
//            val c = getCalendar()
//            for (i in 1..15) {
//                val d = DayOverview(c.time, getExpenses(c.time), getIncome(c.time))
//                days.add(d)
//                c.add(Calendar.DAY_OF_MONTH, 1)
//            }
//            return days
//        }
//
//        fun getExpenses(d: Date?): ArrayList<Expense>? {
//            val expenses = ArrayList<Expense>()
//            for (i in 0..0) {
//                expenses.add(Expense(i, d!!, Random().nextFloat(), "Gasto $i", "Sueldo $i"))
//            }
//            return expenses
//        }
//
//        fun getIncome(d: Date?): ArrayList<Income>? {
//            val incomes = ArrayList<Income>()
//            for (i in 0..0) {
//                incomes.add(Income(i, d!!, Random().nextFloat(), "Ingreso $i", "Sueldo $i"))
//            }
//            return incomes
//        }
    }
}