package com.example.thbuoi1;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

   private ArrayList<Contact> data;//nguon du lieu cho baseadapter,gom danh sach contact
    private Activity context;
    private LayoutInflater inflater;

    public ContactAdapter() {

    }

    public ContactAdapter(ArrayList<Contact> data, Activity activity) {
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }//tra ve mot phan tu

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }//tra ve

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null)
            v = inflater.inflate(R.layout.userlayout, null);
        ImageView imageView = v.findViewById(R.id.img_avt);
        ImageView imageView1 = v.findViewById(R.id.img_call);
        ImageView imageVie2= v.findViewById(R.id.img_sms);
        CheckBox cb_status=v.findViewById(R.id.cb_status);
        TextView tvname = v.findViewById(R.id.tv_name);
        tvname.setText(data.get(i).getName());
        TextView tvphone = v.findViewById(R.id.tv_phone);
        tvphone.setText(data.get(i).getPhone());

          cb_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        //Code khi trạng thái check thay đổi
                        if(b){
                            data.get(i).setStatus(true);
                        }else {
                            data.get(i).setStatus(false);
                        }
                    }
                });

        return v;


    }
}