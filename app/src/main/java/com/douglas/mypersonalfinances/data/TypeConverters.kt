package com.douglas.mypersonalfinances.data

import androidx.room.TypeConverter
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import org.joda.time.LocalDateTime
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromDate(value: LocalDateTime): Long = value.toDate().time

    @TypeConverter
    fun toDate(value: Long): LocalDateTime = LocalDateTime.fromDateFields(Date(value))
}

class TransactionTypeConverter {
    @TypeConverter
    fun toType(value: String) = enumValueOf<TypeTransaction>(value)

    @TypeConverter
    fun fromType(value: TypeTransaction) = value.name
}