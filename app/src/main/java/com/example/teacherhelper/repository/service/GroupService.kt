package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student

/**
 * Сервис групп
 */
interface GroupService {
    /**
     * Геттер группы
     */
    fun getGroup(id: Int): Group

    /**
     * Получить все группы
     */
    fun getGroups(): List<Group>

    /**
     * Сохранить группу
     */
    fun saveGroup(group: Group)

    /**
     * Добавить студента в группу
     */
    fun addStudent(group: Group, student: Student)

    /**
     * Добавить новую группу
     */
    fun addGroup(group: Group)
}