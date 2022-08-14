package com.example.yandex_hwork

import com.example.yandex_hwork.model.Date
import java.util.*

class DateService {

    companion object {
        fun getCurrentDate(): Date {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return Date(year, month, day)
        }
    }
}