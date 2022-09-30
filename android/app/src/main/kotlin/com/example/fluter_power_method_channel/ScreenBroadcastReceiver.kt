import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ScreenBroadcastReceiver:BroadcastReceiver(){
    var countPowerOff = 0
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (Intent.ACTION_SCREEN_OFF == action){
            Log.d(SCREEN_TOGGLE_TAG,"Screen is now off.")
            countPowerOff++
        }else if (Intent.ACTION_SCREEN_ON == action){
            Log.d(SCREEN_TOGGLE_TAG,"Screen is now on.")

            if (countPowerOff==3){
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