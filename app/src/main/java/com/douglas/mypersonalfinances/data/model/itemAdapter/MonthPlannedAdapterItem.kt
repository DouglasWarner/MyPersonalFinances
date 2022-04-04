package com.douglas.mypersonalfinances.data.model.itemAdapter

import com.douglas.mypersonalfinances.data.model.planned.MyPlanned

class MonthPlannedAdapterItem(
    val month: String,
    val totalPlannedExpense: Float,
    val totalPlannedIncome: Float,
    val myPlannedList: List<MyPlanned>
)