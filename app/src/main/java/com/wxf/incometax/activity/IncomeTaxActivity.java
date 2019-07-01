package com.wxf.incometax.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.wxf.incometax.R;
import com.wxf.incometax.base.BaseActivity;
import com.wxf.incometax.constant.Constant;
import com.wxf.incometax.utils.AccumulationFundUtils;
import com.wxf.incometax.utils.InsuranceUtils;
import com.wxf.incometax.utils.TaxUtils;
import com.wxf.incometax.views.CommonTitleBarView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class IncomeTaxActivity extends BaseActivity {
    @BindView(R.id.et_income)
    EditText  etIncome;
    @BindView(R.id.et_accumulationfund)
    EditText etAccumulationfund;
    @BindView(R.id.et_insurance)
    EditText etInsurance;
    @BindView(R.id.tv_calculate)
    TextView tv_calculate;
    @BindView(R.id.tv_result)
    TextView tv_result;
    @BindView(R.id.tv_get_red_packet)
    TextView redPacket;
    @BindView(R.id.commontitle)
    CommonTitleBarView commontitle;

    @Override
    public int getLayout() {
        return R.layout.activity_incometax;
    }

    @Override
    public void initData() {
         etIncome.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                  etAccumulationfund.setText(editable.toString());
                  etInsurance.setText(editable.toString());
             }
         });
         commontitle.setCommonTitle(View.INVISIBLE,View.INVISIBLE,View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
         commontitle.setCenterText(R.string.title);

    }

    @OnClick(R.id.tv_get_red_packet)
    public  void getRedPacket(){
        Intent intent = new Intent(this,RedPacketActivity.class);
        startActivity(intent);
        intoAnim();

    }
    @OnClick(R.id.tv_calculate)
    public  void caculate(){
        String  incomeStr =etIncome.getText().toString();
        if(TextUtils.isEmpty(incomeStr)){
            incomeStr="0";
        }

        double income = Double.parseDouble(incomeStr);
        if(income<=0){
            Toast.makeText(this,R.string.incometoohigh,Toast.LENGTH_SHORT).show();
            return;
        }
        if(income> Constant.MAXINCOME){
            Toast.makeText(this,R.string.incometomany,Toast.LENGTH_SHORT).show();
            return;
        }
        String baseAccumulationfundStr =etAccumulationfund.getText().toString();
        if(TextUtils.isEmpty(baseAccumulationfundStr)){
            baseAccumulationfundStr="0";
        }
        double baseAccumulationfund =Double.parseDouble(baseAccumulationfundStr);
        if(baseAccumulationfund>income){
            Toast.makeText(this,R.string.found_toomany,Toast.LENGTH_SHORT).show();
            return;
        }
        String baseInsuranceStr =etInsurance.getText().toString();
        if(TextUtils.isEmpty(baseInsuranceStr)){
            baseInsuranceStr="0";
        }
        double baseInsurance =Double.parseDouble(baseInsuranceStr);
        if(baseInsurance>income){
            Toast.makeText(this,R.string.insurance_toomany,Toast.LENGTH_SHORT).show();
            return;
        }
        calculate(income,baseAccumulationfund,baseInsurance);




    }

    private void calculate(double income,double baseAccumulationfund,double baseInsurance){
        double accumulationfund = AccumulationFundUtils.getAccumulationFund(baseAccumulationfund);
        double insurance = InsuranceUtils.getInsurance(baseInsurance);
        double oldTax = TaxUtils.getTax(income-accumulationfund-insurance,
                                            getResources().getIntArray(R.array.oldLevels),
                                           getResources().getIntArray(R.array.taxRates));
        double tax = TaxUtils.getTax(income-accumulationfund-insurance,
                getResources().getIntArray(R.array.levels),
                getResources().getIntArray(R.array.taxRates));
        String result ="到手工资:  %.2f元\n"+
                       "公积金:  %.2f元\n"+
                       "社保:  %.2f元\n"+
                       "个人所得税:  %.2f元\n"+
                       "比税改前少缴:  %.2f元";
        tv_result.setVisibility(View.VISIBLE);

        tv_result.setText(String.format(result,income - accumulationfund-insurance-tax
                                                ,accumulationfund
                                                ,insurance
                                               ,tax
                                               ,oldTax-tax));

    }
}
