package com.example.teacherhelper.model

/**
 * Группа студентов
 *
 * @property id id группы
 * @property name имя группы
 * @property students список студентов
 * @property description описание группы
 */
data class Group(
    val id: Int,
    val name: String,
    val students: List<Student>,
    val description: String)