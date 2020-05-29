package com.example.todolistapp.db.ToDoList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todolist")
data class ToDoList (
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "created")
    val created: Date? = null,
    @ColumnInfo(name = "updated")
    val updated: Date? = null,
    @ColumnInfo(name = "due")
    val due: Date? = null
)