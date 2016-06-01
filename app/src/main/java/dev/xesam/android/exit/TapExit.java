package dev.xesam.android.exit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.SystemClock;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class TapExit {
    private final long interval;
    private long lastTap = Integer.MAX_VALUE;

    public TapExit() {
        this(2000);
    }

    public TapExit(long interval) {
        this.interval = interval;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void backTap(Activity activity) {
        if (shouldInterceptTap(activity)) {

        } else {

        }
        long current = SystemClock.elapsedRealtime();
        if (current - lastTap >= interval) {
            if (activity.getFragmentManager().getBackStackEntryCount() > 0) {
//                shouldInterceptTap(false);
//                activity.onBackPressed();
            }
        }

        lastTap = current;
    }

//    public void backTap(FragmentActivity activity) {
//        long current = SystemClock.elapsedRealtime();
//        if (current - lastTap >= interval) {
//
//        }
//    }

    protected boolean shouldInterceptTap(Activity activity) {
        return false;
    }
}
