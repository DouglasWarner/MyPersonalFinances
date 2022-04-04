package com.douglas.mypersonalfinances.data.model

import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class DayOverview(
    @SerializedName("Day")
    val numDay: Date,
    @SerializedName("Transaction")
    val transactionList: ArrayList<MyTransaction>?
) : Serializable