package com.example.yandex_hwork

import com.example.yandex_hwork.model.Date
import com.example.yandex_hwork.model.Importance
import com.example.yandex_hwork.model.TodoItem

class TodoItemsRepository {

    private var todoItems = mutableListOf<TodoItem>()
    private lateinit var currentItemId: String
    private var lastId = 110

    fun getTodoItems()  = todoItems

    fun addTodoItem(todoItem: TodoItem) {
        todoItems.add(todoItem)
    }

    fun deleteTodoItem(todoItem: TodoItem) {
        todoItems.removeAt(todoItems.indexOfFirst { it.id == todoItem.id })
    }

    fun editTodoItem(todoItem: TodoItem) {
        todoItems[todoItems.indexOfFirst { it.id == currentItemId }] = todoItem
    }

    fun setCurrentItem(todoItem: TodoItem) {
        currentItemId = todoItem.id
    }

    fun getCurrentItem() = todoItems[todoItems.indexOfFirst { it.id == currentItemId }]

    fun getNextId() : String {
        lastId++
        return "$lastId"
    }

    init {
        todoItems.add(
            TodoItem("100",
                "Какая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задачаКакая-то задача",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("101",
                "Какая-то задача 2Какая-то задача 2Какая-то задача 2Какая-то задача 2Какая-то задача 2Какая-то задача 2",
                Importance.High,
                DateService.getCurrentDate(),
                true,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("102",
                "Какая-то задача 3sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
                Importance.High,
                Date(-1, -1, -1),
                true,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("103",
                "Какая-то задача 4",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("104",
                "Какая-то задача 5",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("105",
                "Какая-то задача 6",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("106",
                "Какая-то задача 7",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("107",
                "Купить что-то",
                Importance.Low,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("108",
                "Купить что-то? где-то, зачем-то, но зачем не очень понятно",
                Importance.Low,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("109",
                "1)\tреализовать дата класс Дела (TodoItem)\n" +
                        "2)\tреализовать класс-хранилище списка дел (TodoItemsRepository)\n" +
                        "3)\tреализовать UI ячейки Дела\n" +
                        "4)\tреализовать UI списка Дел\n" +
                        "5)\tреализовать кнопку добавления дела\n" +
                        "6)\tреализовать экран добавления/редактирования дела с обновлением данных\n",
                Importance.Normal,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
        todoItems.add(
            TodoItem("110",
                "UI списка дел:\n" +
                        "1)\tдолжен быть реализован через RecyclerView\n" +
                        "2)\tв качестве ячеек должен выступать UI ячейки дела (из предыдущего пункта)\n" +
                        "3)\tсписок должен быть вертикальным и занимать всю ширину и высоту экрана\n" +
                        "4)\tпередать в adapter список дел из TodoItemsRepository и отобразить\n",
                Importance.High,
                DateService.getCurrentDate(),
                false,
                DateService.getCurrentDate(),
                DateService.getCurrentDate())
        )
    }
}