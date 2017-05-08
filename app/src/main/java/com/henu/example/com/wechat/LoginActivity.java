package com.henu.example.com.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView username,register;
    EditText pass;
    ImageView user_photo;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user_photo=(ImageView) findViewById(R.id.user_photo);
        username=(TextView)findViewById(R.id.text_usreName);
        pass=(EditText)findViewById(R.id.editText_password);
        login=(Button)findViewById(R.id.button_Login_login);
        register=(TextView)findViewById(R.id.textView_to_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
