package com.airtonsiq.aprendendosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class createActivity extends AppCompatActivity {

    private Button nextButton;
    private Button botao;
    private EditText query;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Button backButotn = findViewById(R.id.buttonIDback);
        nextButton = findViewById(R.id.buttonIDnext);
        query = findViewById(R.id.queryID);
        botao = findViewById(R.id.btn);
        ImageView colar = findViewById(R.id.imageView6);
        TextView cliqueID = findViewById(R.id.cliqueID);
        Toolbar toolbar = findViewById(R.id.toolbar);
        AdView mAdView = findViewById(R.id.adView);
        ImageView imageFlutter = findViewById(R.id.imageViewFlutterID);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showFlutter();
            }
        }, 5000);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        cliqueID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createActivity.this, tipodeDadosSQL.class);
                startActivity(intent);

            }
        });

        colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("CREATE TABLE CLIENTE (CODIGO INT, NOME CHAR, CPF CHAR);");
            }
        });

        imageFlutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFlutter();
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
                        bancoDados.execSQL(querytext);
                        Toast.makeText(getApplicationContext(), "Tabela criada com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();
                        enabledAdsInterstitial();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro de Sintaxe! Verifique se a tabela já foi criada antes ou use o botão Colar Exemplo.", Toast.LENGTH_LONG).show();
                        enabledAdsInterstitial();
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

    public void showFlutter() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Você conhece o Flutter?");
        alertDialog.setIcon(android.R.drawable.stat_sys_download);
        alertDialog.setMessage("Aprenda agora sobre desenvolvimento de apps!");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Quero aprender desenvolvimento mobile", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goFlutter();
            }
        });
        alertDialog.setNegativeButton("Não quero", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void goFlutter() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.airtonsiq.aprendendoflutter.aprendendo_flutter")));
    }

    private void enabledAdsInterstitial() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequesti = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3721429763641925/6877262672", adRequesti,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
        if (mInterstitialAd != null) {
            mInterstitialAd.show(createActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbarInicio:
                homePage();
                break;
            case R.id.toolbarFlutter:
                Flutter();
                break;
            case R.id.toolbarDonate:
                Donate();
                break;
            case R.id.toolbarRate:
                Rate();
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


    public void Donate() {
        Intent intent = new Intent(this, activity_donate.class);
        startActivity(intent);
    }

    public void Flutter() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.airtonsiq.aprendendoflutter.aprendendo_flutter")));
    }

    public void Rate() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.airtonsiq.aprendendosql")));
    }
}