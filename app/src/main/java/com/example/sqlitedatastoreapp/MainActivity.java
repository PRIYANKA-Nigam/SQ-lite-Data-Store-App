package com.example.sqlitedatastoreapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;DatabaseHelper db;
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editTextTextPersonName2);
        e2=(EditText)findViewById(R.id.editTextNumberPassword2);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
b4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Integer del=db.deleteData(e1.getText().toString());
        if(del>0)
            Toast.makeText(MainActivity.this,"DATA DELETED",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"DATA not DELETED",Toast.LENGTH_SHORT).show();

    }
});
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.Display();
                if(c.getCount()==0){
                    showData("Error","Nothing is stored");
                    return;
                }
                StringBuffer stringBuffer=new StringBuffer();
                while (c.moveToNext()){
                    stringBuffer.append("Username: " +c.getString(0)+"\n");
                    stringBuffer.append("Password: " +c.getString(1)+"\n\n");
                }
                showData("data",stringBuffer.toString());
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
String s1=e1.getText().toString();  String s2=e2.getText().toString();
if(s1.equals(" ")||s2.equals(" ")){
    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
} else {
    Boolean ch=db.check(s1);
    if(ch==true){
        Boolean in=db.insert(s1,s2);
        if(in==true)
        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();}
    else
        Toast.makeText(getApplicationContext(),"User Name Already registered",Toast.LENGTH_SHORT).show(); } }});


    }

public  void showData(String title,String message){
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}
}