package com.douglas.mypersonalfinances.data

import com.douglas.mypersonalfinances.data.MpfApiClient.URLS.URL_AllMyTransaction
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MpfApiClient {

    object URLS {
        const val URL_Base = "https://mypersonalfinances-56651-default-rtdb.europe-west1.firebasedatabase.app/"
        const val URL_AllMyTransaction = ""
    }


    //TODO
    @GET
    suspend fun getAllMyTransaction(@Url url: String = URL_AllMyTransaction): Response<List<MyTransaction>>
}