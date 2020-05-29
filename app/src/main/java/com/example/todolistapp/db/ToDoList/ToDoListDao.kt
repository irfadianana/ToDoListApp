package com.example.todolistapp.db.ToDoList

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface ToDoListDao {
    //return list dengan livedata
    @Query("SELECT * FROM todolist ORDER BY id DESC")
    fun getToDoList():LiveData<List<ToDoList>>
    //create
    @Insert
    suspend fun insertToDoList(toDoList: ToDoList)
    //update
    @Update
    suspend fun updateToDoList(toDoList: ToDoList)
    //due date
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun dueToDoList(due: Date)
    //delete
    @Delete
    suspend fun deleteToDoList(toDoList: ToDoList)
}