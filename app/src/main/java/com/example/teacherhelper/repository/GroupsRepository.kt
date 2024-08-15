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
    suspend fun getStudent(id: Int): Student

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
}