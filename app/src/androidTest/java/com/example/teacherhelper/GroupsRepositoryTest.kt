package com.example.teacherhelper

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.teacherhelper.repository.GroupsRepository
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.dao.Dao
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.GroupDatabase
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.repository.data.StudentByGroup
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test


/**
 * Тестирование репозитория групп
 */
class GroupsRepositoryTest {
    private lateinit var database: GroupDatabase
    private lateinit var groupDao: Dao

    private lateinit var groupsRepository: GroupsRepository

    private val testGroup = Group(
        id = 1,
        name = TEST_NAME_GROUP,
        description = TEST_DESCRIPTION_GROUP
    )

    private val testStudent = Student(
        studentId = 1,
        name = TEST_NAME_STUDENT_1,
        description = TEST_DESCRIPTION_STUDENT_1,
        groupId = 1
    )

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GroupDatabase::class.java
        ).allowMainThreadQueries().build()
        groupDao = database.dao()
        groupsRepository = GroupsRepositoryImpl(groupDao)
    }

    /**
     * Выход из бд
     */
    @After
    fun teardown() {
        database.close()
    }

    /**
     * тест вставки новой группы
     */
    @Test
    fun testInsertNewGroup() {
        runBlocking {
            groupsRepository.insertGroup(testGroup)
            assertEquals(1, groupsRepository.getGroups().first().size)
        }
    }

    /**
     * Тест вставки нового студента
     */
    @Test
    fun testInsertNewStudent() {
        runBlocking {
            groupsRepository.insertGroup(testGroup)
            groupsRepository.insertStudent(testGroup, testStudent)
            assertEquals(testStudent, groupsRepository.getGroups().first()[0].students[0])
        }
    }

    /**
     * Тест вставки нового студента с айди существующего
     */
    @Test
    fun testNewStudentInsertUpdate() {
        runBlocking {
            val student2 = Student(
                studentId = 1,
                name = TEST_NAME_STUDENT_2,
                description = TEST_DESCRIPTION_STUDENT_2,
                groupId = 1
            )

            groupsRepository.insertGroup(testGroup)
            groupsRepository.insertStudent(testGroup, testStudent)
            groupsRepository.insertStudent(testGroup, student2)

            assertEquals(student2, groupsRepository.getGroups().first()[0].students[0])
        }
    }

    /**
     * Тест получения групп
     */
    @Test
    fun testGetGroups() {
        runBlocking {
            val studentByGroup = StudentByGroup(
                testGroup,
                listOf()
            )

            groupsRepository.insertGroup(testGroup)

            assertEquals(listOf(studentByGroup), groupsRepository.getGroups().first())
        }
    }

    /**
     * Тест получения групп без вставки
     */
    @Test
    fun testGetGroupsWithNull(){
        runBlocking {
            assertEquals(listOf<StudentByGroup>(),groupsRepository.getGroups().first())
        }
    }

    /**
     * Тест получения группы по id
     */
    @Test
    fun testGetGroupById(){
        runBlocking {
            groupsRepository.insertGroup(testGroup)

            assertEquals(StudentByGroup(testGroup, listOf()), groupsRepository.getGroup(1).first())
            assertNull(groupsRepository.getGroup(0).first())
            assertNull(groupsRepository.getGroup(-10000).first())
            assertNull(groupsRepository.getGroup(10000).first())
        }
    }

    /**
     * Тест обновления группы
     */
    @Test
    fun testUpdateGroup(){
        runBlocking {
            val group2 = Group(
                id = 1,
                name = "name",
                description = "description"
            )

            groupsRepository.insertGroup(testGroup)
            groupsRepository.updateGroup(group2)

            assertEquals(group2, groupsRepository.getGroup(1).first().group)
        }
    }

    /**
     * Тест обновления группы после удаления
     */
    @Test
    fun testUpdateGroupAfterDelete(){
        runBlocking {
            val group2 = Group(
                id = 1,
                name = "name",
                description = "description"
            )

            groupsRepository.insertGroup(testGroup)

            groupsRepository.deleteGroup(testGroup)

            groupsRepository.updateGroup(group2)

            assertNull(groupsRepository.getGroup(1).first())
        }
    }

    /**
     * Тест обновления данных студента
     */
    @Test
    fun testUpdateStudent(){
        runBlocking {
            val student2 = Student(
                studentId = 1,
                name = TEST_NAME_STUDENT_2,
                description = TEST_DESCRIPTION_STUDENT_2,
                groupId = testGroup.id
            )

            groupsRepository.insertGroup(testGroup)
            groupsRepository.insertStudent(testGroup, testStudent)
            groupsRepository.updateStudent(student2)

            assertEquals(student2, groupsRepository.getGroup(1).first().students[0])
        }
    }

    /**
     * Тест удаления группы
     */
    @Test
    fun testDeleteGroup(){
        runBlocking {
            groupsRepository.insertGroup(testGroup)
            groupsRepository.deleteGroup(testGroup)

            assertEquals(0, groupsRepository.getGroups().first().size)
        }
    }

    /**
     * Тест удаления несуществующей группы
     */
    @Test
    fun testDeleteGroupIfNotExists(){
        runBlocking {
            groupsRepository.deleteGroup(testGroup)

            assertEquals(0, groupsRepository.getGroups().first().size)
        }
    }

    /**
     * Тест удаления студента
     */
    @Test
    fun testDeleteStudent(){
        runBlocking {
            groupsRepository.insertGroup(group = testGroup)
            groupsRepository.insertStudent(testGroup, testStudent)
            groupsRepository.deleteStudent(testStudent)

            assertEquals(0, groupsRepository.getGroup(1).first().students.size)
        }
    }

    ///TODO Подумать ещё над тестами
    companion object {
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
    }
}