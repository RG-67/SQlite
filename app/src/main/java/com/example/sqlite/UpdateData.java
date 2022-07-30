package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    //  Creating variables for EditTexts.
    private EditText updtName, updtNumber, updtEmail;

    //  Creating variables for Button.
    private Button update, delete;

    //  Creating variables for DBHandler (Database handler).
    private DBHandler dbHandler;

    //  Creating String variables for id, name, number, email.
    String personId, personName, personNumber, personEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        //  Finding variables through it's ids.
        updtName = findViewById(R.id.edtName);
        updtNumber = findViewById(R.id.edtNumber);
        updtEmail = findViewById(R.id.edtEmail);
        update = findViewById(R.id.add);
        delete = findViewById(R.id.delete);

        //  Creating object of DBHandler.
        dbHandler = new DBHandler(UpdateData.this);

        //  Through the below lines we are getting and passing data for the adapter (DataAdapter).
        personId = getIntent().getStringExtra("id");
        personName = getIntent().getStringExtra("name");
        personNumber = getIntent().getStringExtra("number");
        personEmail = getIntent().getStringExtra("email");

        //  Setting data to the "UpdateData" class or showing data "activity_update_data.xml".
        updtName.setText(personName);
        updtNumber.setText(personNumber);
        updtEmail.setText(personEmail);

        // Setting "setOnClickListener" method to "update" button.
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Calling "DBHandler" class through it's variable (dbHandler) and passing all values from the EditTexts to the "updateData" method.
                dbHandler.updateData(personId, updtName.getText().toString(), updtNumber.getText().toString(), updtEmail.getText().toString());

                //  After successfully all the values the below Toast message will appear.
                Toast.makeText(UpdateData.this, "Data updated", Toast.LENGTH_SHORT).show();

                //  Creating object of "Intent".
                Intent intent = new Intent(UpdateData.this, MainActivity.class);

                //  Starting "MainActivity".
                startActivity(intent);
            }
        });

        //  Setting "setOnClickListener" method to the "delete" button.
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Calling "DBHandler" class through it's variable "dbHandler" and getting id (personId) as a key and pass the value to the "deleteData" method.
                dbHandler.deleteData(personId);

                //  After completing delete process below Toast message will appear.
                Toast.makeText(UpdateData.this, "Data deleted", Toast.LENGTH_SHORT).show();

                //  Creating object of "Intent".
                Intent intent = new Intent(UpdateData.this, MainActivity.class);

                //  Starting "MainActivity".
                startActivity(intent);
            }
        });
    }
}