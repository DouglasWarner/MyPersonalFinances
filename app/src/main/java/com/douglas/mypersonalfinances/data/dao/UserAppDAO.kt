package com.douglas.mypersonalfinances.data.dao

import androidx.room.*
import com.douglas.mypersonalfinances.data.model.UserApp
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAppDAO {

    @Query("SELECT * FROM user WHERE account=account AND password=password")
    fun getUser(): Flow<List<UserApp>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userApp: UserApp): Long

    @Update
    suspend fun update(myUser: UserApp)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}