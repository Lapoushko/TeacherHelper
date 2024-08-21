package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * VM для GroupDetailScreen
 */
@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val groupsRepository: GroupsRepositoryImpl
) : ViewModel() {
    private val resultMutableStudents = MutableStateFlow<List<Student>>(emptyList())
    val resultLiveStudents: StateFlow<List<Student>> = resultMutableStudents

    init {
        Log.e(Constants.LOG_TAG, "init groupDetailVM")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "groupDetailVM cleared")
        super.onCleared()
    }

    /**
     * Загрузка группы студентов
     */
    fun loadStudents(groupId: Int) {
        viewModelScope.launch {
            if (resultMutableStudents.value.isEmpty()) {
                val group = groupsRepository.getGroup(groupId)
                Log.d(Constants.LOG_TAG, groupId.toString())
                if (group.students.isNotEmpty()) {
                    Log.e(
                        Constants.LOG_TAG,
                        "vm load students with count: ${group.students.count()}"
                    )
                    resultMutableStudents.value = group.students
                } else {
                    Log.e(Constants.LOG_TAG, "students list is empty")
                }
            }
        }
    }
}