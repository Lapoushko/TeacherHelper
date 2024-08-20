package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.dao.GroupDao
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.Student
import javax.inject.Inject

/**
 * Сервис, отвечающий за работу с группой студентов
 */
class GroupServiceImpl @Inject constructor(dao: GroupDao) : GroupService {

    private val groupDao = dao

    override fun getGroup(id: Int): Group {
        return groupDao.getGroup(id)
    }

    override fun getGroups(): List<Group> {
        return groupDao.getGroups()
    }

    override fun saveGroup(group: Group) {
        groupDao.saveGroup(group)
    }

    override fun addStudent(group: Group, student: Student) {
        val newStudents = group.students.toMutableList().apply { add(student) }
        val newGroup = Group(group.id, group.name, newStudents, group.description)
        saveGroup(newGroup)
    }

    override fun addGroup(group: Group) {
        groupDao.addGroup(group)
    }

    override fun editGroup(group: Group) {
        groupDao.saveGroup(group)
    }

    override fun deleteGroup(group: Group) {
        groupDao.deleteGroup(group)
    }
}