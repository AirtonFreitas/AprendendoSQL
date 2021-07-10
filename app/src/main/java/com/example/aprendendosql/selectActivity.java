package com.example.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.aprendendosql.R.*;

public class selectActivity extends AppCompatActivity {

    private Button backButotn, nextButton, botao;
    private EditText query;
    private ListView lst_teste;
    private TextView testeText;

    SimpleCursorAdapter AdapterLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_select);
        backButotn = findViewById(id.buttonIDback);
        nextButton = findViewById(id.buttonIDnext);
        query = findViewById(id.queryID);
        botao = findViewById(id.button);
        testeText = findViewById(id.testTextID);


        lst_teste = findViewById(id.listID);

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

                        //String text = cursor.getString(1);

                        while(cursor.moveToNext()){

                            String dadoRetornado =
                                    //cursor.getString(cursor.getColumnCount(1));

                                    cursor.getColumnName(0) + "  " +
                                    cursor.getString(0) + "  " +
                                    cursor.getColumnName(1) + "  " +
                                    cursor.getString(1) + "  " +
                                    cursor.getColumnName(2) + "  " +
                                    cursor.getString(2)
                                    ;
                            testeText.setText(dadoRetornado);
                            Toast.makeText(getApplicationContext(), "Comando enviado com sucesso!", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
                            //testeText.setText(cursor.toString());

                            bancoDados.close();
                        }
                        //bancoDados.execSQL(querytext);
                    } catch (Exception e) {
                        e.printStackTrace();
                        //Log.e("Erro ao Criar Tabela",e.toString());
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
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