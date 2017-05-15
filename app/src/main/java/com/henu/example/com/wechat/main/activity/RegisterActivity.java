package com.henu.example.com.wechat.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.henu.example.com.wechat.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterActivity extends AppCompatActivity {
    Button register,nation;
    EditText phone,pass;
    TextView agreement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=(Button)findViewById(R.id.button_Register_register);
        nation=(Button)findViewById(R.id.btn_select_nation);
        phone=(EditText)findViewById(R.id.editText_phonenumber);
        pass=(EditText)findViewById(R.id.edit_password);
        agreement=(TextView)findViewById(R.id.textView_agree);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().length()!=0&&pass.getText().length()!=0)
                {
                    register();
                }else{
                    Toast.makeText(RegisterActivity.this,"用户名跟密码不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void register(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(phone.getText().toString().trim(), pass.getText().toString().trim());
                    //Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    Log.e("feiei","注册成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    Log.e("feiei","注册失败");
                }


            }
        }).start();
    }
}
