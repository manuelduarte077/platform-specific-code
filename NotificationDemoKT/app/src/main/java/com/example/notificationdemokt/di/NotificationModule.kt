package com.example.notificationdemokt.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationdemokt.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object NotificationModule {

  @Singleton
  @Provides
  fun provideNotificationBuilder(
    @ApplicationContext context: Context
  ): NotificationCompat.Builder {

    return NotificationCompat.Builder(context, "Main Channel ID")
      .setContentTitle("Welcome")
      .setContentText("Example Notification")
      .setSmallIcon(R.drawable.ic_stat_name)
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)

  }

  @Singleton
  @Provides
  fun provideNotificationManager(
    @ApplicationContext context: Context
  ): NotificationManagerCompat {
    val notificationManager = NotificationManagerCompat.from(context)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        "Main Channel ID",
        "Main Channel",
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationManager.createNotificationChannel(channel)

    }
    return notificationManager
  }
}
