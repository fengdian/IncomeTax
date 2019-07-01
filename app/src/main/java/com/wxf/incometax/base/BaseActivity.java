package com.wxf.incometax.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wxf.incometax.R;
import com.wxf.incometax.views.CommonTitleBarView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class   BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        unbinder = ButterKnife.bind(this);
        initData();



    }
     public  abstract  int getLayout();
    public  abstract void initData();

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void backAnim() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    // 前进动画
    public void intoAnim() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
