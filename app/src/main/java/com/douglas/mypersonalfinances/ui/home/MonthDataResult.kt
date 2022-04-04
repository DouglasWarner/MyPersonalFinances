package com.douglas.mypersonalfinances.ui.home

import com.douglas.mypersonalfinances.data.model.itemAdapter.MonthTransactionAdapterItem

data class MonthDataResult(
    val error: Int? = null,
    val monthData: MonthTransactionAdapterItem? = null
)
