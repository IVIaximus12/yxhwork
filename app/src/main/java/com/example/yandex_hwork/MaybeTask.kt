package com.example.yandex_hwork

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaybeTask(val taskText: String, val priority: Int) : Parcelable
