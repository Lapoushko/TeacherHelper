package com.example.teacherhelper.view.compose

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.teacherhelper.repository.data.DropDownItem
import com.example.teacherhelper.repository.data.Group

/**
 * Item для отображения группы recycler view
 * @param group группа
 * @param dropDownItems список выподающего меню
 * @param onClick клик на нужную группу
 */
@Composable
fun GroupListItem(
    group: Group,
    dropDownItems: List<DropDownItem<Group>>,
    onClick: (Group) -> Unit
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = Modifier
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        },
                        onTap = {
                            onClick(group)
                        }
                    )
                }
        ) {
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
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(text = { Text(item.text) },
                    onClick = {
                        item.onItemClick(group)
                        isContextMenuVisible = false
                    },
                    leadingIcon = { Icon(imageVector = item.icon, contentDescription = null) })
            }
        }
    }
}

@Preview
@Composable
fun button() {
    Card(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                ambientColor = Color.Black,
                spotColor = Color.DarkGray,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(0.dp)),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = "123", style = typography.titleMedium)
                    Text(text = "456", style = typography.titleSmall)

                }
            }
        }
    }
}