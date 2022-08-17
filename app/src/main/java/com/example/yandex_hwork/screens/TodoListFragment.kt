package com.example.yandex_hwork.screens

import android.os.Bundle
import android.view.View
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
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TodoItemsAdapter(object : TodoItemActionListener {
            override fun onTodoItemDelete(todoItem: TodoItem) {
                TODO("Not yet implemented")
            }

            override fun onTodoItemChecked(todoItem: TodoItem) {
                TODO("Not yet implemented")
            }

            override fun onTodoItemChange(todoItem: TodoItem) {
                findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
            }

        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter.items = todoItemsRepository.getTodoItems()
        adapter.notifyDataSetChanged()
    }

    /*private fun gettingTodo(view: View) {

        view.findViewById<Button>(R.id.listButton).setOnClickListener() {
            findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
        }

        parentFragmentManager.setFragmentResultListener(TodoItemFragment.REQUEST_CODE, viewLifecycleOwner) {
                key, data ->
            val newTask = data.getParcelable<MaybeTask>(TodoItemFragment.NEW_TASK)
            // закидываем новую задачу
            if (newTask != null) {
                view.findViewById<Button>(R.id.listButton).text = newTask.taskText
            }
        }
    }*/
}