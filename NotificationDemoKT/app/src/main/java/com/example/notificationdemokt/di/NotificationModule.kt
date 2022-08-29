package com.example.notificationdemokt.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.notificationdemokt.MainActivity
import com.example.notificationdemokt.R
import com.example.notificationdemokt.navigation.MY_ARG
import com.example.notificationdemokt.navigation.MY_URI
import com.example.notificationdemokt.receiver.MyReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

  @Singleton
  @Provides
  fun provideNotificationBuilder(
    @ApplicationContext context: Context
  ): NotificationCompat.Builder {
    val intent = Intent(context, MyReceiver::class.java).apply {
      putExtra("MESSAGE", "Clicked!")
    }
    val flag =
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        PendingIntent.FLAG_IMMUTABLE
      else
        0
    val pendingIntent = PendingIntent.getBroadcast(
      context,
      0,
      intent,
      flag
    )
    val clickIntent = Intent(
      Intent.ACTION_VIEW,
      "$MY_URI/$MY_ARG=Coming from Notification".toUri(),
      context,
      MainActivity::class.java
    )
    val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
      addNextIntentWithParentStack(clickIntent)
      getPendingIntent(1, flag)
    }
    return NotificationCompat.Builder(context, "Main Channel ID")
      .setContentTitle("Welcome")
      .setContentText("Notification DEMO")
      .setSmallIcon(R.drawable.ic_stat_name)
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setVisibility(VISIBILITY_PRIVATE)
      .setPublicVersion(
        NotificationCompat.Builder(context, "Main Channel ID")
          .setContentTitle("Hidden")
          .setContentText("Unlock to see the message.")
          .build()
      )
      .addAction(0, "ACTION", pendingIntent)
      .setContentIntent(clickPendingIntent)
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