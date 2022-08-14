package com.example.yandex_hwork.screens

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yandex_hwork.MaybeTask
import com.example.yandex_hwork.R
import java.util.*

class TodoItemFragment: Fragment(R.layout.fragment_todo_item) {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var txtBtn: TextView
    private lateinit var dateText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.closeButton).setOnClickListener() {
            exampleNewTask()
            findNavController().popBackStack()
        }
        view.findViewById<TextView>(R.id.saveButtonText).setOnClickListener() {
            exampleNewTask()
            findNavController().popBackStack()
        }

        view.findViewById<EditText>(R.id.todoText).doAfterTextChanged { text ->
            view.findViewById<TextView>(R.id.saveButtonText).isEnabled = !text.isNullOrEmpty()
        }






        initDatePicker()
        txtBtn = view.findViewById(R.id.whenText)
        txtBtn.setOnClickListener() {
            datePickerDialog.show()
        }
        dateText = view.findViewById(R.id.dateText)
    }

    private fun initDatePicker() {
        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val realMonth = month + 1
            val date = "$dayOfMonth/$realMonth/$year"
            dateText.text = date
        }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(requireContext(), dateListener, year, month, day)
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