package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insert,update,view,delete;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contactinfo);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        view = findViewById(R.id.view);
        delete = findViewById(R.id.delete);

        dbHandler = new DBHandler(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String contactText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkInsert = dbHandler.insertData(nameText,contactText,dobText);

                if(checkInsert == true){
                    Toast.makeText(MainActivity.this,"Insert",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this,"Fail Insert",Toast.LENGTH_SHORT).show();
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String contactText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkupdate = dbHandler.updateData(nameText,contactText,dobText);

                if (checkupdate == true){
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this,"Fail Update",Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();

                Boolean checkDelete = dbHandler.deleteData(nameText);
                if (checkDelete == true) {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Fail Delete", Toast.LENGTH_SHORT).show();
                }
            }

            });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor results = dbHandler.getData();

                if(results.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Data!!", Toast.LENGTH_SHORT).show();

                    return;

                }
                StringBuffer buffer = new StringBuffer();

                while (results.moveToNext()){
                    buffer.append("Name: "+results.getString(0)+"\n");
                    buffer.append("Date of Birth: "+results.getString(1)+"\n");
                    buffer.append("Contact No: "+results.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("User Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}