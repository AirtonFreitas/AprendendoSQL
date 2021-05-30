package com.example.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tipodeDadosSQL extends AppCompatActivity {

    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipode_dados_sql);
        backBtn = findViewById(R.id.buttonIDback);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tipodeDadosSQL.this, createInstruction.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
