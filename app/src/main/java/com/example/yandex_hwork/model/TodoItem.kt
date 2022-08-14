package com.example.yandex_hwork.model

data class TodoItem(
    val id: String,
    var text: String,
    var importance: Importance,
    var deadline: Date,
    var completed: Boolean,
    var dateCreation: Date,
    var dateChange: Date
)

enum class Importance(val text: String) {
    Normal("Нет"),
    High("!! Высокий"),
    Low("Низкий")
}
