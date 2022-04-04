package com.douglas.mypersonalfinances.data.service

import com.douglas.mypersonalfinances.data.MpfApiClient
import com.douglas.mypersonalfinances.data.MpfApiClient.URLS.URL_AllMyTransaction
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyTransactionAPIService @Inject constructor(
    private val api: MpfApiClient
) {

    fun getAllTransactions(): Flow<List<MyTransaction>> {
        return flow {
//            emit(loading)

            //TODO
            emit(api.getAllMyTransaction(URL_AllMyTransaction).body() ?: emptyList())

        }.flowOn(Dispatchers.IO)
    }
}