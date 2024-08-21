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

//TODO подключить базу данных для работы с данными

@Dao
interface Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(group: Group, student: Student)

    @Update
    suspend fun updateGroup(group: Group)

    @Delete
    suspend fun deleteGroup(group: Group)

    @Query("DELETE FROM groups")
    suspend fun dropDatabase()

    @Transaction
    @Query("SELECT * FROM groups")
    suspend fun getGroups() : List<StudentByGroup>

    @Transaction
    @Query("SELECT * FROM groups WHERE id = :id")
    suspend fun getGroup(id: Int) : StudentByGroup
}