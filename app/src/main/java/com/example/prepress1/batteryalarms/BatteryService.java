package com.example.prepress1.batteryalarms;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.Toast;

public class BatteryService extends Service {
    public BatteryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();

        this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String bStatus="no data";

            int  level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int  plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);

            int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            switch (status){
                case BatteryManager.BATTERY_STATUS_CHARGING: {bStatus = "Charging"; break;}
                case BatteryManager.BATTERY_STATUS_DISCHARGING: {bStatus = "Discharging"; break;}
                case BatteryManager.BATTERY_STATUS_FULL: {bStatus = "Full"; break;}
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING: {bStatus = "Not Charging"; break;}
                case BatteryManager.BATTERY_STATUS_UNKNOWN: {bStatus = "Unknown"; break;}
            }
        }
    };
}
