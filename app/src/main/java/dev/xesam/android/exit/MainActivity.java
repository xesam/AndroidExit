package dev.xesam.android.exit;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int exitCode = intent.getIntExtra(AppExit.EXTRA_EXIT, 0);
        if (exitCode == 1) {
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBtnClick() {
        startActivity(new Intent(this, Sub1Activity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
