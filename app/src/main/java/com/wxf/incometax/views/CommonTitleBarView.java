package com.wxf.incometax.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxf.incometax.R;


public class CommonTitleBarView extends RelativeLayout {

	private static final String TAG = CommonTitleBarView.class.getName();
	private Context mContext;
	private ImageView ivLeft, ivRight;
	private TextView tv_left, tv_right;
	private TextView tv_center;

	private RelativeLayout rl_area_right, rl_area_left, rl_common_title_bar2;

	// private LinearLayout common_constact;
	public CommonTitleBarView(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	public CommonTitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}

	private void initView() {
		LayoutInflater.from(mContext).inflate(R.layout.common_title_bar, this);
		ivLeft = (ImageView) this.findViewById(R.id.title_iv_left);
		tv_left = (TextView) this.findViewById(R.id.title_tv_left);
		ivRight = (ImageView) findViewById(R.id.title_iv_right);
		tv_right = (TextView) this.findViewById(R.id.title_tv_right);
		rl_area_right = (RelativeLayout) this.findViewById(R.id.rl_area_right);
		rl_area_left = (RelativeLayout) this.findViewById(R.id.rl_area_left);
		rl_common_title_bar2 = (RelativeLayout) this
				.findViewById(R.id.rl_common_title_bar2);
		tv_center=(TextView) this.findViewById(R.id.title_tv_center);
	}

	public void setCommonTitle(int LeftTextViewVisibility,
			int leftImageViewVisibility,int centerTextViewVisibility,int rightTextVisibility,
			int rightImageViewVisibility) {
		tv_left.setVisibility(LeftTextViewVisibility);
		ivLeft.setVisibility(leftImageViewVisibility);
		tv_right.setVisibility(rightTextVisibility);
		ivRight.setVisibility(rightImageViewVisibility);
		tv_center.setVisibility(centerTextViewVisibility);
	}

	public void setLeftText(int txtRes) {
		tv_left.setText(txtRes);
	}

	public void setLeftText(String txt) {
		tv_left.setText(txt.trim());
	}
	public void setRightText(int txtRes) {
		tv_right.setText(txtRes);
	}

	public void setRightText(String txt) {
		tv_right.setText(txt.trim());
	}
	public void setCenterText(int txtRes) {
		tv_center.setText(txtRes);
	}

	public void setCenterText(String txt) {
		tv_center.setText(txt);
	}

	// public void setTitleLeft(int resId){
	// btn_titleLeft.setText(resId);
	// }
	//
	// public void setTitleRight(int resId){
	// btn_titleRight.setText(resId);
	// }

	public void setTitleBarBackgroud(int color) {
		rl_common_title_bar2.setBackgroundColor(color);
	}

	public RelativeLayout getLeftClickableArea() {
		return rl_area_left;
	}

	public RelativeLayout getRightClickableArea() {
		return rl_area_right;
	}

	public ImageView getIvLeft() {
		return ivLeft;
	}

	public ImageView getIvRight() {
		return ivRight;
	}

	public TextView getLeftTextView() {
		return tv_left;
	}
	public TextView getRightTextView() {
		return tv_right;
	}

	public TextView getTv_center() {
		return tv_center;
	}
}
