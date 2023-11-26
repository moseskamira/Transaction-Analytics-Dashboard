package com.pay.payanalysis.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.pay.payanalysis.R
import com.pay.payanalysis.ui.theme.Typography
import com.pay.payanalysis.view.home.graphTab.GraphicalReportTabContainer
import com.pay.payanalysis.view.home.reportTab.TextualReportTabContainer

@Composable
fun HomeScreenTabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("TEXTUAL", "GRAPHICAL")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = {
                    Text(
                        title,
                        style = Typography.bodyLarge,
                        color = colorResource(id = R.color.blue_200),
                    )
                },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> TextualReportTabContainer()
            1 -> GraphicalReportTabContainer()
        }
    }
}






