package com.example.notificationdemokt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.notificationdemokt.screens.HomeScreen
import com.example.notificationdemokt.ui.theme.NotificationDemoKTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NotificationDemoKTTheme {
        HomeScreen()
      }
    }
  }
}
