package co.eduardo.animapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;*/


import co.eduardo.animapp.interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments, InicioFragment.OnFragmentInteractionListener,
        RegistroJugadorFragment.OnFragmentInteractionListener,GestionJugadorFragment.OnFragmentInteractionListener,
        RankingFragment.OnFragmentInteractionListener{
    Fragment fragmentInicio,registroJugadorFragment,gestionJugadorFragment, rankingFragment, conteregistrofragment ;
    AlertDialog.Builder dialog; //Mensaje emergente
    CardView cardFacil,cardMedio, cardRegistro, cardSeleccion;
    ImageView btnSalir;
    //private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Publicidad
        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8302868983775944/9042370013");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());*/
        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());
        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaJugadores(this);
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,Utilidades.NOMBRE_BD,null,1);
        fragmentInicio =new InicioFragment();
        registroJugadorFragment=new RegistroJugadorFragment();
        gestionJugadorFragment=new GestionJugadorFragment();
        rankingFragment=new RankingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, fragmentInicio).commit();
    }
    @Override
    public void mostrarMenu() {
        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaJugadores(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }
    public void onClick(View view) {
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    @Override
    public void iniciarJuego() {
        if (Utilidades.listaJugadores!=null && Utilidades.listaJugadores.size()>0)
        {
            crearDialogoGestionUsuario();
        }else{
            Toast.makeText(getApplicationContext(),"Debe registrar un Jugador",Toast.LENGTH_SHORT).show();
        }
    }
    private void  crearDialogoGestionUsuario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogelegirjuego, null);
        builder.setView(v);
        btnSalir=v.findViewById(R.id.btnSalir);
        cardFacil=v.findViewById(R.id.cardFacil);
        cardMedio=v.findViewById(R.id.cardMedio);
        final  AlertDialog dialog=builder.create();
        eventosBotones();
        dialog.show();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void eventosBotones() {
        cardFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar el anuncio
                /*if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }*/
                dialogoInstruccionesNivelFacil();
            }
        });
        cardMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar el anuncio
                /*if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }*/
                dialogoInstruccionesNivelMedio();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
    public void dialogoInstruccionesNivelFacil(){
        dialog = new AlertDialog.Builder(MainActivity.this, R.style.DialogSalida);
        dialog.setTitle("OPERACIONES MATEMATICAS: ");
        dialog.setMessage("Se presentarán 3 tipos de operaciones matemáticas: suma, resta y multiplicación en el juego, lo cual tendrás 3 vidas para resolverlos\n");
        dialog.setCancelable(false);
        dialog.setPositiveButton("JUGAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity_Nivel1.class);
                intent.putExtra("jugador", PreferenciasJuego.nickName);
                intent.putExtra("genero", PreferenciasJuego.NICKNAME);
                intent.putExtra("img",PreferenciasJuego.avatarId);
                startActivity(intent);
                finish();
            }
        });

        dialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void dialogoInstruccionesNivelMedio(){
        dialog = new AlertDialog.Builder(MainActivity.this, R.style.DialogSalida);
        dialog.setTitle("ADIVINA EL ANIMAL: ");
        dialog.setMessage("Se presentarán 4 botones con diferentes textos en forma aleatoria,lo cual tendrás 3 vida para adivinar todos los sombras de los animales");
        dialog.setCancelable(false);
        dialog.setPositiveButton("JUGAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Intent intent = new Intent(getApplicationContext(), AdivinarAnimal.class);
                intent.putExtra("jugador", PreferenciasJuego.nickName);
                intent.putExtra("genero", PreferenciasJuego.NICKNAME);
                intent.putExtra("img", PreferenciasJuego.avatarId);
                startActivity(intent);
                finish();
            }
        });
        dialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //mp.stop();
                dialog.cancel();
            }
        });
        dialog.show();
    }
    @Override
    public void llamarAjustes() {
        //   Toast.makeText(getApplicationContext(),"Ajustes desde la actividad",Toast.LENGTH_SHORT).show();
        Intent inte= new Intent(this, ListaActivity.class);
        startActivity(inte);
    }
    @Override
    public void consultarRanking() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,rankingFragment).commit();
    }
    @Override
    public void consultarInstrucciones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogSalida);
        builder.setMessage("¿Desea salir de la aplicación de juego AnimApp?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
    @Override
    public void gestionarUsuario() {
        createSimpleDialog();
    }
    @Override
    public void consultarInformacion() {
        //   Toast.makeText(getApplicationContext(),"Informacion desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, OpcionesActivity.class);
        startActivity(intent);
    }
    private void createSimpleDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.gestiojugador, null);
        builder.setView(v);
        btnSalir=v.findViewById(R.id.btnSalir);
        cardRegistro=v.findViewById(R.id.cardRegis);
        cardSeleccion=v.findViewById(R.id.cardSeleccion);
        final  AlertDialog dialogGes=builder.create();
        cardRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,registroJugadorFragment).commit();
                //Intent registro= new Intent(getApplicationContext(), RegistroJugadorActivity.class);
                //startActivity(registro);
                dialogGes.cancel();
            }
        });
        cardSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utilidades.listaJugadores!=null && Utilidades.listaJugadores.size()>0)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,gestionJugadorFragment).commit();
                    dialogGes.cancel();
                }else{
                    Toast.makeText(getApplicationContext(),"Debe registrar un Jugador",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialogGes.show();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGes.dismiss();
            }
        });
    }
    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogSalida);
            builder.setMessage("¿Desea salir de la aplicación de juego AnimApp?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}