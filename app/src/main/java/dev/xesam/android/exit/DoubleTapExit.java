package dev.xesam.android.exit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class DoubleTapExit {
    private final long interval;
    private long lastTap = Integer.MAX_VALUE;
    int tapCount = 0;

    public DoubleTapExit() {
        this(2000);
    }

    public DoubleTapExit(long interval) {
        this.interval = interval;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean handleTap(Activity activity) {

        boolean shouldIntercept;
        if (activity instanceof FragmentActivity) {
            shouldIntercept = shouldInterceptTap1((FragmentActivity) activity);
        } else {
            shouldIntercept = shouldInterceptTap0(activity);
        }

        if (shouldIntercept) {
            long current = SystemClock.elapsedRealtime();
            if (current - lastTap <= interval && tapCount >= 1) {
                onInterceptTap(2);
                activity.finish();
            } else {
                tapCount = 0;
                onInterceptTap(1);
                lastTap = current;
            }
            tapCount++;
            return true;
        } else {
            return false;
        }
    }

    protected void onInterceptTap(int count) {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected boolean shouldInterceptTap0(Activity activity) {
        return activity.getFragmentManager().getBackStackEntryCount() == 0;
    }

    protected boolean shouldInterceptTap1(FragmentActivity activity) {
        return activity.getSupportFragmentManager().getBackStackEntryCount() == 0;
    }
}
