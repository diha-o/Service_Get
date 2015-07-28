package dd_pc.service;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceLauncher = new Intent(context, ServiceActivityClass.class);
            context.startService(serviceLauncher);
            Log.v(this.getClass().getName(), "Service loaded while device boot.");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName("dd_pc.service", "dd_pc"+"."+"ServiceExample");
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            }
        }}




