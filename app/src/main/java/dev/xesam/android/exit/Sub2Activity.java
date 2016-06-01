package dev.xesam.android.exit;

import android.content.Intent;
import android.view.View;

public class Sub2Activity extends BaseActivity {

    protected int getLayoutId() {
        return R.layout.activity_sub2;
    }

    @Override
    protected void onBtnClick() {
        startActivity(new Intent(this, Sub2Activity.class));
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
        findViewById(R.id.exitIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExit.exitIntent(Sub2Activity.this);
            }
        });
    }
}
