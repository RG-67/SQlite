package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //  Creating variables for EditText (Name, Number, Email address).
    private EditText edtName, edtNumber, edtEmail;

    //  Creating variables for Button (add, read).
    private Button add, read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Detecting EditText's and Button's IDs from xml.
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        add = findViewById(R.id.add);
        read = findViewById(R.id.read);

        //  Calling and create object of DBHandler (Database handler) class.
        DBHandler dbHandler = new DBHandler(MainActivity.this);

        //  Setting "OnclickListener" method for add_button.
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Creating variables as a string and import texts from EditText's IDs.
                String personName = edtName.getText().toString();
                String personNumber = edtNumber.getText().toString();
                String personEmail = edtEmail.getText().toString();

                //  Checking all Strings of EditText's IDs are either null or empty and if any of the Strings of the IDs are null or empty then the below Toast will appear as a message.
                if (personName == null  || personName.isEmpty() && personNumber == null || personNumber.isEmpty() && personEmail == null || personEmail.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all details", Toast.LENGTH_SHORT).show();
                }

                //  Checking all Strings of EditText's IDs are either null or empty and if all Strings are not null and empty then all text field's data added in database.
                if (personName != null && !personName.isEmpty() && personNumber != null && !personNumber.isEmpty() && personEmail != null && !personEmail.isEmpty()) {

                    //  Calling DBHandler (Database handler) class through the object (dbHandler) of DBHandler class and call the method (addData) for adding and insert the data in database and passing the created context to it.
                    dbHandler.addData(personName, personNumber, personEmail);

                    //  The below Toast message will appear if the above "if" condition will true.
                    Toast.makeText(MainActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();

                    //  Setting name, number and email in the database.
                    edtName.setText("");
                    edtNumber.setText("");
                    edtEmail.setText("");
                }
            }
        });

        //  Setting OnClickListener method for "Read Data" button.
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Below Toast message will appear after click on the "Read Data" button.
                Toast.makeText(MainActivity.this, "View data", Toast.LENGTH_SHORT).show();

                //  New page or activity will start or appear of "ViewData" after click on the "Read Data" button.
                Intent intent = new Intent(MainActivity.this, ViewData.class);
                startActivity(intent);
            }
        });
    }
}