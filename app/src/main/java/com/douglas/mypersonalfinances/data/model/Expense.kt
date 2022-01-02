package com.douglas.mypersonalfinances.data.model

import java.io.Serializable
import java.util.*

class Expense(
    idTransaction: Int,
    dateTime: Date,
    amount: Float,
    description: String,
    category: String
) : Transaction(idTransaction, dateTime, amount, description), Serializable