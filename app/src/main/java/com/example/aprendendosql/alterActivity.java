package com.example.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class alterActivity extends AppCompatActivity {

    private Button backButotn, nextButton;
    private Button btnRenomeando, btnExcluindo, btnAdicionando;
    private EditText queryRenomeando, queryExcluindo, queryAdicionando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        backButotn = findViewById(R.id.buttonIDback);
        nextButton = findViewById(R.id.buttonIDnext);
        btnAdicionando = findViewById(R.id.btn_adiciona);
        btnExcluindo = findViewById(R.id.btn_excluir);
        btnRenomeando = findViewById(R.id.btn_renomeando);
        queryRenomeando = findViewById(R.id.queryIDrenomeando);
        queryExcluindo = findViewById(R.id.queryIDexcluir);
        queryAdicionando = findViewById(R.id.queryIDadicionando);


        btnAdicionando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queryAdicionando.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Digite a query acima, por favor.", Toast.LENGTH_LONG).show();
                }else{
                    String querytext = queryAdicionando.getText().toString();
                    try{
                        SQLiteDatabase bancoDados = openOrCreateDatabase( "NovoBanco",MODE_PRIVATE, null);
                        bancoDados.execSQL(querytext);
                        Toast.makeText(getApplicationContext(), "Comando enviado com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnExcluindo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queryExcluindo.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Digite a query acima, por favor.", Toast.LENGTH_LONG).show();
                }else{
                    String querytext = queryExcluindo.getText().toString();
                    try{
                        SQLiteDatabase bancoDados = openOrCreateDatabase( "NovoBanco",MODE_PRIVATE, null);
                        bancoDados.execSQL(querytext);
                        Toast.makeText(getApplicationContext(), "Comando enviado com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnRenomeando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queryRenomeando.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Digite a query acima, por favor.", Toast.LENGTH_LONG).show();
                }else{
                    String querytext = queryRenomeando.getText().toString();
                    try{
                        SQLiteDatabase bancoDados = openOrCreateDatabase( "NovoBanco",MODE_PRIVATE, null);
                        bancoDados.execSQL(querytext);
                        Toast.makeText(getApplicationContext(), "Comando enviado com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        backButotn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alterActivity.this, dropActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alterActivity.this, Splash.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
