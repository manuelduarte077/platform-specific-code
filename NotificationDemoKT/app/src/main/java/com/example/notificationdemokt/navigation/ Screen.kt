package com.example.notificationdemokt.navigation

sealed class Screen(val route: String) {
  object Home : Screen(route = "home")
  object Details : Screen(route = "details/{$MY_ARG}") {
    fun passArgument(message: String) = "details/$message"
  }
}