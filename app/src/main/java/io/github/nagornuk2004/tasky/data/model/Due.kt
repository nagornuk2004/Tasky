package io.github.nagornuk2004.tasky.data.model

import java.time.LocalDate
import java.time.LocalDateTime

enum class Due(val label: String) {
    TODAY("Today"),
    TOMORROW("Tomorrow");

    companion object {
        fun getTime(dueTime: Due): LocalDateTime {
            return when (dueTime) {
                TODAY -> LocalDate.now().atStartOfDay()
                TOMORROW -> LocalDate.now().atStartOfDay().plusDays(1)
            }
        }
        fun fromString(label: String): Due {
            return Due.entries.first { it.label == label }
        }
    }
}