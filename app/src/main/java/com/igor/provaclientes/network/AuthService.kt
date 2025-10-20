package com.igor.provaclientes.network

import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

object TokenStore {
    var token: String? = null
}

@Serializable
data class AuthRequest(val email: String, val password: String)

@Serializable
data class AuthUser(
    val id: String,
    val name: String,
    val email: String
)

@Serializable
data class AuthResponse(
    val user: AuthUser,
    val token: String
)

suspend fun loginUser(email: String, password: String) {
    val response = apiClient.post("https://extensaoiii-api.onrender.com/auth") {
        contentType(ContentType.Application.Json)
        setBody(AuthRequest(email, password))
    }

    TokenStore.token = response.body<AuthResponse>().token
}
