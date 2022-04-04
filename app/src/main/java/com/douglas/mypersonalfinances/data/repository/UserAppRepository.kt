package com.douglas.mypersonalfinances.data.repository

import androidx.annotation.WorkerThread
import com.douglas.mypersonalfinances.data.dao.UserAppDAO
import com.douglas.mypersonalfinances.data.model.UserApp
import com.douglas.mypersonalfinances.data.service.UserAppAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class UserAppRepository @Inject constructor(
    private val userAppAPIService: UserAppAPIService,
    private val userAppDAO: UserAppDAO?
) {

    //region FROM DATABASE

    val userApp: Flow<List<UserApp>> = userAppDAO?.getUser() ?: emptyFlow()

    @WorkerThread
    fun insertUser(userApp: UserApp): Long =
        userAppDAO?.insert(userApp) ?: -1

    @WorkerThread
    suspend fun updateUser(userApp: UserApp) =
        userAppDAO?.update(userApp)


    //endregion

    //region FROM API SERVICE

    //endregion
}