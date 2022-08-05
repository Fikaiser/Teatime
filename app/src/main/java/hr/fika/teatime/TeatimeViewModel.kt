package hr.fika.teatime

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.AlarmManagerCompat.*
import androidx.lifecycle.ViewModel

class TeatimeViewModel : ViewModel() {

    var status by mutableStateOf("")

    fun runAlarm (context: Context, duration: Int, title: String) {
        status = "Brewing $title"

        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent: PendingIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)}

            alarmMgr.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + ((duration * 60) * 1000),
            alarmIntent
        )

    }
}