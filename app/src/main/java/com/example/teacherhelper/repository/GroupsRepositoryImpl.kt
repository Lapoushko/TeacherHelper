package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.dao.Dao
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.data.StudentByGroup
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с группами студентов
 */
@Singleton
class GroupsRepositoryImpl @Inject constructor(private val groupDao: Dao) : GroupsRepository {

    override suspend fun getGroup(id: Int): StudentByGroup {
        return groupDao.getGroup(id)
    }

    override suspend fun getGroups(): List<StudentByGroup> {
        return groupDao.getGroups()
    }

    override suspend fun deleteGroup(group: Group) {
        groupDao.deleteGroup(group)
    }

    override suspend fun insertGroup(group: Group) {
        groupDao.insertGroup(group)
    }

    override suspend fun updateGroup(group: Group) {
        groupDao.updateGroup(group)
    }

    override suspend fun insertStudent(group: Group, student: Student) {
        groupDao.insertStudent(group, student)
    }

    override suspend fun dropDatabase() {
        groupDao.dropDatabase()
    }
}