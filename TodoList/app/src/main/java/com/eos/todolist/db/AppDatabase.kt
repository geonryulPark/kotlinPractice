package com.eos.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TodoEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTodoDao() : ToDoDao

    companion object {
        val databaseName = "db_todo"
        var appDataBase : AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase? {
            if (appDataBase == null) {
                appDataBase = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    databaseName).build()
            }
            return appDataBase
        }
    }
}