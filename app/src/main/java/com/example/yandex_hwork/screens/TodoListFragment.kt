package com.example.yandex_hwork.screens

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yandex_hwork.MaybeTask
import com.example.yandex_hwork.R

class TodoListFragment: Fragment(R.layout.fragment_todo_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.listButton).setOnClickListener() {
            findNavController().navigate(R.id.action_todoListFragment_to_todoFragment)
        }

        gettingTodo(view)
    }

    private fun gettingTodo(view: View) {
        parentFragmentManager.setFragmentResultListener(TodoItemFragment.REQUEST_CODE, viewLifecycleOwner) {
                key, data ->
            val newTask = data.getParcelable<MaybeTask>(TodoItemFragment.NEW_TASK)
            // закидываем новую задачу
            if (newTask != null) {
                view.findViewById<Button>(R.id.listButton).text = newTask.taskText
            }
        }
    }
}