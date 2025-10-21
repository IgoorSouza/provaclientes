package com.igor.provaclientes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.igor.provaclientes.network.Customer

class CustomerActivity : ComponentActivity() {

    companion object {
        const val EXTRA_CUSTOMER_ID = "customer_id"
        const val EXTRA_CUSTOMER_NAME = "customer_name"
        const val EXTRA_CUSTOMER_EMAIL = "customer_email"
        const val EXTRA_CUSTOMER_PHONE = "customer_phone"
        const val EXTRA_CUSTOMER_CPF = "customer_cpf"
        const val EXTRA_CUSTOMER_TOTAL_PURCHASES = "customer_total_purchases"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customer = Customer(
            id = intent.getStringExtra(EXTRA_CUSTOMER_ID) ?: "",
            name = intent.getStringExtra(EXTRA_CUSTOMER_NAME) ?: "",
            email = intent.getStringExtra(EXTRA_CUSTOMER_EMAIL) ?: "",
            phone = intent.getStringExtra(EXTRA_CUSTOMER_PHONE) ?: "",
            cpf = intent.getStringExtra(EXTRA_CUSTOMER_CPF),
            totalPurchasesValue = intent.getStringExtra(EXTRA_CUSTOMER_TOTAL_PURCHASES)?.toDoubleOrNull()
        )

        setContent {
            CustomerScreen(
                customer = customer,
                onBackClick = { finish() }
            )
        }
    }
}
