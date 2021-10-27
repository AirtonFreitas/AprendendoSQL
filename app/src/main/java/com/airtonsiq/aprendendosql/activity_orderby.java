package com.airtonsiq.aprendendosql;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class activity_orderby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderby);
        try {
            SQLiteDatabase bancoDados = openOrCreateDatabase("NovoBanco", MODE_PRIVATE, null);

            Cursor cursor = bancoDados.rawQuery(
                    "CREATE TABLE CLIENTE (CODIGO INT, NOME CHAR, CPF CHAR);" +
                            "INSERT INTO CLIENTE (CODIGO, NOME, CPF) " +
                            "VALUES " +
                            "(1, \"AIRTON S.\",\"154.854.846-21\")," +
                            "(2, \"JOAO C\",\"664.844.646-24\")," +
                            "(3, \"MARIA S.\",\"194.747.966-34\")," +
                            "(4, \"DANIEL\",\"154.854.654-21\")," +
                            "(5, \"PAULO C.\",\"664.844.981-24\");"


                    ,null);

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
            }
            bancoDados.close();
            Toast.makeText(getApplicationContext(), "Comando enviado com sucesso!", Toast.LENGTH_SHORT).show();
            //testeText.setText(resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
