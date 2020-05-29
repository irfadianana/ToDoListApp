package com.example.todolistapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todolistapp.db.ToDoList.ToDoList
import com.example.todolistapp.db.ToDoList.ToDoListRepository
import java.util.*

class ToDoListViewModel(application: Application) : AndroidViewModel(application) {

    private var toDoListRepository = ToDoListRepository(application)
    private var todolists: LiveData<List<ToDoList>>? = toDoListRepository.getToDoList()

    fun getToDoList(): LiveData<List<ToDoList>>? {
        return todolists
    }

    fun insertToDoList(toDoList: ToDoList) {
        toDoListRepository.insertToDoList(toDoList)
    }

    fun updateToDoList(toDoList: ToDoList) {
        toDoListRepository.updateToDoList(toDoList)
    }

    fun deleteToDoList(toDoList: ToDoList) {
        toDoListRepository.deleteToDoList(toDoList)
    }

    fun dueToDoList(due: Date) {
        toDoListRepository.dueToDoList(due)
    }
}