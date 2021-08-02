package com.example.gridoncanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//      import custom view via id
        final MyCanvas gridView = findViewById(R.id.gridView);


        gridView.setNumColumns(5);
        gridView.setNumRows(5);

                
            }

    }

