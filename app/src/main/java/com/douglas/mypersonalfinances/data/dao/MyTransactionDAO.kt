package com.douglas.mypersonalfinances.data.dao

import androidx.room.*
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import kotlinx.coroutines.flow.Flow
import org.joda.time.LocalDateTime

@Dao
interface MyTransactionDAO {

    @Query("SELECT * FROM `transaction`")
    fun getAllTransaction(): Flow<List<MyTransaction>>

    @Query("SELECT * FROM `transaction` WHERE type_transaction=:type")
    fun getListExpenseTransaction(type: TypeTransaction = TypeTransaction.Expenses): Flow<List<MyTransaction>>

    @Query("SELECT * FROM `transaction` WHERE type_transaction=:type")
    fun getListIncomeTransaction(type: TypeTransaction = TypeTransaction.Incomes): Flow<List<MyTransaction>>

    @Query("SELECT * FROM `transaction` WHERE date=:date AND type_transaction=:type")
    fun getListExpenseByYEAR(
        date: LocalDateTime,
        type: TypeTransaction = TypeTransaction.Expenses
    ): Flow<List<MyTransaction>>

    @Query("SELECT * FROM `transaction` WHERE date=:date AND type_transaction=:type")
    fun getListIncomeByYEAR(
        date: LocalDateTime,
        type: TypeTransaction = TypeTransaction.Incomes
    ): Flow<List<MyTransaction>>

    @Query("SELECT * FROM `transaction` WHERE id_transaction=:id AND type_transaction=:type")
    fun getExpenseByID(
        id: Int,
        type: TypeTransaction = TypeTransaction.Expenses
    ): Flow<MyTransaction?>

    @Query("SELECT * FROM `transaction` WHERE id_transaction=:id AND type_transaction=:type")
    fun getIncomeByID(
        id: Int,
        type: TypeTransaction = TypeTransaction.Incomes
    ): Flow<MyTransaction?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(myTransaction: MyTransaction): Long

    @Update
    suspend fun update(myTransaction: MyTransaction)

    @Query("DELETE FROM `transaction`")
    suspend fun deleteAll()
}