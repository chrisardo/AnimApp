package co.eduardo.animapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=preferences.edit();
                int bandera=Integer.parseInt(preferences.getString("bandera","0"));

                if (bandera==1){
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    //intent.putExtra("jugador", nombre_jugador);
                    //intent.putExtra("genero", genero_jugador);
                    startActivity(intent);
                }else{
                    editor.putString("bandera","1");
                    editor.commit();

                    /*Intent intent2=new Intent(SplashActivity.this, InicioActivity.class);
                    startActivity(intent2);*/

                    Intent intent=new Intent(SplashActivity.this, RegistroPrincipal.class);
                    startActivity(intent);
                }

                finish();
            }
        },2000);
    }
}