package com.airtonsiq.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class dropActivity extends AppCompatActivity {

    private Button backButton, nextButton, botao;
    private EditText query;
    private ImageView imagemTabela;
    private ImageView colar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop);

        backButton = findViewById(R.id.buttonIDback);
        nextButton = findViewById(R.id.buttonIDnext);
        query = findViewById(R.id.queryID);
        botao = findViewById(R.id.btn);
        imagemTabela = findViewById(R.id.imageView1);
        colar = findViewById(R.id.imageView6);

        colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("DROP TABLE CLIENTE;");
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
                        Toast.makeText(getApplicationContext(), "Tabela apagada com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                        imagemTabela.setImageResource(R.drawable.lixeira);

                        Handler handle = new Handler();
                        handle.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imagemTabela.setImageResource(R.drawable.tabela_drop2);
                            }
                        },2000 );

                    }catch(Exception e){
                        e.printStackTrace();
                        //Log.e("Erro ao Criar Tabela",e.toString());
                        //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Erro de Sintaxe! Verifique se já apagou a tabela ou use a opção Colar Exemplo", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dropActivity.this, deleteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dropActivity.this, congratulationsActivity.class);
                startActivity(intent);
            }
        });
    }
}