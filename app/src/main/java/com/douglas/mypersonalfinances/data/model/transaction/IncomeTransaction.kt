package com.douglas.mypersonalfinances.data.model.transaction

import com.douglas.mypersonalfinances.data.model.Category
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import org.joda.time.LocalDateTime

class IncomeTransaction(
    idTransaction: Int,
    dateTime: LocalDateTime,
    amount: Float,
    description: String,
    category: String,
    isSync: Boolean,
    user: Int
) : MyTransaction(
    idTransaction, dateTime, amount, description, category,
    TypeTransaction.Incomes, isSync, user
)