package com.example.detailform;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.attr.name;

public class MainActivity extends AppCompatActivity{

    Button btn_save;
    Button btn_delete;
    Button btn_close;
    EditText et_name;
    EditText et_email;
    EditText et_age;
    private int _Student_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_save = (Button) findViewById(R.id.btn_Save);
        btn_delete = (Button) findViewById(R.id.btn_Delete);
        btn_close = (Button) findViewById(R.id.btn_Close);
        et_name  = (EditText) findViewById(R.id.et_Name);
        et_email  = (EditText) findViewById(R.id.et_Email);
        et_age = (EditText) findViewById(R.id.et_Age);
        _Student_Id =0;
        Intent i = getIntent();
        _Student_Id =i.getIntExtra("student_Id",0);
        StudentRepo repo = new StudentRepo(this);
        StudentInfo student = new StudentInfo();
        student = repo.getStudentById(_Student_Id);

        et_age.setText(String.valueOf(student.age));
        et_name.setText(student.name);
        et_email.setText(student.email);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRepo repo = new StudentRepo(MainActivity.this);
                StudentInfo student = new StudentInfo();
                student.age=Integer.parseInt(et_age.getText().toString());
                student.name=et_name.getText().toString();
                student.email=et_email.getText().toString();
                student.student_ID=_Student_Id;

                if (_Student_Id==0){
                    _Student_Id = repo.insert(student);

                    Toast.makeText(MainActivity.this,"New Student Insert",Toast.LENGTH_SHORT).show();
                }
                else{
                    repo.update(student);
                    Toast.makeText(MainActivity.this,"Student Record updated",Toast.LENGTH_SHORT).show();
                }
            }

               /* String uname  = et_name.getText().toString();
                String uemail = et_email.getText().toString();
                String uage = et_age.getText().toString();

                boolean answer = Helper.inserts(uname,uemail,uage);
                if(answer){
                        et_name.setText("");
                        et_email.setText("");
                        et_age.setText("");
                        Toast.makeText(getApplicationContext(),"OKKKKK",Toast.LENGTH_LONG).show();

                }
                else{
                        Toast.makeText(getApplicationContext(),"NOOOOO",Toast.LENGTH_LONG).show();
                }
            }*/
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentRepo repo = new StudentRepo(MainActivity.this);
                repo.delete(_Student_Id);
                Toast.makeText(MainActivity.this, "Student Record Deleted", Toast.LENGTH_SHORT);
                finish();
               // finish();
               /* String uname = et_name.getText().toString();
                String uemail = et_email.getText().toString();
                String uage = et_age.getText().toString();
                if(uname.contentEquals("")||uemail.contentEquals("")||uage.contentEquals("")){
                    Toast.makeText(MainActivity.this,"NOT INCLUDE",Toast.LENGTH_LONG).show();
                }
                else{
                    Helper.delete(uname,uemail,uage);
                }*/
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }
}
