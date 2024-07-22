package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.dao.GroupDao
import com.example.teacherhelper.repository.data.Group

/**
 * Сервис, отвечающий за работу с группой студентов
 */
class GroupServiceImpl(dao: GroupDao) : GroupService {

    private val groupDao = dao

    override fun getGroup(id: Int): Group {
        return groupDao.getGroup(id)
    }

    override fun getGroups(): List<Group> {
        return groupDao.getGroups()
    }
}