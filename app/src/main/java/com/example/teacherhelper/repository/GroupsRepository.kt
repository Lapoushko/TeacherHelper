package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.data.StudentByGroup
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий групп студентов
 */
interface GroupsRepository {
    /**
     * получить группу
     * @param id айди группы
     * @return группа
     */
    fun getGroup(id: Int): Flow<StudentByGroup>

    /**
     * Получить все группы
     * @return список групп
     */
    fun getGroups(): Flow<List<StudentByGroup>>

    /**
     * Добавить новую группу
     * @param group новая группа
     */
    suspend fun insertGroup(group: Group)

    /**
     * Добавить нового студента
     * @param group в какую группу добавить
     * @param student новый студент
     */
    suspend fun insertStudent(group: Group, student: Student)

    /**
     * Обновить группу
     * @param group нужная группа
     */
    suspend fun updateGroup(group: Group)

    /**
     * Обновить студента
     * @param student нужный студент
     */
    suspend fun updateStudent(student: Student)

    /**
     * Удалить группу
     * @param group группа
     */
    suspend fun deleteGroup(group: Group)

    /**
     * Удалить студента
     * @param student студент
     */
    suspend fun deleteStudent(student: Student)

    /**
     * Сбросить всю таблицу
     */
    suspend fun dropDatabase()
}