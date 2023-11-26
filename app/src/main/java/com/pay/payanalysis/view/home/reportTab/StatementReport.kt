package com.pay.payanalysis.view.home.reportTab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Transactions

@Composable
fun StatementReport(transList: List<Transactions>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        transList.forEach { myItem ->
            Column {
                StatementRecord(item = myItem)
                Divider(
                    thickness = 1.dp,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }
    }
}