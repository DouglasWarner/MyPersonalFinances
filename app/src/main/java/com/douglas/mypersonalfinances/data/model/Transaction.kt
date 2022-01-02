package com.douglas.mypersonalfinances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

open class Transaction(
        @SerializedName("idTransaction")
        var idTransaction: Int,
        @SerializedName("Date")
        var dateTime: Date,
        @SerializedName("Amount")
        var amount: Float,
        @SerializedName("Description")
        var description: String,
        @SerializedName("")
        var category: String? = "Category"
) : Serializable {
        override fun toString(): String {
                return "Transaction(idTransaction=$idTransaction, dateTime=$dateTime, amount=$amount, description='$description', category=$category)"
        }
}