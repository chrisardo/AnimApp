package co.eduardo.animapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/*import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;*/

/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;*/

public class InforApp extends AppCompatActivity {
    //private AdView mAdView;

    private final String APP_ID = "appf11c05d40c61433b8d";
    private final String INTERSTITIAL_ZONE_ID  ="vz75776d19c7bc46b791";
    /*private AdColonyInterstitial adColonyInterstitiall;
    private AdColonyAdOptions adOptions;*/
    ImageButton btatras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_app);
        /*AdColony.configure(InforApp.this, APP_ID, INTERSTITIAL_ZONE_ID);
        AdColonyInterstitialListener listener = new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
                btatras =findViewById(R.id.btnIcoAtras);
                btatras.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adColonyInterstitiall = adColonyInterstitial;
                        adColonyInterstitiall.show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        };
        AdColony.requestInterstitial(INTERSTITIAL_ZONE_ID, listener, adOptions);
        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewinfo);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
    }
    public void atras(View view){
        Intent i = new Intent(getApplicationContext(), OpcionesActivity.class);
        /*i.putExtra("jugador", nombre_jugador);
        i.putExtra("genero", genero_jugador);*/
        startActivity(i);
        finish();
    }
}