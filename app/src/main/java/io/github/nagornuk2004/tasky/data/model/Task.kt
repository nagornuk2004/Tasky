package io.github.nagornuk2004.tasky.data.model

import java.time.LocalDate
import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String? = null,
    var due: LocalDate? = null,
    var priority: Priority? = null,
    var tag: String? = null
)
