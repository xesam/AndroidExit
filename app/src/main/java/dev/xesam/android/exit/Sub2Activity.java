package dev.xesam.android.exit;

import android.content.Intent;

public class Sub2Activity extends BaseActivity {

    protected int getLayoutId() {
        return R.layout.activity_sub2;
    }

    @Override
    protected void onBtnClick() {
        startActivity(new Intent(this, Sub2Activity.class));
    }
}
