package com.example.notificationdemokt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.notificationdemokt.MainViewModel
import com.example.notificationdemokt.navigation.Screen


@Composable
fun HomeScreen(mainViewModel: MainViewModel = hiltViewModel(), navController: NavHostController) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(onClick = mainViewModel::showSimpleNotification) {
      Text(text = "Simple Notification")
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = mainViewModel::updateSimpleNotification) {
      Text(text = "Update Notification")
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = mainViewModel::cancelSimpleNotification) {
      Text(text = "Cancel Notification")
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = mainViewModel::showProgress) {
      Text(text = "Progress")
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(onClick = {
      navController.navigate(
        Screen.Details.passArgument(
          message = "Coming from Main Screen."
        )
      )
    }) {
      Text(text = "DETAILS SCREEN")
    }
  }
}

