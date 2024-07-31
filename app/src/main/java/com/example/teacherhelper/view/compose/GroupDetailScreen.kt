package com.example.teacherhelper.view.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.teacherhelper.presenter.GroupDetailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupDetailScreen(groupDetailViewModel: GroupDetailViewModel = hiltViewModel(),
                      groupId: Int){
    groupDetailViewModel.loadStudents(groupId)
    Scaffold(
        content = {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = groupDetailViewModel.resultLiveStudents.value ?: emptyList(),
                    itemContent = {
                        StudentsListItem(student = it){
                        }
                    }
                )
            }
        }
    )
}