package com.douglas.mypersonalfinances.data.dao

import androidx.room.*
import com.douglas.mypersonalfinances.data.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {

    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategory(listCategory: List<Category>): List<Long>

    @Update
    suspend fun updateAllCategory(listCategory: List<Category>)

    @Query("DELETE FROM category")
    suspend fun deleteAllCategory()
}