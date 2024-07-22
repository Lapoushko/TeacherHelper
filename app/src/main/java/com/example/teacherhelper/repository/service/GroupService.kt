package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.data.Group

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
}