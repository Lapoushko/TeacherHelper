package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.repository.data.StudentByGroup
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для MainScreen
 * @param groupsRepository репозиторий для работы с данными
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val groupsRepository: GroupsRepositoryImpl
) : ViewModel() {

    private val resultMutableGroups = MutableStateFlow<List<StudentByGroup>>(listOf())
    val resultLiveGroups: StateFlow<List<StudentByGroup>> get() = resultMutableGroups

    init {
        Log.e(Constants.LOG_TAG, "vm created")
        loadGroups()
    }

    /**
     * Загрузка групп
     */
    private fun loadGroups(){
        viewModelScope.launch {
            if (resultMutableGroups.value.isEmpty()) {
                val groups = groupsRepository.getGroups()
                groups.collect{
                    if (it.isNotEmpty()) {
                        Log.e(Constants.LOG_TAG, "vm load groups with count: ${it.count()}")
                    } else {
                        Log.e(Constants.LOG_TAG, "vm is empty")
                    }
                    resultMutableGroups.value = it
                }
            }
        }
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "vm cleared")
        super.onCleared()
    }

    /**
     * Удалить группу
     * @param group нужная группа
     */
    fun deleteGroup(group: Group) {
        viewModelScope.launch {
            groupsRepository.deleteGroup(group)
        }
    }
}