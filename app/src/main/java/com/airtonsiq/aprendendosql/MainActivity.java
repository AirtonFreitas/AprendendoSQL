package com.airtonsiq.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button nextButton;
    private TextView insert, select,update, delete, create, alter, drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.buttonIDnext);
        insert = findViewById(R.id.cliqueID);
        select = findViewById(R.id.selectID);
        update = findViewById(R.id.updateID);
        delete = findViewById(R.id.deleteID);
        create = findViewById(R.id.createID);
        alter = findViewById(R.id.alterID);
        drop = findViewById(R.id.dropID);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, insertActivity.class);
                startActivity(intent);
                finish();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, selectActivity.class);
                startActivity(intent);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, updateActivity.class);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, deleteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, createActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, alterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dropActivity.class);
                startActivity(intent);
                finish();
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, createActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}