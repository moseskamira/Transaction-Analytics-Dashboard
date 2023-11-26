package com.pay.payanalysis.view.home.reportTab

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.model.Transactions
import com.pay.payanalysis.ui.theme.Typography

@Composable
fun StatementRecord(item: Transactions) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        Text(
            text = item.date!!,
            modifier = Modifier.padding(vertical = 10.dp),
            style = Typography.bodyLarge
        )
        Text(
            text = item.type!!,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
            style = Typography.bodyLarge
        )
        Text(
            text = item.amount!!.toString(),
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
            style = Typography.bodyLarge
        )
        Text(
            text = item.service!!,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
            style = Typography.bodyLarge
        )
        Text(
            text = item.category!!,
            modifier = Modifier.padding(vertical = 10.dp),
            style = Typography.bodyLarge
        )
    }
}