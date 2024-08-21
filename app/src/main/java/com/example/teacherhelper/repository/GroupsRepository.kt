package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.data.StudentByGroup

/**
 * Репозиторий групп студентов
 */
interface GroupsRepository {
    /**
     * получить группу
     * @param id айди группы
     * @return группа
     */
    suspend fun getGroup(id: Int): StudentByGroup

    /**
     * Получить все группы
     * @return список групп
     */
    suspend fun getGroups(): List<StudentByGroup>

    /**
     * Удалить группу
     */
    suspend fun deleteGroup(group: Group)

    /**
     * Добавить новую группу
     */
    suspend fun insertGroup(group: Group)

    /**
     * Обновить группу
     */
    suspend fun updateGroup(group: Group)

    /**
     * Добавить нового студента
     */
    suspend fun insertStudent(group: Group, student: Student)

    /**
     * Сбросить всю таблицу
     */
    suspend fun dropDatabase()
}