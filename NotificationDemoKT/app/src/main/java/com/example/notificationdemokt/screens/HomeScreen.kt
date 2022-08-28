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
import com.example.notificationdemokt.MainViewModel


@Composable
fun HomeScreen(mainViewModel: MainViewModel = hiltViewModel()) {
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
  }
}

