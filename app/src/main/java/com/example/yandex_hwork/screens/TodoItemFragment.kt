package com.example.yandex_hwork.screens

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yandex_hwork.MaybeTask
import com.example.yandex_hwork.R

class TodoItemFragment: Fragment(R.layout.fragment_todo_item) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.closeButton).setOnClickListener() {
            exampleNewTask()
            findNavController().popBackStack()
        }
    }

    //Передача дела в список дел
    private fun exampleNewTask() {
        val task = MaybeTask("Какая-то новая задача", 123)
        parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(NEW_TASK to task))
    }

    companion object {
        const val REQUEST_CODE = "NEW_TASK_REQUEST_CODE"
        const val NEW_TASK = "NEW_TASK"
    }
}