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

    private fun loadGroups(){
        viewModelScope.launch {
            if (resultMutableGroups.value.isEmpty()) {
                val groups = groupsRepository.getGroups()
                if (groups.isNotEmpty()) {
                    resultMutableGroups.value = groups
                    Log.e(Constants.LOG_TAG, "vm load groups with count: ${groups.count()}")
                } else {
                    Log.e(Constants.LOG_TAG, "vm is empty")
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
     */
    fun deleteGroup(group: Group) {
        viewModelScope.launch {
            groupsRepository.deleteGroup(group)
        }
    }

    /**
     * Очистить базу данных
     */
    fun dropDatabase(){
        viewModelScope.launch {
            groupsRepository.dropDatabase()
        }
    }
}