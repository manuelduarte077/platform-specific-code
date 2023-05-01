package com.getnerdify.native_ringtone

import android.content.Context
import android.database.Cursor
import android.media.RingtoneManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val channel = "native_ringtone"

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channel).setMethodCallHandler {
                call,
                result ->
            when (call.method) {
                "getRingtones" -> {
                    val ringtone = getAllRingtones(this)
                    result.success(ringtone)
                }
            }
        }
    }

    private fun getAllRingtones(context: Context): List<String> {
        val manager = RingtoneManager(context)
        manager.setType(RingtoneManager.TYPE_RINGTONE)

        val cursor: Cursor = manager.cursor
        val list: MutableList<String> = mutableListOf()

        while (cursor.moveToNext()) {
            val notificationTitle: String = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            list.add(notificationTitle)
        }

        return list
    }
}
