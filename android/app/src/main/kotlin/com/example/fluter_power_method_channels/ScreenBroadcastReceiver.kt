package com.example.fluter_power_method_channels

import android.content.*
import android.os.Build
import android.util.Log
import android.widget.Toast
import android.os.VibrationEffect
import android.os.Vibrator
import android.content.Intent
import android.net.Uri

class ScreenBroadcastReceiver: BroadcastReceiver() {
    private val CHANNEL = "samples.flutter.dev/battery"
    var countPowerOff = 0
    private lateinit var context: Context

    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action
        if (Intent.ACTION_SCREEN_OFF == action){
             Log.d(SCREEN_TOGGLE_TAG,"Screen is now off.")
            val vibrator:Vibrator = context.applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(2000)
             countPowerOff++
         }else if (Intent.ACTION_SCREEN_ON == action){
             Log.d(SCREEN_TOGGLE_TAG,"Screen is now on.")

             if (countPowerOff==3){
                 val dialIntent = Intent(Intent.ACTION_DIAL)
                 dialIntent.data = Uri.parse("tel:"+"911")
                 context.startActivity(dialIntent)

                 val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                 if (vibrator.hasVibrator()) { // Vibrator availability checking
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                         vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
                     } else {
                         vibrator.vibrate(3000) // Vibrate method for below API Level 26
                     }
                 }
                 Log.d(SCREEN_TOGGLE_TAG,"Sending emergency message now...")
                 Toast.makeText(context, "Power button clicked 3 times", Toast.LENGTH_SHORT).show()
                 countPowerOff = 0
             }

             }
         }


     companion object {
         const val SCREEN_TOGGLE_TAG = "SCREEN_TOGGLE_TAG"
     }

 }