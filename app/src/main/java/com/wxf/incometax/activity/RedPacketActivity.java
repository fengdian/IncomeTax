package com.wxf.incometax.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.wxf.incometax.R;
import com.wxf.incometax.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class RedPacketActivity extends BaseActivity {
    @BindView(R.id.iv_red_packet)
    ImageView redPacket;
    @Override
    public int getLayout() {
        return R.layout.red_packet_activity;
    }
    @OnLongClick(R.id.iv_red_packet)
    public boolean getRedPacket() {

        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(getResources().getText(R.string.apply_info));
        Toast.makeText(this, getResources().getText(R.string.open_apply), Toast.LENGTH_LONG).show();
        toAliPayScan();
        return  true;
    }

    @Override
    public void initData() {


    }
    private void toAliPayScan() {
        try {
            //利用Intent打开支付宝
            //支付宝跳过开启动画打开扫码和付款码的urlscheme分别是：
            //alipayqr://platformapi/startapp?saId=10000007
            //alipayqr://platformapi/startapp?saId=20000056
            Uri uri = Uri.parse("alipayqr://platformapi/startapp");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            //若无法正常跳转，在此进行错误处理
           e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backAnim();
    }
}
