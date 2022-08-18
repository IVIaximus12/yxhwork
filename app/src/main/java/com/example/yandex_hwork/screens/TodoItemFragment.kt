package com.example.yandex_hwork.screens

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yandex_hwork.*
import com.example.yandex_hwork.model.Date
import com.example.yandex_hwork.model.Importance
import com.example.yandex_hwork.model.TodoItem

class TodoItemFragment: Fragment(R.layout.fragment_todo_item) {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var closeButton: ImageButton
    private lateinit var dateSwitch: SwitchCompat
    private lateinit var todoText: EditText
    private lateinit var dateText: TextView
    private lateinit var whenText: TextView
    private lateinit var saveButtonText: TextView
    private lateinit var importantChooseText: TextView
    private lateinit var deleteButtonText: TextView

    private lateinit var todoItem: TodoItem
    private lateinit var originalTodoItem: TodoItem

    private val todoItemsRepository: TodoItemsRepository
        get() = (activity?.applicationContext as App).todoItemsRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        originalTodoItem  = todoItemsRepository.getCurrentItem()
        todoItem = originalTodoItem.copy()


        initViews(view)
        initContent()
        initListeners()
        initDatePicker()
    }

    private fun initContent() {

        deleteButtonText.isEnabled = todoItem.id != "-1"

        todoText.setText(todoItem.text)

        when (todoItem.importance) {
            Importance.High -> {
                importantChooseText.text = todoItem.importance.text
                importantChooseText.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_red))
            }
            Importance.Normal -> {
                importantChooseText.text = todoItem.importance.text
                importantChooseText.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_tertiary))
            }
            Importance.Low -> {
                importantChooseText.text = todoItem.importance.text
                importantChooseText.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_tertiary))
            }
        }

        initDateContent()
    }

    private fun initDateContent() {
        if (todoItem.deadline.month != -1) {
            val realMonth = todoItem.deadline.month + 1
            val date = "${todoItem.deadline.dayOfMonth}/$realMonth/${todoItem.deadline.year}"
            dateText.text = date
            dateSwitch.isChecked = true
        } else dateSwitch.isChecked = false
    }

    private fun initViews(view: View) {
        dateText = view.findViewById(R.id.dateText)
        whenText = view.findViewById(R.id.whenText)
        closeButton = view.findViewById(R.id.closeButton)
        saveButtonText = view.findViewById(R.id.saveButtonText)
        todoText = view.findViewById(R.id.todoText)
        importantChooseText = view.findViewById(R.id.importantChooseText)
        deleteButtonText = view.findViewById(R.id.deleteButtonText)
        dateSwitch = view.findViewById(R.id.dateSwitch)
    }

    private fun initListeners() {
        closeButton.setOnClickListener {
            if (originalTodoItem.text.isEmpty()) todoItemsRepository.deleteTodoItem(todoItem)
            findNavController().popBackStack()
        }

        saveButtonText.setOnClickListener {
            todoItem.id = todoItemsRepository.getNextId()
            todoItemsRepository.editTodoItem(todoItem)
            findNavController().popBackStack()
        }

        deleteButtonText.setOnClickListener {
            todoItemsRepository.deleteTodoItem(todoItem)
            findNavController().popBackStack()
        }

        todoText.doAfterTextChanged { text ->
            todoItem.text = text.toString()
            readyToSave()
        }

        dateText.setOnClickListener {
            datePickerDialog.show()
        }

        dateSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                dateText.visibility = View.VISIBLE

                if (originalTodoItem.deadline.year != -1) {
                    todoItem.deadline = originalTodoItem.deadline
                } else todoItem.deadline = DateService.getCurrentDate()

                dateText.text = DateService.getString(todoItem.deadline)
                readyToSave()
            } else {
                dateText.visibility = View.INVISIBLE
                todoItem.deadline = Date(-1,-1,-1)
                readyToSave()
            }
        }
    }

    private fun readyToSave() {
        saveButtonText.isEnabled = originalTodoItem != todoItem && todoItem.text.isNotEmpty()
    }

    private fun initDatePicker() {

        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val realMonth = month + 1
            val date = "$dayOfMonth/$realMonth/$year"
            dateText.text = date
            todoItem.deadline = Date(year, month, dayOfMonth)
            readyToSave()
        }

        val currentDate = DateService.getCurrentDate()

        datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            currentDate.year,
            currentDate.month,
            currentDate.dayOfMonth)
    }

    //Передача дела в список дел
    private fun exampleNewTask() {
        val task = MaybeTask("Какая-то новая задача", 123)
        parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(NEW_TASK to task))
    }

    companion object {
        const val REQUEST_CODE = "NEW_TASK_REQUEST_CODE"
        const val NEW_TASK = "NEW_TASK"

        const val NEW_ITEM_REQUEST_CODE = "NEW_ITEM_REQUEST_CODE"
        const val SAVE_ITEM_REQUEST_CODE = "SAVE_ITEM_REQUEST_CODE"
    }
}