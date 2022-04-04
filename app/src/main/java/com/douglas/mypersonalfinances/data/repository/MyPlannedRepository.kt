package com.douglas.mypersonalfinances.data.repository

import androidx.annotation.WorkerThread
import com.douglas.mypersonalfinances.data.dao.MyPlannedDAO
import com.douglas.mypersonalfinances.data.model.planned.MyPlanned
import com.douglas.mypersonalfinances.data.service.MyPlannedAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class MyPlannedRepository @Inject constructor(
    private val myPlannedAPIService: MyPlannedAPIService,
    private val myPlannedDAO: MyPlannedDAO
) {

    //region FROM DATABASE

    val expensePlannedList: Flow<List<MyPlanned>> =
        myPlannedDAO.getListExpensePlanned() ?: emptyFlow()
    val incomePlannedList: Flow<List<MyPlanned>> =
        myPlannedDAO.getListIncomePlanned() ?: emptyFlow()

    @WorkerThread
    fun getAllPlanned(): Flow<List<MyPlanned>> =
        myPlannedDAO.getAllPlanned()
            ?: emptyFlow()

    @WorkerThread
    fun insertPlanned(myPlanned: MyPlanned): Long =
        myPlannedDAO.insert(myPlanned) ?: -1

    @WorkerThread
    suspend fun updatePlanned(myPlanned: MyPlanned) =
        myPlannedDAO.update(myPlanned)

    //endregion

    //region FROM API SERVICE

    //endregion
}