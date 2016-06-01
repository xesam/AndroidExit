package dev.xesam.android.exit;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import java.lang.ref.WeakReference;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class ExitBroadcastReceiver extends BroadcastReceiver {

    private static final String ACTION_EXIT = "app.action.exit";

    public static void broadcastExit(Context context) {
        Intent intent = new Intent(ACTION_EXIT);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    private IntentFilter filter = new IntentFilter();

    private WeakReference<Activity> activityWeakReference;

    public ExitBroadcastReceiver(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
        filter.addAction(ACTION_EXIT);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (activityWeakReference.get() != null) {
            activityWeakReference.get().finish();
        }
    }

    public void register(Context context) {
        context.registerReceiver(this, filter);
    }

    public void unRegister(Context context) {
        context.unregisterReceiver(this);
    }
}
