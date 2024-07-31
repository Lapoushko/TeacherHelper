package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.service.GroupServiceImpl
import com.example.teacherhelper.repository.service.StudentServiceImpl
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с группами студентов
 */
@Singleton
class GroupsRepositoryImpl @Inject constructor(): GroupsRepository {
    @Inject lateinit var groupService: GroupServiceImpl

    @Inject lateinit var studentService: StudentServiceImpl

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