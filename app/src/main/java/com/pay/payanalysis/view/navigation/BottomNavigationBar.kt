package com.pay.payanalysis.view.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pay.payanalysis.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Settings,
        BottomNavItem.Account
    )

    NavigationBar(
        containerColor = colorResource(id = R.color.blue_200),
    ) {
        var selectedNavIndex by remember {
            mutableIntStateOf(0)
        }
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 12.sp
                    )
                },
                alwaysShowLabel = true,
                selected = selectedNavIndex == index,
                onClick = {
                    selectedNavIndex = index
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }


    }


}