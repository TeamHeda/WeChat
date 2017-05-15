package com.henu.example.com.wechat.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.henu.example.com.wechat.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout switch_message,switch_uninter,switch_chat,switch_commom,switch_secret,switch_account,switch_feedback;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }
    private void initView(){
        switch_message=(RelativeLayout)findViewById(R.id.rl_switch_notification);
        switch_uninter=(RelativeLayout)findViewById(R.id.rl_switch_sound);
        switch_chat=(RelativeLayout)findViewById(R.id.rl_switch_vibrate);
        switch_commom=(RelativeLayout)findViewById(R.id.rl_switch_speaker);
        switch_secret=(RelativeLayout)findViewById(R.id.rl_switch_secret);
        switch_account=(RelativeLayout)findViewById(R.id.rl_switch_delete_msg_when_exit_group);
        switch_feedback=(RelativeLayout)findViewById(R.id.rl_switch_adaptive_video_encode);
        logout=(Button)findViewById(R.id.btn_logout);

        switch_message.setOnClickListener(this);
        switch_uninter.setOnClickListener(this);
        switch_chat.setOnClickListener(this);
        switch_commom.setOnClickListener(this);
        switch_secret.setOnClickListener(this);
        switch_account.setOnClickListener(this);
        switch_feedback.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_switch_notification:

                break;
        }
    }
}
