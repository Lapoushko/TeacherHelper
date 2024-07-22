package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.data.Student

/**
 * Сервис студента
 */
interface StudentService {
    /**
     * Геттер студента
     */
    fun getStudent(id: Int): Student
}