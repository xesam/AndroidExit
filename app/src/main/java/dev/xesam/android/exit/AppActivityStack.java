package dev.xesam.android.exit;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class AppActivityStack {
    private AppActivityStack() {
    }

    private static AppActivityStack instance = new AppActivityStack();
    private static List<WeakReference<Activity>> activityStack = new ArrayList<>();

    public static AppActivityStack getInstance() {
        return instance;
    }

    public void onCreate(Activity activity) {
        activityStack.add(new WeakReference<>(activity));
    }

    public void onDestroy(Activity activity) {
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
