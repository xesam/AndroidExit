package dev.xesam.android.exit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by xesamguo@gmail.com on 16-6-1.
 */
public class BaseActivity extends Activity {

    private ExitBroadcastReceiver mExitBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppActivityStack.getInstance().onCreate(this);
        mExitBroadcastReceiver = new ExitBroadcastReceiver(this);
        mExitBroadcastReceiver.register(this);
        setContentView(getLayoutId());
        onViewCreated();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClick();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppActivityStack.getInstance().onDestroy(this);
        mExitBroadcastReceiver.unRegister(this);
    }

    protected void onViewCreated() {
    }

    protected int getLayoutId() {
        return -1;
    }

    protected void onBtnClick() {

    }
}
