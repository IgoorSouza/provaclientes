package com.igor.provaclientes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.provaclientes.network.Customer
import com.igor.provaclientes.network.getCustomers
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(onLogout: () -> Unit) {
    var customers by remember { mutableStateOf<List<Customer>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    suspend fun fetchCustomers() {
        try {
            isLoading = true
            error = null

            val response = getCustomers(query = searchQuery.takeIf { it.isNotEmpty() })
            customers = response.customers
        } catch (e: Exception) {
            Log.e("Erro", "Erro ao buscar clientes", e)
            error = "Erro ao carregar clientes: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    LaunchedEffect(Unit) { fetchCustomers() }

    LaunchedEffect(searchQuery) {
        kotlinx.coroutines.delay(500)
        fetchCustomers()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF8EC5FC), Color(0xFFE0C3FC))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Clientes",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                IconButton(onClick = onLogout) {
                    Icon(
                        Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Sair",
                        tint = Color(0xFF6C63FF)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar cliente...") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color(0xFF6C63FF),
                    unfocusedBorderColor = Color(0xFFAAAAAA),
                    focusedLabelColor = Color(0xFF6C63FF)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF6C63FF))
                }

                error != null -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = error!!, color = Color.Red)
                }

                customers.isEmpty() -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum cliente encontrado.",
                        color = Color(0xFF555555),
                        fontSize = 16.sp
                    )
                }

                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(customers) { customer ->
                        CustomerCard(customer)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomerCard(customer: Customer) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                val intent = Intent(context, CustomerActivity::class.java).apply {
                    putExtra(CustomerActivity.EXTRA_CUSTOMER_ID, customer.id)
                    putExtra(CustomerActivity.EXTRA_CUSTOMER_NAME, customer.name)
                    putExtra(CustomerActivity.EXTRA_CUSTOMER_EMAIL, customer.email)
                    putExtra(CustomerActivity.EXTRA_CUSTOMER_PHONE, customer.phone)
                    putExtra(CustomerActivity.EXTRA_CUSTOMER_CPF, customer.cpf)
                    putExtra(CustomerActivity.EXTRA_CUSTOMER_TOTAL_PURCHASES, customer.totalPurchasesValue?.toString())
                }
                context.startActivity(intent)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = customer.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Ver detalhes",
                tint = Color(0xFF6C63FF)
            )
        }
    }
}
