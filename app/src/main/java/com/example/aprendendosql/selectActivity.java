package com.example.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.aprendendosql.R.*;

public class selectActivity extends AppCompatActivity {

    private Button backButotn, nextButton, botao;
    private EditText query;
    private TextView testeText;
    private ImageView colar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_select);
        backButotn = findViewById(id.buttonIDback);
        nextButton = findViewById(id.buttonIDnext);
        query = findViewById(id.queryID);
        botao = findViewById(id.button);
        testeText = findViewById(id.testTextID);
        colar = findViewById(R.id.imageView6);

        colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("SELECT * FROM CLIENTE");
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (query.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Digite a query, por favor.", Toast.LENGTH_LONG).show();
                } else {
                    String querytext = query.getText().toString();
                    try {
                        SQLiteDatabase bancoDados = openOrCreateDatabase("NovoBanco", MODE_PRIVATE, null);

                        Cursor cursor = bancoDados.rawQuery(querytext,null);

                        String resultado = "";

                        while(cursor.moveToNext()){
                            String dadoRetornado = "";
                            int n;
                            for ( n=0; n< cursor.getColumnCount(); n++) {
                                dadoRetornado +=
                                        cursor.getColumnName(n) + "  " +
                                                cursor.getString(n) + "  ";
                            }

                            resultado += dadoRetornado + "\n";
                            Toast.makeText(getApplicationContext(), "Comando enviado com sucesso!", Toast.LENGTH_SHORT).show();

                            bancoDados.close();
                        }

                        testeText.setText(resultado);
                        //bancoDados.execSQL(querytext);
                    } catch (Exception e) {
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
                Intent intent = new Intent(selectActivity.this, insertActivity.class);
                startActivity(intent);
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectActivity.this, updateActivity.class);
                startActivity(intent);
            }
        });
    }

}