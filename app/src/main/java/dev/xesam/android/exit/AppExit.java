package dev.xesam.android.exit;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class AppExit {
    public static void systemExit() {
        System.exit(0);
    }

    public static final String EXTRA_EXIT = "EXIT";

    public static void exitIntent(Activity activity) {
        Intent exitIntent = new Intent(activity, MainActivity.class);
        exitIntent.putExtra(EXTRA_EXIT, 1);
        activity.startActivity(exitIntent);
    }
}
