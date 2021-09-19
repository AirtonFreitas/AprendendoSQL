package com.airtonsiq.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class alterActivity extends AppCompatActivity {

    private Button backButotn, nextButton;
    private Button btnAdicionando;
    private EditText queryAdicionando;
    private ImageView colarExemploAdicionando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        backButotn = findViewById(R.id.buttonIDback);
        nextButton = findViewById(R.id.buttonIDnext);
        btnAdicionando = findViewById(R.id.btn_adiciona);

        queryAdicionando = findViewById(R.id.queryIDadicionando);
        colarExemploAdicionando = findViewById(R.id.imageView8);

                colarExemploAdicionando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryAdicionando.setText("ALTER TABLE CLIENTE ADD TELEFONE INT;");
            }
        });

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
                            Toast.makeText(getApplicationContext(), "Alteração enviada com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                    }catch(Exception e){
                        //e.printStackTrace();
                        //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Erro de Sintaxe!! Revise, ou use a opção Colar Exemplo", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        backButotn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alterActivity.this, updateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alterActivity.this, deleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
