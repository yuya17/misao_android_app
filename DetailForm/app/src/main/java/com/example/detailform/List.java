package com.example.detailform;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.os.Build.VERSION_CODES.M;

public class List extends ListActivity{

    TextView student_Id;
    Button btn_add;
    Button btn_listall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btn_add =(Button) findViewById(R.id.btn_Add);
        btn_listall = (Button) findViewById(R.id.btn_ShowList);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(List.this,MainActivity.class);
                i.putExtra("student_Id",0);//最初からつくり直すため
                startActivity(i);
            }
        });

        /*tv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = Helper.getData();
                c.moveToFirst();
                String username = c.getString(c.getColumnIndex("Name"));
                String useremail = c.getString(c.getColumnIndex("Email"));
                String userage =c.getString(c.getColumnIndex("Age"));
                Helper.update(username,useremail,userage);
                Intent i = new Intent(List.this,MainActivity.class);
                startActivity(i);

            }
        });
        */
        btn_listall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRepo repo = new StudentRepo(List.this);

                ArrayList<HashMap<String,String>> studentList =  repo.getStudentList();
                if(studentList.size()!=0) {
                    ListView lv = getListView();
                    //それぞれのItemにボタン機能をつける
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            student_Id = (TextView) view.findViewById(R.id.student_id);
                            String studentId = student_Id.getText().toString();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra("student_Id", Integer.parseInt(studentId));
                            startActivity(i);
                        }
                    });
                    ListAdapter adapter = new SimpleAdapter(List.this,studentList,R.layout.view_student_entry,new String[] {"id","name"}, new int[] {R.id.student_id, R.id.student_name});
                    setListAdapter(adapter);
                }
                else{
                    Toast.makeText(List.this,"No student!", Toast.LENGTH_SHORT).show();
                }

               /* Cursor c = Helper.getData();
                c.moveToFirst();
                do {
                    String name = c.getString(c.getColumnIndex("Name"));
                    tv_list.append(name+"\n");
                }
                while(c.moveToNext());
                */
            }
        });
    }
}
