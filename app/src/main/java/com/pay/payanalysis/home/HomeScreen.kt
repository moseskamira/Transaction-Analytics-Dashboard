package com.pay.payanalysis.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.R
import com.pay.payanalysis.ui.theme.PayAnalysisTheme

@Composable
fun HomeScreen() {
    PayAnalysisTheme {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .background(color = colorResource(id = R.color.blue_200))
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp))
                    .background(color = Color.White)
                    .padding(20.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "TRANSACTIONS ANALYTICS DASHBOARD",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
                HomeScreenTabScreen()

            }
        }

    }
}

