package com.douglas.mypersonalfinances.data.model

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime
import java.io.Serializable

data class MonthOverview(
    @SerializedName("date")
    val monthDataTime: LocalDateTime?,
    @SerializedName("planned_expense")
    val plannedExpense: Float?,
    @SerializedName("planned_income")
    val plannedIncome: Float?
) : Serializable