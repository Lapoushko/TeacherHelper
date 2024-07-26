package com.example.teacherhelper.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.teacherhelper.repository.data.Group

/**
 * Item для отображения группы recycler view
 */
@Composable
fun GroupListItem(group: Group, onClick : () -> Unit){
    Button(onClick = {
        onClick()
                     },
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = ButtonDefaults.buttonColors(Color.Gray)) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = group.name, style = typography.titleMedium)
                Text(text = group.description, style = typography.titleSmall)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun button(){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = ButtonDefaults.buttonColors(Color.Gray)) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "12345", style = typography.titleMedium)
                Text(text = "6789", style = typography.titleSmall)

            }
        }
    }
}