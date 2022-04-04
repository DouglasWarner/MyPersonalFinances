package com.douglas.mypersonalfinances.data.model.planned

import com.douglas.mypersonalfinances.data.model.Category
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import org.joda.time.LocalDateTime

class ExpensePlanned(month: LocalDateTime, planned: Float, category: String, user: Int) :
    MyPlanned(month, category, TypeTransaction.Expenses, planned, user)