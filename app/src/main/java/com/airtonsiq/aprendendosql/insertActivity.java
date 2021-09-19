package com.airtonsiq.aprendendosql;
;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class insertActivity extends AppCompatActivity {

    private Button backButotn, nextButton, botao;
    private EditText query;
    private ImageView colar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        backButotn = findViewById(R.id.buttonIDback);
        nextButton = findViewById(R.id.buttonIDnext);
        query = findViewById(R.id.queryID);
        botao = findViewById(R.id.btn);

        colar = findViewById(R.id.imageView6);

        colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("INSERT INTO CLIENTE (CODIGO, NOME, CPF) " +
                        "VALUES " +
                        "(1, \"AIRTON S.\",\"154.854.846-21\")," +
                        "(2, \"JOAO C\",\"664.844.646-24\")," +
                        "(3, \"MARIA S.\",\"194.747.966-34\")," +
                        "(4, \"DANIEL\",\"154.854.654-21\")," +
                        "(5, \"PAULO C.\",\"664.844.981-24\");");
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
                        Toast.makeText(getApplicationContext(), "Dados inseridos com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                    }catch(Exception e){
                        e.printStackTrace();
                        //Log.e("Erro ao Criar Tabela",e.toString());
                        //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Erro de Sintaxe! Verifique se a tabela já foi criada ou use a opção Colar Exemplo", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        backButotn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(insertActivity.this, createActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(insertActivity.this, selectActivity.class);
                startActivity(intent);
            }
        });
    }



}
