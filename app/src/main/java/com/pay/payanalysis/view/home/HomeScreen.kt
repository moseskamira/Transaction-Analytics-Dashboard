package com.pay.payanalysis.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.R
import com.pay.payanalysis.ui.theme.PayAnalysisTheme
import com.pay.payanalysis.ui.theme.Typography
import com.pay.payanalysis.view.reUsable.CustomDivider

@Composable
fun HomeScreen() {
    PayAnalysisTheme {
        ShowHomeScreen()
    }
}

@Composable
@Preview
fun ShowHomeScreen() {
    Column(
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
        ) {
            WelcomeNameCard()
            CustomDivider()
            Text(
                text = "TRANSACTIONS ANALYTICS DASHBOARD",
                style = Typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            HomeScreenTabScreen()

        }
    }

}