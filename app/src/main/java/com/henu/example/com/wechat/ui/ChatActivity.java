package com.henu.example.com.wechat.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.henu.example.com.wechat.R;
import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends AppCompatActivity {
    private EaseChatFragment chatFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatFragment=new EaseChatFragment();
    }
}
