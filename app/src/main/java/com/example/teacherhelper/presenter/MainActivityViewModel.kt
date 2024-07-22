package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Group

private const val LOG_TAG = "ViewM"
class MainActivityViewModel: ViewModel() {
    private val groupsRepository = GroupsRepositoryImpl()

    private val resultMutableGroups = MutableLiveData<List<Group>>()
    val resultLiveGroups: LiveData<List<Group>> = resultMutableGroups

    private val resultMutableName1 = MutableLiveData<String>()
    val resultLiveName1: LiveData<String> = resultMutableName1

    init {
        Log.e(LOG_TAG,"vm created")
    }

    override fun onCleared() {
        Log.e(LOG_TAG,"vm cleared")
        super.onCleared()
    }

    fun loadName(){
        val id = 1
        val name = groupsRepository.getStudent(id).name
        resultMutableName1.value = name
        Log.e(LOG_TAG, "vm load student[${id}] with name: $name")
    }

    /**
     * Загрузка групп
     */
    fun loadGroups(){
        val groups = groupsRepository.getGroups()
        if (groups.isNotEmpty()){
            Log.e(LOG_TAG, "vm load groups with count: ${groups.count()}")
            resultMutableGroups.value = groups
        } else{
            Log.e(LOG_TAG, "vm is empty")
        }
    }
}