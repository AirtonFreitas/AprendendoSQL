package com.airtonsiq.aprendendosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button nextButton;
    private TextView insert, select,update, delete, create, alter, drop;
    private Toolbar toolbar;


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
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbarInicio:
                homePage();
                break;
            case R.id.toolbarCertificado:
                Congrats();
                break;
            case R.id.toolbarDonate:
                Donate();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    public void homePage() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Congrats() {
        Intent intent = new Intent(MainActivity.this, congratulationsActivity.class);
        startActivity(intent);
        finish();
    }

    public void Donate() {
        Intent intent = new Intent(MainActivity.this, activity_donate.class);
        startActivity(intent);
        finish();
    }
}