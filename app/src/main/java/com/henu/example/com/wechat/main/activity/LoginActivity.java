package com.henu.example.com.wechat.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.fragment.MainFragmentActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    EditText pass,username;
    ImageView user_photo;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user_photo=(ImageView) findViewById(R.id.user_photo);
        username=(EditText)findViewById(R.id.text_usreName);
        pass=(EditText)findViewById(R.id.editText_password);
        login=(Button)findViewById(R.id.button_Login_login);
        register=(TextView)findViewById(R.id.textView_to_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
        private void login(){
            EMClient.getInstance().login(username.getText().toString().trim(), pass.getText().toString().trim(), new EMCallBack() {
                @Override
                public void onSuccess() {
                    startActivity(new Intent(LoginActivity.this,MainFragmentActivity.class));
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
}
