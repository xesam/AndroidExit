package dev.xesam.android.exit;

import android.content.Intent;

public class Sub1Activity extends BaseActivity {

    protected int getLayoutId() {
        return R.layout.activity_sub1;
    }

    @Override
    protected void onBtnClick() {
        startActivity(new Intent(this, Sub2Activity.class));
    }
}
