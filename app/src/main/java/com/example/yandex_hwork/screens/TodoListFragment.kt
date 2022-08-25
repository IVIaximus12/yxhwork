package com.example.yandex_hwork.screens

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yandex_hwork.*
import com.example.yandex_hwork.model.TodoItem

class TodoListFragment: Fragment(R.layout.fragment_todo_list) {

    private lateinit var completedCountText: TextView
    private lateinit var rvAdapter: TodoItemsAdapter

    private var visibilityOn = true
    private val todoItemsRepository: TodoItemsRepository
        get() = (activity?.applicationContext as App).todoItemsRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews(view)
        updateCountCompletedItem()
        initRecyclerView(view)
        initOnClickListeners(view)
    }

    private fun initViews(view: View) {
        completedCountText = view.findViewById(R.id.completedCountText)
    }

    private fun updateCountCompletedItem() {
        var count = 0
        todoItemsRepository.getTodoItems().forEach { if (it.completed) count++}
        completedCountText.text = "$count"
    }

    private fun initOnClickListeners(view: View) {
        view.findViewById<View>(R.id.newTodoItemBtn).setOnClickListener {
            todoItemsRepository.setCurrentItem(TodoItem())
            todoItemsRepository.addTodoItem(TodoItem())
            findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
        }

        val imageButton = view.findViewById<ImageButton>(R.id.visibilityButton)

        imageButton.setOnClickListener {
            if (visibilityOn) {
                visibilityOn = false
                imageButton.setImageResource(R.drawable.ic_visibility_off_button)
                rvAdapter.items = todoItemsRepository.getTodoItems().filter {!it.completed} as MutableList<TodoItem>
                rvAdapter.notifyDataSetChanged()
            } else {
                visibilityOn = true
                imageButton.setImageResource(R.drawable.ic_visibility_button)
                rvAdapter.items = todoItemsRepository.getTodoItems()
                rvAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        rvAdapter = TodoItemsAdapter(object : TodoItemActionListener {
            override fun onTodoItemDelete(todoItem: TodoItem) {
                TODO("Not yet implemented")
            }

            override fun onTodoItemChecked(todoItem: TodoItem) {
                todoItem.completed = !todoItem.completed
                updateCountCompletedItem()
            }

            override fun onTodoItemChange(todoItem: TodoItem) {
                todoItemsRepository.setCurrentItem(todoItem)
                findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
            }

        })
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvAdapter.items = todoItemsRepository.getTodoItems()
        rvAdapter.notifyDataSetChanged()
    }
}