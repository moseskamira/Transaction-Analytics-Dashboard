package com.pay.payanalysis.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.view.home.graph.GraphTabContainer
import com.pay.payanalysis.view.home.reportTab.ReportTabContainer


@SuppressLint("SimpleDateFormat", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Report", "Graphs", "Transactions")
    val firstName = remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ReportTabContainer()
            1 -> GraphTabContainer()
            2 -> Column() {
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
        }
    }
}






