package com.example.teacherhelper.repository.data

import androidx.room.Entity
import javax.inject.Inject

/**
 * Студента
 *
 * @property id id ученика
 * @property name имя ученика
 * @property description описание ученика
 * @author lapoushko
 */
@Entity
data class Student @Inject constructor(
    val id: Int,
    val name: String,
    val description: String
)