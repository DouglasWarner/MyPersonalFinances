package com.douglas.mypersonalfinances.data.repository

import androidx.annotation.WorkerThread
import com.douglas.mypersonalfinances.data.dao.MyTransactionDAO
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.data.service.MyTransactionAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.joda.time.LocalDateTime
import javax.inject.Inject

class MyTransactionRepository @Inject constructor(
    private val myTransactionAPIService: MyTransactionAPIService,
    private val myTransactionDAO: MyTransactionDAO
) {

    //region FROM DATABASE

    val expenseTransactionListFromDatabase: Flow<List<MyTransaction>> =
        myTransactionDAO.getListExpenseTransaction() ?: emptyFlow()
    val incomeTransactionListFromDatabase: Flow<List<MyTransaction>> =
        myTransactionDAO.getListIncomeTransaction() ?: emptyFlow()

    @WorkerThread
    fun getExpenseListFromDatabase(date: LocalDateTime): Flow<List<MyTransaction>> =
        myTransactionDAO.getListExpenseByYEAR(date) ?: emptyFlow()

    @WorkerThread
    fun getIncomeListFromDatabase(date: LocalDateTime): Flow<List<MyTransaction>> =
        myTransactionDAO.getListIncomeByYEAR(date) ?: emptyFlow()

    @WorkerThread
    fun getAllTransactionFromDatabase(): Flow<List<MyTransaction>> =
        myTransactionDAO.getAllTransaction()
            ?: emptyFlow()

    @WorkerThread
    fun insertTransaction(myTransaction: MyTransaction): Long =
        myTransactionDAO.insert(myTransaction) ?: -1

    @WorkerThread
    suspend fun updateTransaction(myTransaction: MyTransaction) =
        myTransactionDAO.update(myTransaction)

    //endregion


    //region FROM API

    val getAllTransaction: Flow<List<MyTransaction>> = myTransactionAPIService.getAllTransactions()

    //endregion
}