package com.example.teacherhelper.repository

import com.example.teacherhelper.repository.dao.GroupDao
import com.example.teacherhelper.repository.dao.StudentDao
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.service.GroupServiceImpl
import com.example.teacherhelper.repository.service.StudentServiceImpl

/**
 * Репозиторий для работы с группами студентов
 */
class GroupsRepositoryImpl: GroupsRepository {
    //TODO Здесь нужно использовать даггер
    private val groupDao = GroupDao()
    private val studentDao = StudentDao()

    private val groupService = GroupServiceImpl(groupDao)
    private val studentService = StudentServiceImpl(studentDao)

        //TODO Сделать с корутинами!
    override fun getStudent(id: Int): Student {
        return studentService.getStudent(id)
    }

    override fun getGroup(id: Int): Group {
        return groupService.getGroup(id)
    }

    override fun getGroups(): List<Group> {
        return groupService.getGroups()
    }

}