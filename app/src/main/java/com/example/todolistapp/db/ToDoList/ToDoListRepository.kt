package com.example.todolistapp.db.ToDoList

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todolistapp.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class ToDoListRepository(application: Application) {

    private val todolistDao: ToDoListDao?
    private var todolists: LiveData<List<ToDoList>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        todolistDao = db?.todolistDao()
        todolists = todolistDao?.getToDoList()
    }

    fun getToDoList(): LiveData<List<ToDoList>>? {
        return todolists
    }

    fun insertToDoList(toDoList: ToDoList)= runBlocking {
        this.launch(Dispatchers.IO) {
            todolistDao?.insertToDoList(toDoList)
        }
    }

    fun updateToDoList(toDoList: ToDoList)= runBlocking {
        this.launch(Dispatchers.IO) {
            todolistDao?.updateToDoList(toDoList)
        }
    }

    fun deleteToDoList(toDoList: ToDoList) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                todolistDao?.deleteToDoList(toDoList)
            }
        }
    }

    fun dueToDoList(due: Date) = runBlocking {
        this.launch(Dispatchers.IO) {
            todolistDao?.dueToDoList(due)
        }
    }


}

