package com.pay.payanalysis.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pay.payanalysis.view.account.AccountScreen
import com.pay.payanalysis.view.home.HomeScreen
import com.pay.payanalysis.view.settings.SettingsScreen

@Composable
fun NavHostContainer(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Settings.route) {
            SettingsScreen()
        }
        composable(BottomNavItem.Account.route) {
            AccountScreen()
        }
    }
}