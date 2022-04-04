package com.douglas.mypersonalfinances.data.model.itemAdapter

import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction

class MonthTransactionAdapterItem(
    val month: String,
    val totalAmountExpenses: Float,
    val totalAmountIncomes: Float,
    val expensesList: List<MyTransaction>,
    val incomesList: List<MyTransaction>,
    val plannedData: MonthPlannedAdapterItem
)