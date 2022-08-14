package com.example.yandex_hwork.screens

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yandex_hwork.DateService
import com.example.yandex_hwork.MaybeTask
import com.example.yandex_hwork.R

class TodoItemFragment: Fragment(R.layout.fragment_todo_item) {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var closeButton: ImageButton
    private lateinit var dateText: TextView
    private lateinit var whenText: TextView
    private lateinit var saveButtonText: TextView
    private lateinit var todoText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        initDatePicker()
    }

    private fun initViews(view: View) {
        dateText = view.findViewById(R.id.dateText)
        whenText = view.findViewById(R.id.whenText)
        closeButton = view.findViewById(R.id.closeButton)
        saveButtonText = view.findViewById(R.id.saveButtonText)
        todoText = view.findViewById(R.id.todoText)
    }

    private fun initListeners() {
        closeButton.setOnClickListener() {
            exampleNewTask()
            findNavController().popBackStack()
        }

        saveButtonText.setOnClickListener() {
            exampleNewTask()
            findNavController().popBackStack()
        }

        todoText.doAfterTextChanged { text ->
            saveButtonText.isEnabled = !text.isNullOrEmpty()
        }

        whenText.setOnClickListener() {
            datePickerDialog.show()
        }
    }

    private fun initDatePicker() {

        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val realMonth = month + 1
            val date = "$dayOfMonth/$realMonth/$year"
            dateText.text = date
        }

        val currentDate = DateService.getCurrentDate()

        datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            currentDate.year,
            currentDate.month,
            currentDate.day)
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