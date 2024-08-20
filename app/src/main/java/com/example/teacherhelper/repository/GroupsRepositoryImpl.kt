package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.service.GroupServiceImpl
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с группами студентов
 */
@Singleton
class GroupsRepositoryImpl @Inject constructor() : GroupsRepository {
    @Inject
    lateinit var groupService: GroupServiceImpl

    override suspend fun getGroup(id: Int): Group {
        return groupService.getGroup(id)
    }

    override suspend fun getGroups(): List<Group> {
        return groupService.getGroups()
    }

    override suspend fun addStudent(group: Group, student: Student) {
        groupService.addStudent(group, student)
    }

    override suspend fun addGroup(group: Group) {
        groupService.addGroup(group)
    }

    override suspend fun editGroup(group: Group) {
        groupService.editGroup(group)
    }

    override suspend fun deleteGroup(group: Group) {
        groupService.deleteGroup(group)
    }
}