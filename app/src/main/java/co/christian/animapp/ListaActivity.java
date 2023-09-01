package co.christian.animapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListaActivity extends AppCompatActivity {
    ArrayList<PersonajeVo> listaPersonajes;
    RecyclerView recyclerPersonajes;
    private MediaPlayer mpgato, mpperro, mpkoala, mpleon, mpoveja, mpjirafa, mppinguino, mpcaballo, mpmono, mpdelfin, mpcanguro, mpgallo, mpgallina, mpelefante, mppollo, mpvaca;
    AdaptadorPersonajes adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        toolbar = findViewById(R.id.toolboar);
        //toolbar.setTitleTextColor(View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        this.setSupportActionBar(toolbar);
        //this.getSupportActionBar().setTitle("");
        construirRecycler();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setMaxWidth(Integer.MIN_VALUE);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }
    private void llenarPersonajes(){
        listaPersonajes.add(new PersonajeVo("GATO",R.drawable.gato));
        listaPersonajes.add(new PersonajeVo("PERRO",R.drawable.perro));
        listaPersonajes.add(new PersonajeVo("KOALA", R.drawable.koala));
        listaPersonajes.add(new PersonajeVo("LEÓN",R.drawable.leon));
        listaPersonajes.add(new PersonajeVo("OVEJA", R.drawable.oveja));
        listaPersonajes.add(new PersonajeVo("JIRAFA", R.drawable.jirafa));
        listaPersonajes.add(new PersonajeVo("PINGUINO", R.drawable.pinguin));
        listaPersonajes.add(new PersonajeVo("CABALLO", R.drawable.caballo));
        listaPersonajes.add(new PersonajeVo("MONO", R.drawable.mono));
        listaPersonajes.add(new PersonajeVo("DELFIN", R.drawable.delfin));
        listaPersonajes.add(new PersonajeVo("CANGURO", R.drawable.canguro));
        listaPersonajes.add(new PersonajeVo("GALLO", R.drawable.gallo));
        listaPersonajes.add(new PersonajeVo("GALLINA", R.drawable.gallina));
        listaPersonajes.add(new PersonajeVo("ELEFANTE", R.drawable.elefante));
        listaPersonajes.add(new PersonajeVo("POLLO", R.drawable.pollo));
        listaPersonajes.add(new PersonajeVo("VACA", R.drawable.vaca));
    }
    private void construirRecycler() {
        listaPersonajes=new ArrayList<>();
        recyclerPersonajes= (RecyclerView)findViewById(R.id.RecyclerId);
        if (Utilidades.visualizacion==Utilidades.LIST){
            recyclerPersonajes.setLayoutManager(new LinearLayoutManager(this));
        }
        llenarPersonajes();
        adapter=new AdaptadorPersonajes(listaPersonajes);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Animal: "+listaPersonajes.get(recyclerPersonajes.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_LONG).show();
                switch (listaPersonajes.get(recyclerPersonajes.getChildAdapterPosition(view)).getNombre()){
                    case "GATO":  mpgato = MediaPlayer.create(getApplicationContext(), R.raw.gato);
                        mpgato.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "PERRO":  mpperro = MediaPlayer.create(getApplicationContext(), R.raw.perro);
                        //view.findViewById(R.id.idsonido).setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp);
                        mpperro.start();
                        if (mpgato != null){ mpgato.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "KOALA":  mpkoala = MediaPlayer.create(getApplicationContext(), R.raw.koala);
                        mpkoala.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "LEÓN": mpleon = MediaPlayer.create(getApplicationContext(), R.raw.leon);
                        mpleon.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "OVEJA": mpoveja = MediaPlayer.create(getApplicationContext(), R.raw.oveja);
                        mpoveja.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "JIRAFA": mpjirafa = MediaPlayer.create(getApplicationContext(), R.raw.jirafa);
                        mpjirafa.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "PINGUINO": mppinguino = MediaPlayer.create(getApplicationContext(), R.raw.pinguino);
                        mppinguino.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "CABALLO": mpcaballo = MediaPlayer.create(getApplicationContext(), R.raw.caballo);
                        mpcaballo.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "MONO": mpmono = MediaPlayer.create(getApplicationContext(), R.raw.mono);
                        mpmono.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        break;
                    case "DELFIN": mpdelfin = MediaPlayer.create(getApplicationContext(), R.raw.delfin);
                        mpdelfin.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        break;
                    case "CANGURO": mpcanguro = MediaPlayer.create(getApplicationContext(), R.raw.canguro);
                        mpcanguro.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        break;
                    case "GALLO": mpgallo = MediaPlayer.create(getApplicationContext(), R.raw.gallo);
                        mpgallo.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        break;
                    case "GALLINA": mpgallina = MediaPlayer.create(getApplicationContext(), R.raw.gallina);
                        mpgallina.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        break;
                    case "ELEFANTE": mpelefante = MediaPlayer.create(getApplicationContext(), R.raw.elefante);
                        mpelefante.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        break;
                    case "POLLO": mppollo = MediaPlayer.create(getApplicationContext(), R.raw.pollo);
                        mppollo.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        if (mpvaca != null){ mpvaca.release(); }
                        break;
                    case "VACA": mpvaca = MediaPlayer.create(getApplicationContext(), R.raw.vaca);
                        mpvaca.start();
                        if (mpperro != null){ mpperro.release(); }
                        if (mpgato != null){ mpgato.release(); }
                        if (mpkoala != null){ mpkoala.release(); }
                        if (mpleon != null){ mpleon.release(); }
                        if (mpoveja != null){ mpoveja.release(); }
                        if (mpjirafa != null){ mpjirafa.release(); }
                        if (mppinguino != null){ mppinguino.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        if (mpdelfin != null){ mpdelfin.release(); }
                        if (mpmono != null){ mpmono.release(); }
                        if (mpcanguro != null){ mpcanguro.release(); }
                        if (mpgallina != null){ mpgallina.release(); }
                        if (mpelefante != null){ mpelefante.release(); }
                        if (mppollo != null){ mppollo.release(); }
                        if (mpgallo != null){ mpgallo.release(); }
                        if (mpcaballo != null){ mpcaballo.release(); }
                        break;
                }
            }
        });
        recyclerPersonajes.setAdapter(adapter);
    }
    public void atras(View view){
        if (mpperro != null){ mpperro.release(); }
        if (mpgato != null){ mpgato.release(); }
        if (mpkoala != null){ mpkoala.release(); }
        if (mpleon != null){ mpleon.release(); }
        if (mpoveja != null){ mpoveja.release(); }
        if (mpjirafa != null){ mpjirafa.release(); }
        if (mppinguino != null){ mppinguino.release(); }
        if (mpcaballo != null){ mpcaballo.release(); }
        if (mpdelfin != null){ mpdelfin.release(); }
        if (mpmono != null){ mpmono.release(); }
        if (mpcanguro != null){ mpcanguro.release(); }
        if (mpgallina != null){ mpgallina.release(); }
        if (mpelefante != null){ mpelefante.release(); }
        if (mppollo != null){ mppollo.release(); }
        if (mpvaca != null){ mpvaca.release(); }
        if (mpgallo != null){ mpgallo.release(); }
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}