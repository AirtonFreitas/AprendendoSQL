package com.airtonsiq.aprendendosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class activity_donate extends AppCompatActivity {

    private ImageView pix;
    private Toolbar toolbar;
    private Button btnRate, btnBack;
    private TextView linkLinkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        pix = findViewById(R.id.imageCopyPix);
        toolbar = findViewById(R.id.toolbar);
        btnRate = findViewById(R.id.btn_rate);
        btnBack = findViewById(R.id.btnDonateBackID);
        linkLinkedin = findViewById(R.id.linkedInLink);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", "6c1b2b17-5f39-494d-a683-c88e3792a1ff");
                clipboard.setPrimaryClip(clip);

                Toast.makeText(activity_donate.this, "Chave Pix copiada!", Toast.LENGTH_LONG).show();
            }
        });
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.iasoftwares.sharelist")));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_donate.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linkLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/airton-siqueira-85260b174")));

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Congrats() {
        Intent intent = new Intent(this, congratulationsActivity.class);
        startActivity(intent);
        finish();
    }

    public void Donate() {
        Intent intent = new Intent(this, activity_donate.class);
        startActivity(intent);
        finish();
    }
}