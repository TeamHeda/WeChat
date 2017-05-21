package com.henu.example.com.wechat.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.WXServer;
import com.henu.example.com.wechat.main.okhttp.JudgeSuccess;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity {
    Button register, nation;
    EditText phone, pass;
    TextView agreement;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.button_Register_register);
        nation = (Button) findViewById(R.id.btn_select_nation);
        phone = (EditText) findViewById(R.id.editText_phonenumber);
        pass = (EditText) findViewById(R.id.edit_password);
        agreement = (TextView) findViewById(R.id.textView_agree);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().length() != 0 && pass.getText().length() != 0) {
                    register();
                } else {
                    Toast.makeText(RegisterActivity.this, "用户名跟密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void register() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody =new  FormBody.Builder()
                        .add("username", phone.getText().toString())
                        .add("password", pass.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url(WXServer.URL_REGISTER)
                        .post(requestBody)
                        .build();

                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSON(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSON(String jsondata){
        Log.i("feiei",jsondata);

        Gson gson=new Gson();
        JudgeSuccess jj=gson.fromJson(jsondata,JudgeSuccess.class);
//        List<JudgeSuccess> judge=gson.fromJson(jsondata,new TypeToken<List<JudgeSuccess>>(){}.getType());
//        for (JudgeSuccess j:judge){
//            Log.i("code",j.getCode()+"");
//            Log.i("judge",j.getJudge());
            Log.i("gg",jj.getCode()+jj.getMsg());
            if(jj.getCode()==200) {
                Log.i("",jj.getMsg()+jj.getCode()+"");
                mhander.sendEmptyMessage(0);
            }
            if(jj.getCode()==400){
                Log.i("",jj.getMsg()+jj.getCode()+"");
                mhander.sendEmptyMessage(1);
            }

    }
        Handler mhander=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what=msg.what;
            switch (what){
                case 0:
                    Log.e("feiei", "注册成功");
                    Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username",phone.getText().toString());
                    intent.putExtra("password",pass.getText().toString());
                    startService(intent);
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    break;

                case 1:
                    Log.e("feiei", "注册失败");
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                    break;
            }

               // try {
                 //       EMClient.getInstance().createAccount(phone.getText().toString().trim(), pass.getText().toString().trim());
                        //Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();


                //} catch (HyphenateException e) {
                  //      e.printStackTrace();
                         //Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        //;

                //}


        }
        };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
