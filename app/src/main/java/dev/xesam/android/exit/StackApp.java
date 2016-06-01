package dev.xesam.android.exit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class StackApp extends Application {
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onCreate() {
        super.onCreate();
        initStack();
    }

    private static List<WeakReference<Activity>> activityStack = new ArrayList<>();

    private void initStack() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return;
        }
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activityStack.add(new WeakReference<>(activity));
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                WeakReference<Activity> target = null;
                for (int size = activityStack.size(), i = size - 1; i >= 0; i--) {
                    WeakReference<Activity> weakReference = activityStack.get(i);
                    if (weakReference.get() == activity) {
                        target = weakReference;
                        break;
                    }
                }
                activityStack.remove(target);
            }
        });
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).get().finish();
            }
        }
        activityStack.clear();
    }
}
