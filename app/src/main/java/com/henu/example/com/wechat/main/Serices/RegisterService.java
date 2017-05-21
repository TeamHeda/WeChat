package com.henu.example.com.wechat.main.Serices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterService extends Service {
    private String name;
    private String pass;
    public RegisterService(String name,String pass) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        name=intent.getStringExtra("username");
        pass=intent.getStringExtra("password");
        Log.i("服务",name+pass);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    EMClient.getInstance().createAccount(name.trim(),pass.trim());
                    Log.i("服务","注册成功");
                    Toast.makeText(RegisterService.this,"注册成功",Toast.LENGTH_LONG).show();
                } catch (HyphenateException e) {
                    Log.i("服务","注册失败");
                    Toast.makeText(RegisterService.this,"注册失败",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
