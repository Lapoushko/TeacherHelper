package com.example.teacherhelper.repository.data

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Item для Drop Down кнопок
 * @param text текст на кнопке
 * @param icon иконка кнопки
 * @param onItemClick клик на кнопку
 */
data class DropDownItem<T>(
    val text: String,
    val icon: ImageVector,
    val onItemClick: (T) -> Unit
)
