package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student

/**
 * Репозиторий групп студентов
 */
interface GroupsRepository {
    /**
     * получить студента
     * @param id айди студента
     * @return студент
     */
    fun getStudent(id: Int): Student

    /**
     * получить группу
     * @param id айди группы
     * @return группа
     */
    fun getGroup(id: Int): Group

    /**
     * Получить все группы
     * @return список групп
     */
    fun getGroups(): List<Group>
}