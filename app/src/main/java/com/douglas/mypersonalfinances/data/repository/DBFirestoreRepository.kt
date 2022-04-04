package com.douglas.mypersonalfinances.data.repository

import android.text.format.DateFormat
import com.douglas.mypersonalfinances.data.model.MonthOverview
import com.douglas.mypersonalfinances.data.model.UserApp
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.utils.CommonUtils.Companion.DAYWEEKFORMAT
import com.douglas.mypersonalfinances.utils.CommonUtils.Companion.MONTHFORMAT
import com.douglas.mypersonalfinances.utils.CommonUtils.Companion.YEARFORMAT
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.joda.time.LocalDateTime

class DBFirestoreRepository {

    val firestore = Firebase.firestore

    fun createNewExpenses(expense: MyTransaction, user: UserApp) {
        val yearString = DateFormat.format(YEARFORMAT, expense.dateTime.toDate())
        val monthString = DateFormat.format(MONTHFORMAT, expense.dateTime.toDate())
        val dayString = DateFormat.format(DAYWEEKFORMAT, expense.dateTime.toDate())

        firestore.collection("transaction")
            .document(expense.idTransaction.toString())
            .set(
                hashMapOf(
                    KEY_AMOUNT to expense.amount,
                    KEY_DESCRIPTION to expense.description
                )
            )
    }

    fun createNewIncomes(income: MyTransaction, user: UserApp) {
        val yearString = DateFormat.format(YEARFORMAT, income.dateTime.toDate())
        val monthString = DateFormat.format(MONTHFORMAT, income.dateTime.toDate())
        val dayString = DateFormat.format(DAYWEEKFORMAT, income.dateTime.toDate())

        firestore.collection(
            String.format(
                COLLECTION_INCOMES,
                user.name, yearString, monthString, dayString
            )
        )
            .document(income.idTransaction.toString())
            .set(
                hashMapOf(
                    KEY_AMOUNT to income.amount,
                    KEY_DESCRIPTION to income.description
                )
            )
    }

    fun createPlannedExpense(monthOverview: MonthOverview, user: UserApp) {
        val yearString = DateFormat.format(
            YEARFORMAT,
            monthOverview.monthDataTime?.toDate() ?: LocalDateTime.now().toDate()
        )
        val monthString = DateFormat.format(
            MONTHFORMAT,
            monthOverview.monthDataTime?.toDate() ?: LocalDateTime.now().toDate()
        )
        val dayString = DateFormat.format(
            DAYWEEKFORMAT,
            monthOverview.monthDataTime?.toDate() ?: LocalDateTime.now().toDate()
        )

        firestore.collection(String.format(COLLECTION_MONTHS, user.name, yearString))
            .document(monthString.toString())
            .set(
                hashMapOf(
                    KEY_PLANNEDEXPENSES to monthOverview.plannedExpense,
                    KEY_PLANNEDINCOMES to monthOverview.plannedIncome
                )
            )
    }

    fun createPlannedIncome(monthOverview: MonthOverview, user: UserApp) {
        val yearString = DateFormat.format(
            YEARFORMAT,
            monthOverview.monthDataTime?.toDate() ?: LocalDateTime.now().toDate()
        )
        val monthString = DateFormat.format(
            MONTHFORMAT,
            monthOverview.monthDataTime?.toDate() ?: LocalDateTime.now().toDate()
        )
        val dayString = DateFormat.format(
            DAYWEEKFORMAT,
            monthOverview.monthDataTime?.toDate() ?: LocalDateTime.now().toDate()
        )

        firestore.collection(String.format(COLLECTION_MONTHS, user.name, yearString))
            .document(monthString.toString())
            .set(
                hashMapOf(
                    KEY_PLANNEDEXPENSES to monthOverview.plannedExpense,
                    KEY_PLANNEDINCOMES to monthOverview.plannedIncome
                )
            )
    }

    fun getExpenseList() {
//        firestore.collection(String.format(COLLECTION_MONTHS, user.name, yearString))
    }

    fun getIncomeList() {

    }

    companion object {
        private val NODE_USERS = "/USERS/%s"
        private val NODE_FINANCES = "/FINANCES/%s"
        private val NODE_MONTHS = "/MONTH"
        private val NODE_TRANSACTION = "/%s/TRANSACTION"
        private val NODE_EXPENSES = "/EXPENSE"
        private val NODE_INCOMES = "/INCOME"

        private val COLLECTION_EXPENSES =
            NODE_USERS + NODE_FINANCES + NODE_MONTHS + "/%s" + NODE_TRANSACTION + NODE_EXPENSES
        private val COLLECTION_INCOMES =
            NODE_USERS + NODE_FINANCES + NODE_MONTHS + "/%s" + NODE_TRANSACTION + NODE_INCOMES
        private val COLLECTION_MONTHS = NODE_USERS + NODE_FINANCES + NODE_MONTHS

//        /USERS/douglas/FINANCES/2022/MONTH/January

        private val KEY_PLANNEDEXPENSES = "PLANNED_EXPENSES"
        private val KEY_PLANNEDINCOMES = "PLANNED_INCOMES"
        private val KEY_AMOUNT = "AMOUNT"
        private val KEY_DESCRIPTION = "DESCRIPTION"
    }
}