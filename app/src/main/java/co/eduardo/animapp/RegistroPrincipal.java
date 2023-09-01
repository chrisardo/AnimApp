package co.eduardo.animapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistroPrincipal extends AppCompatActivity {
    RecyclerView recyclerAvatars;
    ImageView imagenAvatar;
    FloatingActionButton fabRegistro;
    EditText campoNick;
    RadioButton radioM,radioF;
    //int avatarId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_principal);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());
        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaJugadores(this);
        recyclerAvatars =(RecyclerView)findViewById(R.id.recyclerAvatarsId);
        imagenAvatar=(ImageView)findViewById(R.id.imageView_Personaje);
        fabRegistro=(FloatingActionButton) findViewById(R.id.idFabRegistro);
        campoNick=(EditText) findViewById(R.id.campoNickName);
        radioM=(RadioButton)findViewById(R.id.radioM);
        radioF=(RadioButton) findViewById(R.id.radioF);
        recyclerAvatars.setLayoutManager(new GridLayoutManager(this,4));
        recyclerAvatars.setHasFixedSize(true);
        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarJugador();
            }
        });
        //se asigna la lista de avatars por defecto
        final AdaptadorAvatar miAdaptador=new AdaptadorAvatar(Utilidades.listaAvatars);
        recyclerAvatars.setAdapter(miAdaptador);
    }
    public void  ayuda(View view){
        createSimpleDialog().show();
    }
    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroPrincipal.this,R.style.DialogSalida);

        builder.setTitle("Ayuda")
                .setMessage("Ingrese el nombre del jugador, Genero y seleccione la imagen del jugador de la lista de imagénes disponibles, posteriormente presione el botón para confirmar el registro.")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

        return builder.create();
    }
    private void registrarJugador() {
        String genero="";
        if (radioM.isChecked()==true){
            genero="M";
        }else if(radioF.isChecked()==true){
            genero="F";
        }else{
            genero="No seleccionado";
        }
        if (!genero.equals("No seleccionado") && campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals("")){
            String nickName=campoNick.getText().toString();
            int avatarId=Utilidades.avatarSeleccion.getId();
            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(getApplicationContext(),Utilidades.NOMBRE_BD,null,1);
            SQLiteDatabase db=conn.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE,nickName);
            values.put(Utilidades.CAMPO_GENERO,genero);
            values.put(Utilidades.CAMPO_AVATAR,avatarId);
            Long idResultante=db.insert(Utilidades.TABLA_JUGADOR,Utilidades.CAMPO_ID,values);
            if(idResultante!=-1){
                PreferenciasJuego.jugadorId=Integer.parseInt(idResultante+"");
                //Toast.makeText(getApplicationContext(),"El Jugador a sido Registrado con Exito!",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Bienvenido!: "+nickName,Toast.LENGTH_SHORT).show();
                PreferenciasJuego.nickName=campoNick.getText().toString();
                PreferenciasJuego.avatarId=Utilidades.avatarSeleccion.getId();
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                PreferenciasJuego.asignarPreferenciasJugador(preferences,getApplicationContext());
                campoNick.setText("");
                //Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(i);
                mostrarMenu();
            }else
                Toast.makeText(getApplicationContext(),"No se pudo Registrar el Jugador! ",Toast.LENGTH_SHORT).show();
            db.close();
        }else{
            Toast.makeText(getApplicationContext(),"Verifique los datos de registro",Toast.LENGTH_SHORT).show();
        }
    }
    public void mostrarMenu() {
        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaJugadores(this);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}