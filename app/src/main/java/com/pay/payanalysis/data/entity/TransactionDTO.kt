package com.pay.payanalysis.data.entity

import androidx.room.Entity
import com.pay.payanalysis.model.Transaction
import com.pay.payanalysis.model.TransactionStatus
import java.util.Date

@Entity(tableName = "transaction", primaryKeys = ["transactionId"])
data class TransactionDTO(
    val transactionId: Int,
    val type: String,
    val amount: Double,
    val date: String,
    val status: TransactionStatus

)

fun convertTransactionToTransactionDTO(transaction: Transaction): TransactionDTO {
    val transactionId = transaction.id
    val type = transaction.type
    val amount = transaction.amount
    val date = transaction.date
    val status = transaction.status
    return TransactionDTO(transactionId, type, amount, date, status)
}
