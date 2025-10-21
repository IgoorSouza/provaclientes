package com.igor.provaclientes.network

import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val cpf: String? = null,
    val totalPurchasesValue: Double? = null
)

@Serializable
data class CustomersResponse(
    val customers: List<Customer>,
    val totalCount: Int
)

suspend fun getCustomers(query: String? = null): CustomersResponse {
    val token = TokenStore.token ?: throw Exception("Token não encontrado. Faça login novamente.")

    val response = apiClient.get("https://extensaoiii-api.onrender.com/customer") {
        contentType(ContentType.Application.Json)
        header("Authorization", "Bearer $token")
        parameter("pageSize", 999)
        query?.let { value -> parameter("name", value) }
    }

    return response.body()
}
