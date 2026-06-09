package org.lineageos.lemonades.refreshratehack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "LemonadesRefreshRateHack";
    private static final String PROP_HACK_APPLIED = "sys.lineageos.lemonades.refresh_rate_hack_applied";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (
            Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction())
            // Note: ACTION_LOCKED_BOOT_COMPLETED will trigger once on direct boot regardless of presence of secure lockscreen
        ) {
            // Prevent repeated actions (no longer needed)
            // if ("1".equals(SystemProperties.get(PROP_HACK_APPLIED, "0"))) {
            //     Log.i(TAG, "Refresh rate fix already applied this boot. Skipping.");
            //     return;
            // }
            // SystemProperties.set(PROP_HACK_APPLIED, "1");

            Log.i(TAG, "Boot completed. Scheduling refresh rate fix...");
            
            // No delay after boot completed
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                // try { // Settings.System.getFloat does not throw error if default value is specified.
                    Log.i(TAG, "Fix start");

                    // 1. Read current values
                    float currentPeak = Settings.System.getFloat(context.getContentResolver(), "peak_refresh_rate", 1.0f / 0.0f);
                    float currentMin = Settings.System.getFloat(context.getContentResolver(), "min_refresh_rate", 0.0f);
                    Log.i(TAG, "Current refresh rate settings: peak=" + currentPeak + ", min=" + currentMin);

                    // 2. Toggle down to force HAL re-evaluation
                    Settings.System.putFloat(context.getContentResolver(), "peak_refresh_rate", 60.0f);
                    Settings.System.putFloat(context.getContentResolver(), "min_refresh_rate", 0.0f);
                    Log.i(TAG, "Temporarily set refresh rate to peak=60.0, min=0.0");

                    // 3. Restore after a while
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        Settings.System.putFloat(context.getContentResolver(), "peak_refresh_rate", currentPeak);
                        Settings.System.putFloat(context.getContentResolver(), "min_refresh_rate", currentMin);
                        Log.i(TAG, "Restored refresh rate");
                        Log.i(TAG, "Fix completed");
                    }, 500);
                // }
                // catch (Settings.SettingNotFoundException e) {
                //     Log.e(TAG, "Refresh rate setting not found", e);
                // }
            }, 0);
        }
    }
}
