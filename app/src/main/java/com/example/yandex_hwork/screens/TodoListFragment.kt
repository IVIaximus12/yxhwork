package com.example.yandex_hwork.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yandex_hwork.*
import com.example.yandex_hwork.model.TodoItem

class TodoListFragment: Fragment(R.layout.fragment_todo_list) {

    private val todoItemsRepository: TodoItemsRepository
        get() = (activity?.applicationContext as App).todoItemsRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecyclerView(view)
        //gettingTodo(view)
        initOnClickListeners(view)
    }

    private fun initOnClickListeners(view: View) {
        view.findViewById<View>(R.id.newTodoItemBtn).setOnClickListener {
            todoItemsRepository.setCurrentItem(TodoItem())
            todoItemsRepository.addTodoItem(TodoItem())
            findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
        }
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = TodoItemsAdapter(object : TodoItemActionListener {
            override fun onTodoItemDelete(todoItem: TodoItem) {
                TODO("Not yet implemented")
            }

            override fun onTodoItemChecked(todoItem: TodoItem) {
                todoItem.completed = !todoItem.completed
            }

            override fun onTodoItemChange(todoItem: TodoItem) {
                todoItemsRepository.setCurrentItem(todoItem)
                //parentFragmentManager.setFragmentResult(NEW_TODO_ITEM_REQUEST, bundleOf())
                findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
            }

        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter.items = todoItemsRepository.getTodoItems()
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val NEW_TODO_ITEM_REQUEST = "NEW_TODO_ITEM_REQUEST"
    }
}