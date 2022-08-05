package hr.fika.teatime

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val vib = context.getSystemService(Activity.VIBRATOR_SERVICE) as Vibrator

        val la = arrayOf(1000L,1000L,1000L,1000L,1000L)
        val ia = arrayOf(240, 0,240,0,240)
        vib.vibrate(
            VibrationEffect.createWaveform(la.toLongArray(),ia.toIntArray(),-1),
            AudioAttributes.Builder().
        setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_ALARM).build())

        Toast.makeText(context,"Done", Toast.LENGTH_SHORT).show()
    }
}