package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для MainScreen
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var groupsRepository: GroupsRepositoryImpl
    private val resultMutableGroups = MutableStateFlow<List<Group>>(listOf())
    val resultLiveGroups: StateFlow<List<Group>> get() = resultMutableGroups.asStateFlow()

    init {
        Log.e(Constants.LOG_TAG, "vm created")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "vm cleared")
        super.onCleared()
    }

    /**
     * Загрузка групп
     */
    fun loadGroups() {
        viewModelScope.launch {
            if (resultMutableGroups.value.isEmpty()) {
                val groups = groupsRepository.getGroups()
                if (groups.isNotEmpty()) {
                    Log.e(Constants.LOG_TAG, "vm load groups with count: ${groups.count()}")
                    resultMutableGroups.update {
                        groups
                    }
                } else {
                    Log.e(Constants.LOG_TAG, "vm is empty")
                }
            }
        }
    }

    /**
     * Удалить группу
     */
    fun deleteGroup(group: Group){
        viewModelScope.launch {
            groupsRepository.deleteGroup(group)
            loadGroups()
        }
    }
}