package com.example.teacherhelper.view.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.teacherhelper.presenter.GroupDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupDetailScreen(
    groupDetailViewModel: GroupDetailViewModel = hiltViewModel(),
    onMainClick: () -> Unit,
    onAddStudentClick: (Int) -> Unit,
    groupId: Int
) {
    groupDetailViewModel.loadStudents(groupId = groupId)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Список студентов")
                },
                navigationIcon = {
                    IconButton(onClick = onMainClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back icon"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
//            ,colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)))
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAddStudentClick(groupId)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save icon")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = groupDetailViewModel.resultLiveStudents.value ?: emptyList(),
                itemContent = {
                    StudentsListItem(student = it) {
                    }
                }
            )
        }
    }
}