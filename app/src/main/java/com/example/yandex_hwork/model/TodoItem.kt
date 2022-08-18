package com.example.yandex_hwork.model

data class TodoItem(
    var id: String = "-1",
    var text: String = "",
    var importance: Importance = Importance.Normal,
    var deadline: Date = Date(-1,-1,-1),
    var completed: Boolean = false,
    var dateCreation: Date = Date(-1,-1,-1),
    var dateChange: Date = Date(-1,-1,-1)
)

enum class Importance(val text: String) {
    Normal("Нет"),
    High("!! Высокий"),
    Low("Низкий")
}
