package co.eduardo.animapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.appnext.ads.interstitial.Interstitial;
import com.appnext.base.Appnext;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

public class OpcionesActivity extends AppCompatActivity {
    private LinearLayout linstru, liindica, Lacerca;
    //private BannerView bannerop_appnext;
    Interstitial interstitialop_appnext;
    int desbloquear1, desbloquear2;
    ImageButton iratras;
    private View decorView;//Para quitar el estatus bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Appnext.init(this);
        // NOTE always use test ads during development and testing
        StartAppSDK.setTestAdsEnabled(BuildConfig.DEBUG);
        StartAppSDK.init(this, "202504993", true);
        //bannerop_appnext = (BannerView) findViewById(R.id.banerop);
        interstitialop_appnext = new Interstitial(this, "da374833-5714-400f-a5c4-311747d5b1f0");
        interstitialop_appnext.loadAd();
        interstitialop_appnext.setMute(true);
        interstitialop_appnext.setBackButtonCanClose(true);

        /*BannerView banerop = new BannerView(this);
        banerop.setPlacementId("da374833-5714-400f-a5c4-311747d5b1f0");
        bannerop_appnext.loadAd(new BannerAdRequest());
        banerop.setBannerSize(BannerSize.BANNER);*/

        desbloquear1 = getIntent().getIntExtra("desbloquearNivel1",0);
        desbloquear2 = getIntent().getIntExtra("desbloquearNivel2",0);
        Lacerca =(LinearLayout) findViewById(R.id.LAcerca);
        Lacerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acerca = new Intent(getApplicationContext(), InforApp.class);
                /*acerca.putExtra("desbloquearNivel1", desbloquear1);
                acerca.putExtra("desbloquearNivel2", desbloquear2);*/
                startActivity(acerca);
                //interstitialop_appnext.showAd();
                StartAppAd.showAd(OpcionesActivity.this);
            }
        });
        iratras =(ImageButton)findViewById(R.id.btnIcoAtras);
        iratras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adColonyInterstitiall = adColonyInterstitial;
                //adColonyInterstitiall.show();
                Intent ia = new Intent(getApplicationContext(), MainActivity.class);
                ia.putExtra("desbloquearNivel1", desbloquear1);
                ia.putExtra("desbloquearNivel2", desbloquear2);
                startActivity(ia);
                interstitialop_appnext.showAd();
                //StartAppAd.showAd(OpcionesActivity.this);
                finish();
            }
        });
    }
    public void instruccion(View view){
        Intent instruccion = new Intent(OpcionesActivity.this, ContenedorInstruccionesActivity.class);
        startActivity(instruccion);
    }
    public void compartir(View view){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"Diviértete probando tu retentiva con este juego de aventura. Descarga GalacticShip desde la PlayStore, es gratis: " + "https://play.google.com/store/apps/details?id=co.lozano.galacticship");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, null));
    }
    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent mainac = new Intent(getApplicationContext(), MainActivity.class);
            /*mainac.putExtra("desbloquearNivel1", desbloquear1);
            mainac.putExtra("desbloquearNivel2", desbloquear2);*/
            startActivity(mainac);
        }
        return super.onKeyDown(keyCode, event);
    }
}