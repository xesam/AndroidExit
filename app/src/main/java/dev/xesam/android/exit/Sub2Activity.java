package dev.xesam.android.exit;

import android.view.View;

public class Sub2Activity extends BaseActivity {

    protected int getLayoutId() {
        return R.layout.activity_sub2;
    }

    @Override
    protected void onBtnClick() {
        AppExit.systemExit();
    }

    @Override
    protected void onViewCreated() {
        findViewById(R.id.system_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExit.systemExit();
            }
        });
        findViewById(R.id.stack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppActivityStack.getInstance().finishAllActivity();
            }
        });
        findViewById(R.id.stack2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StackApp) getApplication()).finishAllActivity();
            }
        });
        findViewById(R.id.broadcastExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitBroadcastReceiver.broadcastExit(getApplicationContext());
            }
        });
    }
}
