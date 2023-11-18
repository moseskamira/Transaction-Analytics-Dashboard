package com.pay.payanalysis.home

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.data.database.AppDatabase
import com.pay.payanalysis.data.entity.TransactionDTO
import com.pay.payanalysis.data.entity.convertTransactionToTransactionDTO
import com.pay.payanalysis.home.visual.DataPieChart
import com.pay.payanalysis.home.visual.PieChartEntry
import com.pay.payanalysis.model.Transaction
import com.pay.payanalysis.model.TransactionStatus
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("SimpleDateFormat", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Payment", "Transactions", "Visual")
    val clickedTabCount = remember {
        mutableIntStateOf(0)
    }

    val firstName = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> Column() {
                Text(
                    text = tabIndex.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                )
                OutlinedTextField(value = firstName.value, onValueChange = { input ->
                    firstName.value = input
                    print(firstName.value)
                })
            }

            1 -> Column {
                Text(text = "This is Transaction Section")
            }

            2 -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 40.dp),
            ) {
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                val transactions: List<Transaction> = listOf(
                    Transaction(
                        amount = 0.25,
                        type = "DEPOSIT",
                        id = 2,
                        status = TransactionStatus.COMPLETED,
                        date = currentDate
                    ), Transaction(
                        amount = 0.45,
                        type = "WITHDRAW",
                        id = 3,
                        status = TransactionStatus.PENDING,
                        date = currentDate
                    ), Transaction(
                        amount = 0.3,
                        type = "DEPOSIT",
                        id = 5,
                        status = TransactionStatus.COMPLETED,
                        date = currentDate
                    )
                )

//                val coroutineScope = rememberCoroutineScope()
//                val context = LocalContext.current;
//                coroutineScope.launch {
//                    convertData(
//                        Transaction(
//                            amount = 0.3,
//                            type = "DEPOSIT",
//                            id = 5,
//                            status = TransactionStatus.COMPLETED,
//                            date = currentDate
//                        ),
//                        context = context
//                    )
//                }


                val entries: MutableList<PieChartEntry> = mutableListOf()
                for (tran in transactions) {
                    if (tran.type == "DEPOSIT") {
                        print(tran.type)
                        val bgColor: Color = MaterialTheme.colors.primary
                        entries.add(PieChartEntry(bgColor, tran.amount.toFloat()))
                    } else {
                        val bgColor: Color = MaterialTheme.colors.primaryVariant
                        entries.add(PieChartEntry(bgColor, tran.amount.toFloat()))
                    }

                }
                DataPieChart(entries)
            }
        }
    }
}

//suspend fun convertData(trans: Transaction, context: Context) {
//    val convertedTrans = convertTransactionToTransactionDTO(trans)
//    val response = AppDatabase.getInstance(context).transactionDao().insert(convertedTrans)
//    print(response)
//}



