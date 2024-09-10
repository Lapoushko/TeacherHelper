package com.example.teacherhelper.repository.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.GroupDatabase
import com.example.teacherhelper.repository.data.Student
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Константы для группы
 */
const val TEST_NAME_GROUP = "Тестовое имя группы"
const val TEST_DESCRIPTION_GROUP = "Тестовое описание группы"

/**
 * Константы для учеников
 */
const val TEST_NAME_STUDENT_1 = "Тестовое имя ученика 1"
const val TEST_NAME_STUDENT_2 = "Тестовое имя ученика 2"

const val TEST_DESCRIPTION_STUDENT_1 = "Тестовое описание ученика 1"
const val TEST_DESCRIPTION_STUDENT_2 = "Тестовое описание ученика 2"

/**
 * Тестирование DAO
 */
class GroupDaoTest {
    private lateinit var database: GroupDatabase
    private lateinit var groupDao: Dao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GroupDatabase::class.java
        ).allowMainThreadQueries().build()
        groupDao = database.dao()
    }

    /**
     * Выход из бд
     */
    @After
    fun teardown() {
        database.close()
    }

    /**
     * Тестирование получения всех групп
     */
    @Test
    fun testGetGroups() = runBlocking {
        Assert.assertEquals(0, groupDao.getGroups().first().size)
        Assert.assertNotEquals(1, groupDao.getGroups().first().size)
    }

    /**
     * Тест вставки новой группы в список групп
     */
    @Test
    fun testInsertGroup() = runBlocking {
        val expectedGroup = Group(id = 1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )

        groupDao.insertGroup(expectedGroup)

        Assert.assertEquals(1, groupDao.getGroups().first().size)
    }

    /**
     * тест поиска групп по айди
     */
    @Test
    fun testGetGroupById() = runBlocking {
        val insertedGroup = Group(
            1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )
        val insertedGroup2 = Group(
            2,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )

        groupDao.insertGroup(insertedGroup)
        groupDao.insertGroup(insertedGroup2)

        Assert.assertEquals(insertedGroup, groupDao.getGroup(1).first().group)
        Assert.assertEquals(insertedGroup2, groupDao.getGroup(2).first().group)
    }

    /**
     * Тест обновления группы
     */
    @Test
    fun testUpdateGroup() = runBlocking {
        var insertedGroup = Group(
            1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )
        val newGroup = Group(
            1,
            name = "name",
            description = "description"
        )

        groupDao.insertGroup(insertedGroup)
        groupDao.updateGroup(newGroup)
        insertedGroup = groupDao.getGroup(1).first().group

        Assert.assertEquals(newGroup, insertedGroup)
    }

    /**
     * Тест удаления группы и списков групп
     */
    @Test
    fun testDeleteGroupFromGroups() = runBlocking {
        val insertedGroup = Group(
            1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )

        groupDao.insertGroup(group = insertedGroup)
        groupDao.deleteGroup(group = insertedGroup)

        Assert.assertEquals(0, groupDao.getGroups().first().size)
    }

    /**
     * Тест вставки нового студента в группу
     */
    @Test
    fun testInsertStudentInGroup() = runBlocking {
        val insertedGroup = Group(
            1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )
        val insertedStudent = Student(
            1,
            name = TEST_NAME_STUDENT_1,
            description = TEST_DESCRIPTION_STUDENT_1,
            1
        )

        groupDao.insertGroup(insertedGroup)
        groupDao.insertStudent(insertedGroup, insertedStudent)

        Assert.assertEquals(insertedStudent, groupDao.getGroup(1).first().students[0])
        Assert.assertNull(groupDao.getGroup(0).first())
        Assert.assertEquals(1, groupDao.getGroup(1).first().students.size)
    }

    /**
     * Тест обновления студента в группе
     */
    @Test
    fun testUpdateStudentInGroup() = runBlocking {
        val insertedGroup = Group(
            1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )
        val insertedStudent = Student(
            1,
            name = TEST_NAME_STUDENT_1,
            description = TEST_DESCRIPTION_STUDENT_1,
            1
        )

        val newStudent = Student(
            1,
            name = TEST_NAME_STUDENT_2,
            description = TEST_DESCRIPTION_STUDENT_2,
            1
        )

        groupDao.insertGroup(insertedGroup)
        groupDao.insertStudent(insertedGroup, insertedStudent)
        groupDao.updateStudent(newStudent)

        Assert.assertNotEquals(insertedGroup, groupDao.getGroup(1).first().students[0])
    }

    /**
     * Тест удаления студента из группы
     */
    @Test
    fun testDeleteStudentFromGroup() = runBlocking {
        val insertedGroup = Group(
            1,
            name = TEST_NAME_GROUP,
            description = TEST_DESCRIPTION_GROUP
        )
        val insertedStudent = Student(
            1,
            name = TEST_NAME_STUDENT_1,
            description = TEST_DESCRIPTION_STUDENT_1,
            1
        )

        groupDao.insertGroup(insertedGroup)
        groupDao.insertStudent(insertedGroup, insertedStudent)
        groupDao.deleteStudent(insertedStudent)

        Assert.assertEquals(0, groupDao.getGroup(1).first().students.size)
    }
}