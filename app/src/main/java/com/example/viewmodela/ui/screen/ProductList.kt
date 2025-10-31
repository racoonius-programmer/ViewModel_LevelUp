package com.example.viewmodela.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.viewmodela.R
import com.example.viewmodela.model.Producto
import com.example.viewmodela.util.formatPrice

@Composable
fun ProductListScreen(onGoToCart: () -> Unit, onAddProduct: () -> Unit) {
    val products = listOf(
        Producto("Polera Level Up", 15990, imageRes = R.drawable.polera_level_up),
        Producto("Pc Gamer", 1899990, imageRes = R.drawable.pc_gamer_asus_strix),
        Producto("PlayStation 5", 549990, imageRes = R.drawable.playstation5)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Listado de Productos", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
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
                        if (product.imageRes != null) {
                            Image(
                                painter = painterResource(id = product.imageRes),
                                contentDescription = product.name,
                                modifier = Modifier.size(60.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else if (product.icon != null) {
                            Image(
                                imageVector = product.icon,
                                contentDescription = product.name,
                                modifier = Modifier.size(60.dp)
                            )
                        } else {
                            Spacer(Modifier.size(60.dp))
                        }
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
        Spacer(Modifier.height(35.dp))

    }
}
