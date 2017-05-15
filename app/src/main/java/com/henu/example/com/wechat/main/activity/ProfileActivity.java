package com.henu.example.com.wechat.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.henu.example.com.wechat.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_avatar;
    private TextView tv_name;
    private TextView tv_fxid;
    private TextView tv_sex;
    private TextView tv_sign;
    private TextView tv_region;
    private TextView tv_place;
    private String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();
    }
    private void initView(){
        iv_avatar = (ImageView) this.findViewById(R.id.iv_avatar);
        tv_name = (TextView) this.findViewById(R.id.tv_name);
        tv_fxid = (TextView) this.findViewById(R.id.tv_fxid);
        tv_sex = (TextView) this.findViewById(R.id.tv_sex);
        tv_sign = (TextView) this.findViewById(R.id.tv_sign);
        tv_region = (TextView) this.findViewById(R.id.tv_region);
        tv_place=(TextView)this.findViewById(R.id.tv_place);



        this.findViewById(R.id.re_avatar).setOnClickListener(this);
        this.findViewById(R.id.re_name).setOnClickListener(this);
        this.findViewById(R.id.re_fxid).setOnClickListener(this);
        this.findViewById(R.id.re_sex).setOnClickListener(this);
        this.findViewById(R.id.re_region).setOnClickListener(this);
        this.findViewById(R.id.re_sign).setOnClickListener(this);
        this.findViewById(R.id.re_qrcode).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_avatar:
                break;
            case R.id.re_name:

                break;
            case R.id.re_fxid:

                break;
            case R.id.re_sex:

                break;
            case R.id.re_region:

            case R.id.re_sign:

                break;
            case R.id.re_qrcode:

                break;

        }
    }
}
