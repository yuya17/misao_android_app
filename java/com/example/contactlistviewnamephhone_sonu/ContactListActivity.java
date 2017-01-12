package com.example.contactlistviewnamephhone_sonu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.Manifest;

public class ContactListActivity extends Activity implements AdapterView.OnItemClickListener {//Main Class

    ListView listView;
    private List<ContactAttribute> contactlist = new ArrayList<ContactAttribute>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);//listviewのitemにボタン機能を追加
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);//ContentResolver Class is to include staic getContentResolver().
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactAttribute person = new ContactAttribute();
            person.setName(name);
            person.setPhone(phone);
            contactlist.add(person);
        }
        cursor.close();
        ContactAdapter adapter = new ContactAdapter(ContactListActivity.this,R.layout.user_row,contactlist);
        listView.setAdapter(adapter);

        if(null!=contactlist && contactlist.size()!=0){
            Collections.sort(contactlist, new Comparator<ContactAttribute>() {
                @Override
                public int compare(ContactAttribute lhs, ContactAttribute rhs) {
                    return lhs.getname().compareTo(rhs.getname());
                }
            });
            AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this).create();
            alert.setTitle("Title");
            alert.setMessage(contactlist.size()+" Contact Found!!");
            alert.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();//This is gone.
                }
            });
            alert.show();
        }

        else{
            Toast.makeText(getApplicationContext(),"No Contact Phone",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       ContactAttribute attribute = (ContactAttribute) listView.getItemAtPosition(position);
       showCallDialog(attribute.getname(),attribute.getphone());
    }

    private void showCallDialog(String name, final String phone) {
        AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this).create();
        alert.setTitle("Call??");
        alert.setMessage("Are you sure want to call "+ name);
        alert.setButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phoneNo = "Tel:" + phone;
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNo));
                if(ActivityCompat.checkSelfPermission(ContactListActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(i);
            }
        });

        alert.setButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }
}
