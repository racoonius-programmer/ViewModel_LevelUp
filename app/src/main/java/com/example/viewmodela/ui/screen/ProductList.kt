package com.example.viewmodela.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodela.model.Producto
import com.example.viewmodela.util.formatPrice

@Composable
fun ProductListScreen(onGoToCart: () -> Unit, onAddProduct: () -> Unit) {
    val products = listOf(
        Producto("Polera Level Up", 15990, Icons.Default.Image),
        Producto("Tarjeta Gráfica RTX 4090", 1899990, Icons.Default.Build),
        Producto("PlayStation 5", 549990, Icons.Default.ShoppingCart)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Listado de Productos", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(10.dp))

        LazyColumn {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = product.icon,
                            contentDescription = product.name,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(product.name)
                            Text("Precio: $${product.price.formatPrice()}")
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onAddProduct) { Text("Agregar producto") }
            Button(onClick = onGoToCart) { Text("Ver carrito") }
        }
    }
}
