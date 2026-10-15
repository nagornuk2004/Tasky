package io.github.nagornuk2004.tasky.data.model

enum class Priority(val label: String) {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    companion object {
        fun fromString(label: String): Priority {
            return entries.first { it.label == label }
        }
    }
}