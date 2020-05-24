package com.example.voicediary.Data

import java.util.*

data class DiaryData(
    val title: String? = "",
    val memo: String? = "",
    val registerTime: Date = Date()
)