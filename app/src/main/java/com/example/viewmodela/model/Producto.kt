package com.example.viewmodela.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

// atributo de Producto
data class Producto(
    val name: String,
    val price: Int,
    val icon: ImageVector
)
