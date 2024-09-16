package com.example.teacherhelper.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * VM для редактирования студентов
 * @param groupsRepository репозиторий для работы с данными
 */
@HiltViewModel
class EditStudentViewModel @Inject constructor(
    private val groupsRepository: GroupsRepositoryImpl
) : ViewModel(){
    init {
        Log.e(Constants.LOG_TAG, "init editor student vm")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "create editor group vm cleared")
        super.onCleared()
    }

    /**
     * Редактирование группы
     * @param studentId айди группы
     * @param name новое имя студента
     * @param description новое описание студента
     */
    fun editGroup(groupId: Int ,name: String, description: String, studentId: Int) {
        viewModelScope.launch {
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val student = Student(
                    studentId = studentId,
                    name = name,
                    description = description,
                    groupId = groupId
                )
                groupsRepository.updateStudent(student)
            }
        }
    }
}