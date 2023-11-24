package com.pay.payanalysis.view.home.transactioRreport

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.model.Transactions

@Composable
fun StatementRecord(item: Transactions) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        Text(
            text = item.date!!,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Text(
            text = item.type!!,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = item.amount!!.toString(),
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = item.service!!,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = item.category!!,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}