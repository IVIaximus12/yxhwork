package com.example.yandex_hwork

import com.example.yandex_hwork.model.Importance
import com.example.yandex_hwork.model.TodoItem

class TodoItemsRepository {

    private var todoItems = mutableListOf<TodoItem>()

    fun getTodoItems()  = todoItems

    fun addTodoItem(todoItem: TodoItem) {
        todoItems.add(todoItem)
    }

    fun deleteTodoItem(todoItem: TodoItem) {
        todoItems.remove(todoItem)
    }

    init {
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задача",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задача 2Какая-то задача 2Какая-то задача 2Какая-то задача 2Какая-то задача 2Какая-то задача 2",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задача 3sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задача 4",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задача 5",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задача 6",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("1234567",
                "Какая-то задача 7",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
    }
}