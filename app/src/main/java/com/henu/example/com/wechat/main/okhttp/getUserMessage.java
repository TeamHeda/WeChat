package com.henu.example.com.wechat.main.okhttp;

import com.henu.example.com.wechat.main.WXServer;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by node4 on 2017/5/19.
 */

public class getUserMessage {
    public void getMess(final String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody =new  FormBody.Builder()
                            .add("username", name)
                            .build();
                    Request request = new Request.Builder()
                            .url(WXServer.URL_REGISTER)
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
