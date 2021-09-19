package com.airtonsiq.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class createActivity extends AppCompatActivity {

    private Button backButotn, nextButton, botao;
    private EditText query;
    private ImageView colar;
    private TextView cliqueID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        backButotn = findViewById(R.id.buttonIDback);
        nextButton = findViewById(R.id.buttonIDnext);
        query = findViewById(R.id.queryID);
        botao = findViewById(R.id.btn);
        colar = findViewById(R.id.imageView6);
        cliqueID = findViewById(R.id.cliqueID);

        cliqueID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createActivity.this, tipodeDadosSQL.class);
                startActivity(intent);
                finish();}
        });

        colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("CREATE TABLE CLIENTE (CODIGO INT, NOME CHAR, CPF CHAR);");
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(query.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Digite a query, por favor.", Toast.LENGTH_LONG).show();
                }else{
                    String querytext = query.getText().toString();
                    try{
                        SQLiteDatabase bancoDados = openOrCreateDatabase( "NovoBanco",MODE_PRIVATE, null);
                        bancoDados.execSQL(querytext);
                        Toast.makeText(getApplicationContext(), "Tabela criada com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                    }catch(Exception e){
                        //e.printStackTrace();
                        //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Erro de Sintaxe! Verifique se a tabela já foi criada antes ou use o botão Colar Exemplo.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        backButotn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createActivity.this, insertActivity.class);
                startActivity(intent);
            }
        });
    }
}