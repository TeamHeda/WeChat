package com.henu.example.com.wechat.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.ui.ChatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
    }
    private void initView(){
        Button btnMsg = (Button) this.findViewById(R.id.btn_msg);
        Button btnAdd = (Button) this.findViewById(R.id.btn_add);
        ImageView iv_avatar = (ImageView) this.findViewById(R.id.iv_avatar);
        ImageView iv_sex = (ImageView) this.findViewById(R.id.iv_sex);
        TextView tv_name = (TextView) this.findViewById(R.id.tv_name);
        TextView tv_mobile = (TextView) this.findViewById(R.id.tv_mobile);
        TextView tv_sign = (TextView) this.findViewById(R.id.tv_sign);
        TextView tv_region = (TextView) this.findViewById(R.id.tv_region);
        TextView tv_mixin = (TextView) this.findViewById(R.id.tv_mixin);

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDetailsActivity.this, ChatActivity.class));
            }
        });
    }
}
