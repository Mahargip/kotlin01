package com.maha.kotlin01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maha.kotlin01.presentation.detail.DetailScreen
import com.maha.kotlin01.presentation.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{itemNumber}") {
        fun createRoute(itemNumber: Int) = "detail/$itemNumber"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val count = remember { mutableIntStateOf(0) }

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                count = count.intValue,
                onIncrement = { count.intValue++ },
                onDecrement = {
                    if (count.intValue > 0) {
                        count.intValue--
                    }
                },
                onItemClick = { itemNumber ->
                    navController.navigate(Screen.Detail.createRoute(itemNumber))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("itemNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemNumber = backStackEntry.arguments?.getInt("itemNumber") ?: 0
            DetailScreen(
                itemNumber = itemNumber,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
