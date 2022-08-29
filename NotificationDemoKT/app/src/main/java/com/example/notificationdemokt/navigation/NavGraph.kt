package com.example.notificationdemokt.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.notificationdemokt.screens.DetailsScreen
import com.example.notificationdemokt.screens.HomeScreen


const val MY_URI = "https://github/manuelduarte077.com"
const val MY_ARG = "message"

@Composable
fun SetupNavGraph(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Home.route
  ) {
    composable(route = Screen.Home.route) {
      HomeScreen(navController = navController)
    }
    composable(
      route = Screen.Details.route,
      arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
      deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
    ) {
      val arguments = it.arguments
      arguments?.getString(MY_ARG)?.let { message ->
        DetailsScreen(message = message)
      }
    }
  }
}