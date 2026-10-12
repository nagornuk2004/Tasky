package io.github.nagornuk2004.tasky.data.model

import java.util.UUID

data class Project(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    val tasks: List<Task> = emptyList(),
)
