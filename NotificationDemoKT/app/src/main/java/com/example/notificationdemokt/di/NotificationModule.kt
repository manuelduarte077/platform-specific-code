package com.example.notificationdemokt.di

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val notificationBuilder: NotificationCompat.Builder,
  private val notificationManager: NotificationManagerCompat,
) : ViewModel() {

}