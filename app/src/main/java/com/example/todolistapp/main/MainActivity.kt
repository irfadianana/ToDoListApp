package com.example.todolistapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.db.ToDoList.ToDoList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todolist.*

class MainActivity : AppCompatActivity() {

    private lateinit var toDoListViewModel: ToDoListViewModel
    private lateinit var toDoListAdapter: ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toDoListRV.layoutManager = LinearLayoutManager(this)
        toDoListAdapter = ToDoListAdapter(this) { toDoList, i ->
            showAlertMenu(toDoList)
        }
        toDoListRV.adapter = toDoListAdapter

        toDoListViewModel = ViewModelProvider(this).get(toDoListViewModel::class.java)
        toDoListViewModel.getToDoList()?.observe(this, Observer {
            toDoListAdapter.setToDoLists(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenu -> showAlertDialogAdd()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialogAdd() {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.hint = "Enter your text"

        alert.setTitle("New To Do List")
        alert.setView(editText)

        alert.setPositiveButton("Save") { dialog, _ ->
            toDoListViewModel.insertToDoList(
                ToDoList(note = editText.text.toString())
            )
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
    }

    private fun showAlertMenu(toDoList: ToDoList) {
        val items = arrayOf("Edit", "Delete")

        val builder =
            AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            when (which) {
                0 -> {
                    showAlertDialogEdit(toDoList)
                }
                1 -> {
                    toDoListViewModel.deleteToDoList(toDoList)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(toDoList: ToDoList) {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.setText(toDoList.note)

        alert.setTitle("Edit To Do List")
        alert.setView(editText)

        alert.setPositiveButton("Update") { dialog, _ ->
            toDoList.note = editText.text.toString()
            toDoListViewModel.updateToDoList(toDoList)
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alert.show()
    }
}
