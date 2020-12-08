package com.example.sqlitedatastoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText e1,e2;DatabaseHelper db;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editTextTextPersonName2);
        e2=(EditText)findViewById(R.id.editTextNumberPassword2);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();  String s2=e2.getText().toString();
                Boolean ch=db.namePass(s1,s2);
                if(ch==true)
                   startActivity(new Intent(MainActivity2.this,MainActivity3.class));
else
                    Toast.makeText(getApplicationContext(),"Wrong username or Password",Toast.LENGTH_SHORT).show();

            }
        });
    }
}