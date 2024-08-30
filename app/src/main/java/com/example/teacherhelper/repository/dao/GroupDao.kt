package com.example.teacherhelper.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.data.StudentByGroup
import kotlinx.coroutines.flow.Flow

/**
 * Функции базы данных
 */
@Dao
interface Dao{
    /**
     * Добавить группу
     * @param group новая группа студентов
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    /**
     * Добавить студента в группу
     * @param group в какую группу вставить студента
     * @param student новый студент
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(group: Group, student: Student)

    /**
     * Обновить группу
     * @param group новая группа студентов
     */
    @Update
    suspend fun updateGroup(group: Group)

    /**
     * Обновить студента
     * @param student новый студент
     */
    @Update
    suspend fun updateStudent(student: Student)

    /**
     * Удалить группу
     * @param group нужная группа
     */
    @Delete
    suspend fun deleteGroup(group: Group)

    /**
     * Удалить студента
     * @param student нужный студент
     */
    @Delete
    suspend fun deleteStudent(student: Student)

    /**
     * Сбросить таблицу groups
     */
    @Query("DELETE FROM groups")
    suspend fun dropDatabase()

    /**
     * Получить все группы
     * @return Список групп моста связи Student и Group
     */
    @Transaction
    @Query("SELECT * FROM groups")
    fun getGroups() : Flow<List<StudentByGroup>>

    /**
     * Получить нужную группу
     * @param id Идентификатор нужной группы
     * @return группу моста связи Student и Group
     */
    @Transaction
    @Query("SELECT * FROM groups WHERE id = :id")
    fun getGroup(id: Int) : Flow<StudentByGroup>
}