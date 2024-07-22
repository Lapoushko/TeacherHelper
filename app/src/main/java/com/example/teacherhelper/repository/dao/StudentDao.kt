package com.example.teacherhelper.repository.dao

import com.example.teacherhelper.repository.data.ExampleData
import com.example.teacherhelper.repository.data.Student

/**
 * Дао для работы со студентом
 */
class StudentDao {
    private val data = ExampleData()

    /**
     * Получить студента
     * @param idStudent айди студента
     */
    fun getStudent(idStudent: Int): Student{
        //TODO Получать информацию о студенте независимо от номера группы
        return data.groups[0].students[idStudent]
    }
}