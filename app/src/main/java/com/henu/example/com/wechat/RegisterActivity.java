package com.henu.example.com.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    Button register,nation;
    EditText phone;
    TextView agreement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=(Button)findViewById(R.id.button_Register_register);
        nation=(Button)findViewById(R.id.btn_select_nation);
        phone=(EditText)findViewById(R.id.editText_phonenumber);
        agreement=(TextView)findViewById(R.id.textView_agree);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
