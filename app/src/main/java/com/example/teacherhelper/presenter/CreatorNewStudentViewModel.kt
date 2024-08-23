package com.example.teacherhelper.presenter

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
 * View Model для добавления нового студента в группу
 * @param groupsRepository репозиторий для работы с данными
 */
@HiltViewModel
class CreatorNewStudentViewModel @Inject constructor(
    private val groupsRepository: GroupsRepositoryImpl
) : ViewModel() {


    init {
        Log.e(Constants.LOG_TAG, "init creator student vm")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "creator student vm cleared")
        super.onCleared()
    }

    /**
     * Добавить студента в группу
     * @param groupId айди нужной группы
     * @param name имя студента
     * @param description описание студента
     */
    fun addStudent(groupId: Int, name: String, description: String) {
        viewModelScope.launch {
            val group = groupsRepository.getGroup(groupId)
            group.collect{
                val student = Student(
                    0,
                    name, description,
                    groupId
                )
                if (student.name.isNotEmpty() && student.description.isNotEmpty()) {
                    groupsRepository.insertStudent(
                        it.group,
                        student
                    )
                }
            }
        }
    }
}