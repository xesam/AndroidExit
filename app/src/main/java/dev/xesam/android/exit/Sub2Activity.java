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
    }
}
