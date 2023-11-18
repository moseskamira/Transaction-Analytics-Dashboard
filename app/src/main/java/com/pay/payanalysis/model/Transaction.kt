package com.pay.payanalysis.model

enum class TransactionStatus {
    PENDING, COMPLETED, CANCELED
}

data class Transaction(
    val id: Int,
    val type: String,
    val amount: Double,
    val date: String,
    val status: TransactionStatus
)
