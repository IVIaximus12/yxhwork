package com.example.yandex_hwork

import android.app.Application

class App: Application() {

    val todoItemsRepository = TodoItemsRepository()
}