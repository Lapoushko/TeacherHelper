package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.dao.GroupDao
import com.example.teacherhelper.repository.dao.StudentDao
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.service.GroupServiceImpl
import com.example.teacherhelper.repository.service.StudentServiceImpl
import javax.inject.Singleton

/**
 * Репозиторий для работы с группами студентов
 */
@Singleton
class GroupsRepositoryImpl: GroupsRepository {
    //TODO Здесь нужно использовать даггер
    private val groupDao = GroupDao()
    private val studentDao = StudentDao()

    private val groupService = GroupServiceImpl(groupDao)
    private val studentService = StudentServiceImpl(studentDao)

    override suspend fun getStudent(id: Int): Student {
        return studentService.getStudent(id)
    }

    override suspend fun getGroup(id: Int): Group {
        return groupService.getGroup(id)
    }

    override suspend fun getGroups(): List<Group> {
        return groupService.getGroups()
    }

}