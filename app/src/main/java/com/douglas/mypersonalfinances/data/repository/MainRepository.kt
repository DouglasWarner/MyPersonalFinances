package com.douglas.mypersonalfinances.data.repository

import androidx.annotation.WorkerThread
import com.douglas.mypersonalfinances.data.dao.CategoryDAO
import com.douglas.mypersonalfinances.data.model.Category
import com.douglas.mypersonalfinances.data.service.MainAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainAPIService: MainAPIService,
    private val categoryDAO: CategoryDAO?
) {

    //region FROM DATABASE

    @WorkerThread
    fun allCategory(): Flow<List<Category>> =
        categoryDAO?.getAllCategory() ?: emptyFlow()

    @WorkerThread
    fun insertAllCategory(categoryList: List<Category>): List<Long> =
        categoryDAO?.insertAllCategory(categoryList) ?: emptyList()

    @WorkerThread
    suspend fun updateAllCategory(categoryList: List<Category>) =
        categoryDAO?.updateAllCategory(categoryList)

    @WorkerThread
    suspend fun deleteAllCategory() = categoryDAO?.deleteAllCategory()

    //endregion

    //region FROM API SERVICE

    //endregion
}