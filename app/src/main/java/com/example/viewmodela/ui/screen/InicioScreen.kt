package com.example.viewmodela.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.example.viewmodela.R

// --- Data Classes (Modelos de datos) ---
data class CarouselSlide(
    val imageRes: Int,
    val title: String,
    val subtitle: String
)

data class Product(
    val name: String,
    val price: String,
    val imageRes: Int
)

// --- Sample Data (Datos de ejemplo) ---
// NOTA: Reemplaza con tus propias imágenes. Añádelas a res/drawable.
//val carouselSlides = listOf(
//    CarouselSlide(R.drawable.gamer_stock, "Level-up Gamer", "Tu lugar para todo lo relacionado con videojuegos"),
//    CarouselSlide(R.drawable.componentes, "Arma tu PC como un Pro", "Encuentra componentes de última generación"),
//    CarouselSlide(R.drawable.ps_logo_stock, "Conéctate al mundo Gamer", "Comunidad, merch y más")
//)
//
//val bestSellers = listOf(
//    Product("Polera Level Up", "$15.990", R.drawable.polera_level_up),
//    Product("Tarjeta Gráfica RTX 4090", "$1.899.990", R.drawable.componentes),
//    Product("PlayStation 5", "$549.990", R.drawable.ps_logo_stock)
//    // Agrega más productos
//)

@Composable
fun InicioScreen(navController: NavController) {
    // Estado para simular el inicio de sesión. En una app real, esto vendría de un ViewModel.
    var isLoggedIn by remember { mutableStateOf(false) }
    var isAdmin by remember { mutableStateOf(false) } // Simula si el usuario es admin
    val username = "Frank" // Simula un nombre de usuario

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                //CarouselSection(slides = carouselSlides)
            }
            item {
                UserGreetingSection(
                    isLoggedIn = isLoggedIn,
                    isAdmin = isAdmin,
                    username = username,
                    navController = navController,
                    onLoginToggle = { isLoggedIn = !isLoggedIn },
                    onAdminToggle = { isAdmin = !isAdmin }
                )
            }
            item {
                //BestSellersSection(products = bestSellers, navController = navController)
            }
            item {
                TechSupportBanner(context = LocalContext.current)
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselSection(slides: List<CarouselSlide>) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = slides.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) { page ->
        val slide = slides[page]
        Box(contentAlignment = Alignment.BottomStart) {
            Image(
                painter = painterResource(id = slide.imageRes),
                contentDescription = slide.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = slide.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    style = LocalTextStyle.current.copy(
                        shadow = Shadow(color = Color.Black, blurRadius = 8f)
                    )
                )
                Text(
                    text = slide.subtitle,
                    color = Color.White,
                    fontSize = 16.sp,
                    style = LocalTextStyle.current.copy(
                        shadow = Shadow(color = Color.Black, blurRadius = 8f)
                    )
                )
            }
        }
    }
}

@Composable
fun UserGreetingSection(
    isLoggedIn: Boolean,
    isAdmin: Boolean,
    username: String,
    navController: NavController,
    onLoginToggle: () -> Unit,
    onAdminToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isLoggedIn) {
                if (isAdmin) {
                    Text("¡Bienvenido, Administrador!", style = MaterialTheme.typography.headlineSmall)
                    Text("A sus ordenes, Señor Oscuro")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { /* navController.navigate("admin_panel") */ }) {
                        Text("Panel de Administración")
                    }
                } else {
                    Text("¡Bienvenido, $username!", style = MaterialTheme.typography.headlineSmall)
                    Text("¡Explora nuestra tienda y descubre lo último!")
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Button(onClick = { /* navController.navigate("productos") */ }) {
                            Text("Ver productos")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = { /* navController.navigate("carrito") */ }) {
                            Text("Ir al carrito")
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                TextButton(onClick = onLoginToggle) {
                    Text("Cerrar Sesión")
                }

            } else {
                Text("¡Bienvenido!", style = MaterialTheme.typography.headlineSmall)
                Text("Inicia sesión para una mejor experiencia.")
                Spacer(Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        onLoginToggle()
                    }) {
                        Text("Iniciar Sesión")
                    }
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = { navController.navigate("registro") }) {
                        Text("Registrarse")
                    }
                }
            }
            // Botón para simular cambio de rol
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Es Admin:")
                Checkbox(checked = isAdmin, onCheckedChange = { onAdminToggle() })
            }
        }
    }
}

@Composable
fun BestSellersSection(products: List<Product>, navController: NavController) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = "Lo más vendido",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product, onClick = { /* navController.navigate("product_detail/${product.name}") */ })
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp),
        onClick = onClick
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = product.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(text = product.price)
            }
        }
    }
}

@Composable
fun TechSupportBanner(context: Context) {
    val whatsappUrl = "https://api.whatsapp.com/send?phone=56984543683&text=¡Hola!"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(whatsappUrl)
            }
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("¿Necesitas servicio técnico?", style = MaterialTheme.typography.titleLarge)
                Text("Contáctanos a nuestro Whatsapp")
            }
        }
    }
}