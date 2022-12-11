package com.airtonsiq.aprendendosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import static android.os.Environment.*;


public class congratulationsActivity extends AppCompatActivity {

    private Button botaoCert, backButton, btnRate;
    private EditText nome;
    private TextView nomeCert, compartilhar;
    private ImageView imagemCertificado, shareImage;
    private Toolbar toolbar;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        imagemCertificado = findViewById(R.id.imageView9);
        shareImage = findViewById(R.id.shareId);
        botaoCert = findViewById(R.id.buttonID);
        nome = findViewById(R.id.nomeID);
        nomeCert = findViewById(R.id.textView4);
        backButton = findViewById(R.id.buttonIDback);
        imagemCertificado = findViewById(R.id.imageView9);
        compartilhar = findViewById(R.id.textView35);
        toolbar = findViewById(R.id.toolbar);
        mAdView = findViewById(R.id.adView);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        imagemCertificado.setVisibility(View.INVISIBLE);
        shareImage.setVisibility(View.INVISIBLE);
        compartilhar.setVisibility(View.INVISIBLE);
        btnRate = findViewById(R.id.btn_rate);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        botaoCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifica se tem permissao para gravar na pasta
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(congratulationsActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {

                    if (nome.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Preencha o Nome Completo por favor.", Toast.LENGTH_SHORT).show();
                    } else {
                        criarPasta();
                        enabledAdsInterstitial();
                    }

                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(congratulationsActivity.this, dropActivity.class);
                startActivity(intent);
                finish();
            }
        });

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCert();
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.airtonsiq.aprendendosql")));
            }
        });
    }

    private void shareCert() {
        if (nomeCert.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Preencha o Nome Completo e clique em Gerar Certificado", Toast.LENGTH_SHORT).show();
        } else {
            String filepath = getExternalStorageDirectory() + "/AprendendoSQL/CertificadoSQLBasico.pdf";
            Uri arquivo = Uri.parse(filepath);
            Intent _intent = new Intent();
            _intent.setAction(Intent.ACTION_SEND);
            _intent.putExtra(Intent.EXTRA_STREAM, arquivo);
            _intent.setType("*/*");

            startActivity(Intent.createChooser(_intent, "Compartilhar Certificado"));
        }
    }


    //cria a pasta
    private void criarPasta() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/AprendendoSQL");
        try {
            if (folder.exists()) {
                gravarPDF();
            } else {
                folder.mkdir();
                gravarPDF();
            }
        } catch (Exception g) {
            Toast.makeText(getApplicationContext(), "erro" + g, Toast.LENGTH_LONG).show();
        }

    }

    //grava o pdf
    private void gravarPDF() {
        //pega o nome digitado
        String nomeStr = nome.getText().toString();
        nomeCert.setText(nomeStr + ", ");

        PdfDocument documentoPDF = new PdfDocument();
        PdfDocument.PageInfo detalhesPagina =
                new PdfDocument.PageInfo.Builder(2150, 1350, 1).create();
        PdfDocument.Page novaPagina = documentoPDF.startPage(detalhesPagina);
        Canvas canvas = novaPagina.getCanvas();
        Paint corDoText = new Paint();
        corDoText.setColor(Color.GRAY);
        corDoText.setTextSize(60);
        Bitmap backCertificado;
        backCertificado = BitmapFactory.decodeResource(getResources(), R.drawable.certificado);
        canvas.drawBitmap(backCertificado, 0, 0, null);
        canvas.drawText(nomeStr, 545, 640, corDoText);

        documentoPDF.finishPage(novaPagina);

        String targetPdf = Environment.getExternalStorageDirectory() + "/AprendendoSQL/CertificadoSQLBasico.pdf";

        File filePath = new File(targetPdf);
        try {
            documentoPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(getApplicationContext(), "Gerado com Sucesso..", Toast.LENGTH_SHORT).show();
        } catch (Exception f) {
            Toast.makeText(getApplicationContext(), "Houve falha ao gerar o PDF. Detalhamento:   " + f, Toast.LENGTH_LONG).show();
        }
        imagemCertificado.setVisibility(View.VISIBLE);
        shareImage.setVisibility(View.VISIBLE);
        compartilhar.setVisibility(View.VISIBLE);
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
            mInterstitialAd.show(congratulationsActivity.this);
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
