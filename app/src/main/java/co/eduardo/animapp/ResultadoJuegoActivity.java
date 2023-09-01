package co.eduardo.animapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;*/
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResultadoJuegoActivity extends AppCompatActivity {
    TextView txnombreJugador, txnivel, txtPunto, txGanoPer;
    ImageView imAvatar;
    String nombre_jugador, GanoPer, string_score, niveljuego, string_vidas;
    int img_jugador,score;
    FloatingActionButton irInicio;
    /*private AdView mAdView;
    private InterstitialAd mInterstitialAd1;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_juego);
        txnombreJugador=(TextView)findViewById(R.id.txNombreJugador);
        txnivel=(TextView)findViewById(R.id.txNivel);
        txtPunto=(TextView)findViewById(R.id.txtPuntaje);
        imAvatar=(ImageView)findViewById(R.id.avaImage);
        txGanoPer=(TextView)findViewById(R.id.txtGanoPer);
        nombre_jugador = getIntent().getStringExtra("jugador");
        string_score = getIntent().getStringExtra("score");
        niveljuego = getIntent().getStringExtra("nivel");
        GanoPer = getIntent().getStringExtra("GoP");
        score = Integer.parseInt(string_score);
        txGanoPer.setText(""+GanoPer);
        txnombreJugador.setText(""+nombre_jugador);
        txnivel.setText(""+niveljuego);
        txtPunto.setText(""+string_score);
        imAvatar.setImageResource(Utilidades.listaAvatars.get(PreferenciasJuego.avatarId-1).getAvatarId());
        irInicio=(FloatingActionButton)findViewById(R.id.irInicio);
        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewresul);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
       // mAdView.loadAd();
        mInterstitialAd1 = new InterstitialAd(this);
        mInterstitialAd1.setAdUnitId("ca-app-pub-8302868983775944/6711720149");
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());*/
        irInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (mInterstitialAd1.isLoaded()) {
                    mInterstitialAd1.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }*/
                BaseDeDatos();
            }
        });
    }
    public void BaseDeDatos(){
        int jugador=PreferenciasJuego.jugadorId;
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(getApplicationContext(),Utilidades.NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID_JUGADOR,jugador);
        values.put(Utilidades.CAMPO_PUNTOS,score);
        values.put(Utilidades.CAMPO_NIVEL,niveljuego);
        Long idResultante=db.insert(Utilidades.TABLA_PUNTAJE,Utilidades.CAMPO_ID_JUGADOR,values);
        if(idResultante!=-1){
            Intent irInici= new Intent(ResultadoJuegoActivity.this, MainActivity.class);
            startActivity(irInici);
            //Toast.makeText(actividad,"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"El puntaje a sido Registrado con Exito! "+idResultante,Toast.LENGTH_LONG).show();
        }
        db.close();
    }
}