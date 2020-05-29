package com.example.todolistapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.db.ToDoList.ToDoList
import kotlinx.android.synthetic.main.item_todolist.view.*

class ToDoListAdapter (private val context: Context?, private val listener: (ToDoList, Int) -> Unit) :
    RecyclerView.Adapter<ToDoListViewHolder>() {

    private var todolists = listOf<ToDoList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todolist,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = todolists.size

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, todolists[position], listener)
        }
    }

    fun setToDoLists(todolists: List<ToDoList>) {
        this.todolists = todolists
        notifyDataSetChanged()
    }
}

class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(context : Context, toDoList: ToDoList, listener: (ToDoList, Int) -> Unit) {
        itemView.toDolistTV.text = toDoList.note

        itemView.setOnClickListener{
            listener(toDoList, layoutPosition)
        }
    }
}