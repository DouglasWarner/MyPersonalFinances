package com.douglas.mypersonalfinances.data.dao

import androidx.room.*
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.douglas.mypersonalfinances.data.model.planned.MyPlanned
import kotlinx.coroutines.flow.Flow
import org.joda.time.LocalDateTime

@Dao
interface MyPlannedDAO {

    @Query("SELECT * FROM planned")
    fun getAllPlanned(): Flow<List<MyPlanned>>

    @Query("SELECT * FROM planned WHERE type_planned=:type")
    fun getListExpensePlanned(type: TypeTransaction = TypeTransaction.Expenses): Flow<List<MyPlanned>>

    @Query("SELECT * FROM planned WHERE type_planned=:type")
    fun getListIncomePlanned(type: TypeTransaction = TypeTransaction.Incomes): Flow<List<MyPlanned>>

    @Query("SELECT * FROM planned WHERE month=:date AND type_planned=:type")
    fun getListExpenseByMONTH(
        date: LocalDateTime,
        type: TypeTransaction = TypeTransaction.Expenses
    ): Flow<List<MyPlanned>>

    @Query("SELECT * FROM planned WHERE month=:date AND type_planned=:type")
    fun getListIncomeByMONTH(
        date: LocalDateTime,
        type: TypeTransaction = TypeTransaction.Incomes
    ): Flow<List<MyPlanned>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(myPlanned: MyPlanned): Long

    @Update
    suspend fun update(myPlanned: MyPlanned)

    @Query("DELETE FROM planned")
    suspend fun deleteAll()
}