package com.example.thbuoi1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    private EditText etFullName;
    private EditText etPhone;
    private Button btnOk;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etFullName=findViewById(R.id.edt_name);
        etPhone=findViewById(R.id.edt_phone);
        btnOk=findViewById(R.id.btn_edit_user);        //lay intent tu mainactivity chuyen sang
        Intent intent=getIntent();
        //lay bundle
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {

            String name=bundle.getString("Name");
            String phone=bundle.getString("Phone");
            etFullName.setText(name);
            etPhone.setText(phone);

        }
       btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay du lieu gui ve cho main activity

                String name=etFullName.getText().toString();
                String phone=etPhone.getText().toString();
                Intent intent=new Intent();
                 Bundle b=new Bundle();

                b.putString("Name",name);
                b.putString("Phone",phone);
                intent.putExtras(b);
                setResult(150,intent);
                finish();
            }
        });


    }
    }
