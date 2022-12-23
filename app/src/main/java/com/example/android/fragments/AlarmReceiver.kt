package com.example.android.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi


class AlarmReceiver : BroadcastReceiver() {
    @RequiresApi(api = Build.VERSION_CODES.Q)  // implement onReceive() method
    override fun onReceive(context: Context, intent: Intent) {
        println("happy happy day")
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show()
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        // setting default ringtone
        val ringtone = RingtoneManager.getRingtone(context, alarmUri)

        // play ringtone
        println("yooooooooooooooooooooooo")
        ringtone.play()


    }

}
