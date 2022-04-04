package com.douglas.mypersonalfinances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class YearOverview(
    @SerializedName("Year")
    val yearDateTime: Date,
    @SerializedName("Months")
    val months: ArrayList<MonthOverview>?
) : Serializable