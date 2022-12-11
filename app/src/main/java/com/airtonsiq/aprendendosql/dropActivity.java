package com.airtonsiq.aprendendosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class dropActivity extends AppCompatActivity {

    private Button backButton, nextButton, botao;
    private EditText query;
    private ImageView imagemTabela;
    private ImageView colar;
    private Toolbar toolbar;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;


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
        toolbar = findViewById(R.id.toolbar);
        mAdView = findViewById(R.id.adView);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("DROP TABLE CLIENTE;");
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
                        Toast.makeText(getApplicationContext(), "Tabela apagada com sucesso!", Toast.LENGTH_LONG).show();
                        bancoDados.close();

                        imagemTabela.setImageResource(R.drawable.lixeira);

                        Handler handle = new Handler();
                        handle.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imagemTabela.setImageResource(R.drawable.tabela_drop2);
                                enabledAdsInterstitial();
                            }
                        }, 2000);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro de Sintaxe! Verifique se já apagou a tabela ou use a opção Colar Exemplo", Toast.LENGTH_LONG).show();
                        enabledAdsInterstitial();
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
            mInterstitialAd.show(dropActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

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