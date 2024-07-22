package com.example.teacherhelper.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.teacherhelper.repository.data.Group

@Composable
fun MainContent(groups: List<Group>){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = groups,
            itemContent = {
                GroupListItem(group = it)
            }
        )
    }
}
