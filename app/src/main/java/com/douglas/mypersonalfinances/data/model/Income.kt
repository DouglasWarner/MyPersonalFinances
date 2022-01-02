package com.douglas.mypersonalfinances.data.model

import java.io.Serializable
import java.util.*

class Income(
    idTransaction: Int,
    dateTime: Date,
    amount: Float,
    description: String,
    category: String
) : Transaction(idTransaction, dateTime, amount, description), Serializable

//TODO this have to be in firebase DB
//enum class Category(s: String) {
//    Salary("Salary"), Saving("Saving"), Investment("Investment"), Bonus("Bonus")
//}