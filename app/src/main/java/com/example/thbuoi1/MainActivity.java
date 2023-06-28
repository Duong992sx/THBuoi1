package com.example.thbuoi1;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> ContactList;
    private ContactAdapter ListAdapter;
    private EditText etSearch;
    private ListView lstContact;
    private Button btnAdd,btnDel;
    private int SelectedItemId;
     private MyDB db;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=new MenuInflater(this);
//        inflater.inflate(R.menu.optionmenu,menu);
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater inflater1 = new MenuInflater(this);
        inflater1.inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
 @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b=data.getExtras();
        int id=b.getInt("Id");
        String name=b.getString("Name");
        String phone=b.getString("Phone");
        Contact newcontact=new Contact(id,name,phone, true);
        if(requestCode==200 && resultCode==150)
        {
           ContactList.add(new Contact(id,name,phone,false));
          //  db.addContact(newcontact);

        }
        else if(requestCode==100 && resultCode==201)
        {
          ContactList.set(SelectedItemId,new Contact(id,name,phone,false));
          //  db.updateContact(id,newcontact);
        }
        resetData();
        ListAdapter.notifyDataSetChanged();
        lstContact.setAdapter(ListAdapter);

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Contact c = ContactList.get(SelectedItemId);
        switch (item.getItemId()) {

            case R.id.mnuEdit:

                //1.tao intent de mo subactivity
                Intent intent=new Intent(MainActivity.this,AddContact.class);
                //2.truyen du lieu sang subactivity bang bundle neu can

                Bundle b=new Bundle();
                b.putInt("Id", c.getId());
                b.putString("Name",c.getName());
                b.putString("Phone",c.getPhone());
                intent.putExtras(b);
                //3 mo subactivity bang cach goi ham
                //staractivity hoac staractivityforresult;
                startActivityForResult(intent,100);
                break;

            case R.id.sameName:
                int dem=0;
                String[] sameName =  ContactList.get(SelectedItemId).getName().split("\\s");
                for (Contact contact: ContactList
                     ) {
                    String[] name = contact.getName().split("\\s");
                    if(sameName[sameName.length-1].compareTo(name[name.length-1]) == 0){
                        dem++;
                        }
                    }
                String result = "Có " + dem + " người tên " + sameName[sameName.length-1];
                Toast.makeText(MainActivity.this,
                        result, Toast.LENGTH_LONG).show();



        }
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuAES:
                SortASC();


                break;
            case R.id.mnuDES:
                SortDes();


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetData(){
        db = new MyDB(MainActivity.this, "ContacDB",null,1);
        ContactList  = db.getAllContact();
        ListAdapter = new ContactAdapter(ContactList, this);
        lstContact.setAdapter(ListAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //db=new MyDB(this,"ContacDB",null,1);
        ContactList=new ArrayList<Contact>();
        ContactList.add(new Contact(1, "Nguyen Van An", "0337693148", true));
    ContactList.add(new Contact(2, "Tran Van Ba", "0337801993", true));
      ContactList.add(new Contact(3, "Hoang Thuy Dung", "0337793145", false));
    ContactList.add(new Contact(4, "Dinh Thi Tuyen", "0339693148", true));
//       ContactList.add(new Contact(5, "Dang Van Hien", "0337693479", true));
//         db.addContact(new Contact(1,"Nguyễn Văn An","0987976589"));
//         db.addContact(new Contact(2,"Đào Thu Hà","0987976588"));
//        db.addContact(new Contact(3,"Nguyễn  Thị Lụa","0987976587"));
       //   ContactList =db.getAllContact();
       ListAdapter=new ContactAdapter(ContactList,this);
       btnDel = findViewById(R.id.btn_del);
        etSearch=findViewById(R.id.etSearch);
        lstContact=findViewById(R.id.lv_contact);
        btnAdd=findViewById(R.id.btn_add);
        lstContact.setAdapter(ListAdapter);



         lstContact.setAdapter(ListAdapter);
        registerForContextMenu(lstContact);
        lstContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                SelectedItemId=i;
                return false;
            }
        });
         btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddContact.class),200);
            }
        });
         ArrayList<Contact> ContacList1 = new ArrayList<Contact>();
        btnDel.setOnClickListener((view -> {
            for (int i = 0; i < ContactList.size(); i++) {
                if(ContactList.get(i).getStatus()) {
                    ContacList1.add(ContactList.get(i));
                   // db.deleteContact(ContactList.get(i).getId());
                }
            }
            resetData();
            ContactList.removeAll(ContacList1);
            lstContact.setAdapter(ListAdapter);
        }));


    }





     private void SortASC ()
        {
            Collections.sort(ContactList, new Contact.NameAsc());
            ListAdapter.notifyDataSetChanged();
        }
        private void SortDes ()
        {
            Collections.sort(ContactList, new Contact.NameDes());
            ListAdapter.notifyDataSetChanged();
        }
}int  price =  hoadonlist.get(selectedid).getDongia()*hoadonlist.get(selectedid).getSongayluutru();


