package com.example.yandex_hwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yandex_hwork.model.TodoItem

interface TodoItemActionListener {

    fun onTodoItemDelete(todoItem: TodoItem)

    fun onTodoItemChecked(todoItem: TodoItem)

    fun onTodoItemChange(todoItem: TodoItem)
}

class TodoItemsAdapter(
    private val actionListener: TodoItemActionListener
) : RecyclerView.Adapter<TodoItemsAdapter.TodoViewHolder>(), View.OnClickListener {

    var items = mutableListOf<TodoItem>()

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = TodoViewHolder(layoutInflater.inflate(R.layout.todo_item, parent, false))

        viewHolder.itemView.setOnClickListener(this)
        viewHolder.itemView.findViewById<CheckBox>(R.id.checkBoxTodoItem).setOnClickListener(this)

        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoItem = items[position]
        holder.itemView.tag = todoItem
        holder.itemView.apply {
            val checkBoxTodoItem = findViewById<CheckBox>(R.id.checkBoxTodoItem)
            checkBoxTodoItem.isChecked = todoItem.completed
            checkBoxTodoItem.tag = todoItem
            findViewById<TextView>(R.id.textTodoItem).text = todoItem.text
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onClick(v: View) {
        val todoItem = v.tag as TodoItem
        when (v.id) {
            R.id.checkBoxTodoItem -> {

            }
            else -> {
                actionListener.onTodoItemChange(todoItem)
            }
        }
    }
}