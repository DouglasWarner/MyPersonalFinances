package com.douglas.mypersonalfinances.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.douglas.mypersonalfinances.data.dao.CategoryDAO
import com.douglas.mypersonalfinances.data.dao.MyPlannedDAO
import com.douglas.mypersonalfinances.data.dao.MyTransactionDAO
import com.douglas.mypersonalfinances.data.dao.UserAppDAO
import com.douglas.mypersonalfinances.data.model.Category
import com.douglas.mypersonalfinances.data.model.UserApp
import com.douglas.mypersonalfinances.data.model.planned.MyPlanned
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [UserApp::class, MyTransaction::class, MyPlanned::class, Category::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class, TransactionTypeConverter::class)
abstract class MpfDatabase : RoomDatabase() {

    abstract fun UserAppDAO(): UserAppDAO
    abstract fun MyTransactionDAO(): MyTransactionDAO
    abstract fun MyPlannedDAO(): MyPlannedDAO
    abstract fun CategoryDAO(): CategoryDAO

    companion object {
        const val DATABASE_NAME = "MyPersonalFinancesAPP"
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    var wordDao = database.wordDao()
//
//                    // Delete all content here.
//                    wordDao.deleteAll()
//
//                    // Add sample words.
//                    var word = Word("Hello")
//                    wordDao.insert(word)
//                    word = Word("World!")
//                    wordDao.insert(word)
//
//                    // TODO: Add your own words!
//                    word = Word("TODO!")
//                    wordDao.insert(word)
//                }
//            }
        }
    }

}