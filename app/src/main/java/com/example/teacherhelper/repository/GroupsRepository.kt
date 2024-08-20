package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student

/**
 * Репозиторий групп студентов
 */
interface GroupsRepository {
    /**
     * получить группу
     * @param id айди группы
     * @return группа
     */
    suspend fun getGroup(id: Int): Group

    /**
     * Получить все группы
     * @return список групп
     */
    suspend fun getGroups(): List<Group>

    /**
     * Добавить студента в группу
     */
    suspend fun addStudent(group: Group, student: Student)

    /**
     * Добавить новую группу
     */
    suspend fun addGroup(group: Group)

    /**
     * Редактировать группу
     */
    suspend fun editGroup(group: Group)

    /**
     * Удалить группу
     */
    suspend fun deleteGroup(group: Group)
}