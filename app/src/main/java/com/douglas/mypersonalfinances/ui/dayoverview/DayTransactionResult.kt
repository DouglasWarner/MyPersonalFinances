package com.douglas.mypersonalfinances.ui.dayoverview

import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction

class DayTransactionResult(
    val error: Int? = null,
    val listExpense: List<MyTransaction>? = null,
    val listIncome: List<MyTransaction>? = null
) {
}