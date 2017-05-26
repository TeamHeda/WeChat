package com.henu.example.com.wechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.henu.example.com.wechat.Helper;
import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.Serices.ContactsService;
import com.henu.example.com.wechat.main.activity.MainActivity;
import com.henu.example.com.wechat.main.fragment.MainFragmentActivity;
import com.hyphenate.chat.EMClient;

public class StartActivity extends AppCompatActivity {
    private RelativeLayout rootLayout;
    private static final int sleepTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent intent = new Intent(StartActivity.this, MainActivity.class);
//                startActivity(intent);
//                StartActivity.this.finish();
//            }
//        }, 2000);
        rootLayout = (RelativeLayout) findViewById(R.id.activity_start);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(Helper.getInstance().isLoggedIn()){
                    long start = System.currentTimeMillis();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();

                    long costTime = System.currentTimeMillis() - start;
                    //wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    startService(new Intent(StartActivity.this, ContactsService.class));
                    //enter main screen
                    startActivity(new Intent(StartActivity.this, MainFragmentActivity.class));
                    //获取下群组信息
                    //startService(new Intent(StartActivity.this, GroupService.class));
                    finish();
                }else{
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    finish();
                }
            }
        }).start();
    }
}
