package dd_pc.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class ServiceActivityClass extends Service {
    public static Cursor c;
    public static int INTERVAL =(int) AlarmManager.INTERVAL_FIFTEEN_MINUTES;
    // 60 min = 3600000
    public static final int FIRST_RUN = 5; // 5 seconds
    public static int REQUEST_CODE = 11223344;
    private RepeatingAlarmService mSMSreceiver;
    private IntentFilter mIntentFilter;
    static AlarmManager alarmManager;

    @Override
    public void onCreate() {

        super.onCreate();
        mSMSreceiver = new RepeatingAlarmService();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.permission.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);
        startService(INTERVAL);
        Log.v(this.getClass().getName(), "onCreate(..)");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(this.getClass().getName(), "onBind(..)");
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mSMSreceiver);
        if (alarmManager != null) {
            Intent intent = new Intent(this, RepeatingAlarmService.class);
            alarmManager.cancel(PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0));
        }
        Toast.makeText(this, "Service Stopped!", Toast.LENGTH_LONG).show();
        Log.v(this.getClass().getName(), "Service onDestroy(). Stop AlarmManager at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
    }

 void startService(int interval) {
     registerReceiver(this.mSMSreceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
     Intent intent = new Intent(this, RepeatingAlarmService.class);
     PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);
     alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
     alarmManager.setRepeating(
             AlarmManager.ELAPSED_REALTIME_WAKEUP,
             SystemClock.elapsedRealtime() + (60 * 1000),
             SystemClock.elapsedRealtime() + interval,
             pendingIntent);
     Toast.makeText(this, "Service Started.", Toast.LENGTH_LONG).show();
     Log.v(this.getClass().getName(), "AlarmManger started at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());


 }
}
