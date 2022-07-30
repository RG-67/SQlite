package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

    //  Creating variables for Arraylist, DataAdapter, DBHandler and RecyclerView.
    private ArrayList<DataModal> dataModalArrayList;
    private DataAdapter dataAdapter;
    private DBHandler dbHandler;
    private RecyclerView viewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        //  Initializing variable of Arraylist.
        dataModalArrayList = new ArrayList<>();

        //  Initializing variable of "DBHandler".
        dbHandler = new DBHandler(ViewData.this);

        //  Getting data arraylist from "DBHandler" class.
        dataModalArrayList = dbHandler.readData();

        //  Initializing variable of "DataAdapter".
        dataAdapter = new DataAdapter(dataModalArrayList, ViewData.this);

        //  Initializing variable (viewData) of RecyclerView.
        viewData = findViewById(R.id.viewData);

        //  Setting width and height as fixed of "viewData".
        viewData.setHasFixedSize(true);
        //  Setting layoutManager for the RecyclerView.
        viewData.setLayoutManager(new LinearLayoutManager(ViewData.this));

        //  Setting adapter (dataAdapter) for the RecyclerView.
        viewData.setAdapter(dataAdapter);
    }
}