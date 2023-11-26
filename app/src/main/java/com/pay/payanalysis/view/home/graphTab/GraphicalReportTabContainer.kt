package com.pay.payanalysis.view.home.graphTab

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Transactions
import com.pay.payanalysis.repository.TransactionRepository
import com.pay.payanalysis.ui.theme.Typography
import com.pay.payanalysis.view.reUsable.CustomDivider
import com.pay.payanalysis.viewModel.TransactionViewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun GraphicalReportTabContainer() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(top = 40.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        val fullTransactionList: MutableList<Transactions> = ArrayList()
        val selectedList = mutableListOf<Transactions>()
        val transactionViewModel = TransactionViewModel(
            TransactionRepository()
        )
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var selectedType by remember {
            mutableStateOf("Deposit")
        }
        var showChart by remember {
            mutableStateOf(true)
        }
        val myStatement = transactionViewModel.getStatement(LocalContext.current)
        fullTransactionList.addAll(myStatement.customer!!.account!!.transactions)
        for (txn in fullTransactionList) {
            if (txn.type!!.contains(selectedType)) {
                selectedList.add(txn)
            }
        }
        Text(
            text = "GRAPHICAL TRANSACTION REPORT",
            style = Typography.titleMedium,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.blue_200),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        StatementSummary(customer = myStatement.customer!!)
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(
                text = "KEY:",
                style = Typography.bodyLarge,
                color = colorResource(id = R.color.blue_200),
            )
            Text(
                text = "AT: Airtime",
                style = Typography.bodyLarge,
                color = colorResource(id = R.color.black)
            )
            Text(
                text = "MM: Mobile Money",
                style = Typography.bodyLarge,
                color = colorResource(id = R.color.black)
            )
            Text(
                text = "Util: Utilities",
                style = Typography.bodyLarge,
                color = colorResource(id = R.color.black)
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
        CustomDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Type:",
                style = Typography.bodyLarge,
                color = colorResource(id = R.color.black),
                modifier = Modifier.weight(1f)
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue ->
                    isExpanded = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                TextField(
                    value = selectedType.uppercase(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Type")
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        textColor = colorResource(id = R.color.black),
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.menuAnchor(),
                    textStyle = Typography.bodyLarge
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    },
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.white))
                        .clip(shape = RectangleShape)

                ) {
                    val categories = mutableListOf<String>()
                    fullTransactionList.forEach {
                        categories.add(it.type!!)
                    }
                    val uniqueDates = categories.toSet().toList()
                    uniqueDates.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option.uppercase(), style = Typography.bodyLarge,
                                    color = colorResource(id = R.color.black)
                                )
                            },
                            onClick = {
                                selectedType = option
                                isExpanded = false
                            }
                        )
                    }
                }
            }

        }
        val pairsList = mutableMapOf<Any, Float>()
        val keyColorMap = mutableMapOf<Any, Color>()
        var airtimeTotal = 0.0
        var mobileMoneyTotal = 0.0
        var tvTotal = 0.0
        var utilTotal = 0.0
        var internetTotal = 0.0
        selectedList.forEach {
            val cat = mutableStateOf("")
            when (it.category) {
                "Mobile Money" -> {
                    cat.value = "MM"
                    mobileMoneyTotal += it.amount!!
                    keyColorMap[cat.value] = colorResource(id = R.color.blue_200)
                    pairsList[cat.value] = mobileMoneyTotal.toFloat()
                }

                "Airtime" -> {
                    cat.value = "AT"
                    airtimeTotal += it.amount!!
                    keyColorMap[cat.value] = Color.Green
                    pairsList[cat.value] = airtimeTotal.toFloat()
                }

                "Utilities" -> {
                    cat.value = "Util"
                    utilTotal += it.amount!!
                    keyColorMap[cat.value] = Color.Black
                    pairsList[cat.value] = utilTotal.toFloat()
                }

                "Internet" -> {
                    cat.value = "Internet"
                    internetTotal += it.amount!!
                    keyColorMap[cat.value] = colorResource(id = R.color.teal_200)
                    pairsList[cat.value] = internetTotal.toFloat()
                }

                else -> {
                    cat.value = it.category!!
                    tvTotal += it.amount!!
                    keyColorMap[cat.value] = Color.Yellow
                    pairsList[cat.value] = tvTotal.toFloat()
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        CustomDivider()

        Spacer(modifier = Modifier.height(2.dp))
        BarChartGraph(
            data = pairsList,
            barColor = keyColorMap,
            isExpanded = showChart,
        ) {
            showChart = !showChart
        }
        Spacer(modifier = Modifier.height(80.dp))
    }

}
