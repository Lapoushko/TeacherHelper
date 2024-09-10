package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.dao.Dao
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.data.StudentByGroup
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с группами студентов
 */
@Singleton
class GroupsRepositoryImpl @Inject constructor(private val groupDao: Dao) : GroupsRepository {
    override fun getGroup(id: Int): Flow<StudentByGroup> {
        return groupDao.getGroup(id)
    }

    override fun getGroups(): Flow<List<StudentByGroup>> {
        return groupDao.getGroups()
    }

    override suspend fun deleteGroup(group: Group) {
        groupDao.deleteGroup(group)
    }

    override suspend fun deleteStudent(student: Student) {
        groupDao.deleteStudent(student)
    }

    override suspend fun insertGroup(group: Group) {
        groupDao.insertGroup(group)
    }

    override suspend fun updateGroup(group: Group) {
        groupDao.updateGroup(group)
    }

    override suspend fun updateStudent(student: Student) {
        groupDao.updateStudent(student)
    }

    override suspend fun insertStudent(group: Group, student: Student) {
        groupDao.insertStudent(group, student)
    }

    override suspend fun dropDatabase() {
        groupDao.dropDatabase()
    }
}