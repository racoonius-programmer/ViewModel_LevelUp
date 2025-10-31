package com.example.viewmodela.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Producto(
    val name: String,
    val price: Int,
    val icon: ImageVector? = null,
    @DrawableRes val imageRes: Int? = null
)
