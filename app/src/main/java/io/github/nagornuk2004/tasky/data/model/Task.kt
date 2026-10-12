package io.github.nagornuk2004.tasky.data.model

import java.time.LocalDate
import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String?,
    val due: LocalDate?,
    val priority: Priority?
)
