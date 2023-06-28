package com.example.thbuoi1;
import static com.example.thbuoi1.R.id.btn_edit_user1;
import static com.example.thbuoi1.R.id.editId;
import static com.example.thbuoi1.R.id.img_avt;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddContact extends AppCompatActivity {

    EditText editText_name, editText_phone,id;
    Button button_add;
    ImageView img;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        id =  findViewById(editId);
        editText_name = findViewById(R.id.editfullname);
        editText_phone = findViewById(R.id.editphone);
        button_add = findViewById(R.id.btnOk);
        img=findViewById(R.id.imageView2);
        Intent intent=getIntent();
        //lay bundle
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            int idd = bundle.getInt("Id");
            String name=bundle.getString("Name");
            String phone=bundle.getString("Phone");
             id.setText(String.valueOf(idd));
            editText_name.setText(name);
            editText_phone.setText(phone);
            button_add.setText("Edit");
        }



        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editText_name.getText().toString();
                String p = editText_phone.getText().toString();
                Intent intent = new Intent(AddContact.this, MainActivity.class);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                Bundle bundle = new Bundle();
//                intent.putExtra("name", n);
//                intent.putExtra("phone", p);
                bundle.putInt("Id", Integer.parseInt(id.getText().toString()));
                bundle.putString("Name", n);
                bundle.putString("Phone", p);
                intent.putExtras(bundle);
                setResult(150,intent);
               if(button_add.getText()=="Edit")
                  setResult(201, intent);
                finish();
            }
        });

        ActivityResultLauncher<Intent> resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>()
        {

            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData() !=null)
                {
                    if(bitmap !=null)
                    {
                        bitmap.recycle();
                    }
                    Uri uri=result.getData().getData();
                    try{
                        InputStream stream=getContentResolver().openInputStream(uri);
                        bitmap= BitmapFactory.decodeStream(stream);
                        stream.close();
                        img.setImageBitmap(bitmap);


                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }
}

