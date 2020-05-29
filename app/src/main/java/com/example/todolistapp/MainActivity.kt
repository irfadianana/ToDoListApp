package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*inisialisasi array list dan adapter*/
        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,itemlist)

        /*fungsi untuk menambahkan item ke dalam list ketika tombol add di tekan*/
        add.setOnClickListener {
            val add = itemlist.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()

            editText.text.clear()
        }

        /*fungsi untuk menghapus semua isi list ketika tombol clear ditekan*/
        clear.setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        /*fungsi untuk menghapus item dari list ketika tombol delete ditekan*/
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}

