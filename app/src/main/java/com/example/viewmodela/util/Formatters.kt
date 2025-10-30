package com.example.viewmodela.util

import java.text.NumberFormat
import java.util.Locale

fun Int.formatPrice(): String {
    return NumberFormat.getNumberInstance(Locale("es", "CL")).format(this)
}
