package co.eduardo.animapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class AdivinarAnimal extends AppCompatActivity implements View.OnClickListener{
    ImageView imagen, iv_vidas;
    TextView lblcuenta, tv_score,tv_nombre,tvnivel;
    private final int TIEMPO_ESPERA = 4000;
    int INTENTOS = 3;
    private Button btn1, btn2, btn3, btn4;
    AlertDialog.Builder dialog; //Mensaje emergente
    int img_jugador, score;
    String nombre_jugador, genero_jugador, string_score;
    private MediaPlayer mp, mp_great, mp_bad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinar_animal);
        tv_nombre = (TextView)findViewById(R.id.textView_nombre);
        nombre_jugador = getIntent().getStringExtra("jugador");
        genero_jugador = getIntent().getStringExtra("genero");
        img_jugador = getIntent().getIntExtra("img",0);
        tv_nombre.setText("Jugador: " + nombre_jugador);
        iniciarComponentes();
        new MiTarea().execute();
        AnimalDB.cargarDatos(getApplicationContext());
        mp = MediaPlayer.create(this, R.raw.cuentaregresiva);
        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);
    }
    private void iniciarComponentes() {
        imagen = (ImageView) findViewById(R.id.miimagen);
        //lblintentos = (TextView) findViewById(R.id.lblintentos);
        lblcuenta = (TextView) findViewById(R.id.lblcuenta);
        tvnivel = (TextView)findViewById(R.id.textnivel);
        tv_score = (TextView)findViewById(R.id.textView_score);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        iv_vidas = (ImageView)findViewById(R.id.imageView_vidas);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Button boton = (Button) v;
        String nombreAnimal = boton.getText().toString().toLowerCase();
        if (AnimalDB.isAnimal(nombreAnimal)) {
            setPokemon(AnimalDB.NUMEROGENERADO);
            AnimalDB.setAdivinado(AnimalDB.NUMEROGENERADO, true);
            AnimalDB.ADIVINADOS++;
            score++;
            habilitarBotones(false);
            boton.setVisibility(View.VISIBLE);
            boton.setClickable(false);
            mp.start();
            esperar();
        } else {
            //AnimalDB.DisminuirIntentos();
            mp_bad.start();
            INTENTOS--;
            switch (INTENTOS){
                case 3:
                    iv_vidas.setImageResource(R.drawable.tresvidas);
                    break;
                case 2:
                    Toast.makeText(this, "Te quedan 2 vidas", Toast.LENGTH_LONG).show();
                    iv_vidas.setImageResource(R.drawable.dosvidas);
                    break;
                case 1:
                    Toast.makeText(this, "Te queda 1 vida", Toast.LENGTH_LONG).show();
                    iv_vidas.setImageResource(R.drawable.unavida);
                    break;
                case 0:
                    iv_vidas.setImageResource(R.drawable.gameover);
                    Intent intent = new Intent(getApplicationContext(), ResultadoJuegoActivity.class);
                    string_score = String.valueOf(score);
                    intent.putExtra("jugador", nombre_jugador);
                    intent.putExtra("nivel", tvnivel.getText().toString());
                    intent.putExtra("score", string_score);
                    intent.putExtra("GoP", "Has perdido!! :(");
                    startActivity(intent);
                    break;
            }
            //lblintentos.setText(String.valueOf(AnimalDB.INTENTOS));
            v.setVisibility(View.INVISIBLE);
        }
    }
    public void esperar() {
        new CountDownTimer(TIEMPO_ESPERA, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                lblcuenta.setText("Generando en " + (millisUntilFinished / 1000));
            }
            @Override
            public void onFinish() {
                lblcuenta.setText("");
                mp_great.start();
                tv_score.setText("Aciertos: " + score);
                if (!AnimalDB.isWin()) {
                    new MiTarea().execute();
                } else {
                    Intent i = new Intent(getApplicationContext(), ResultadoJuegoActivity.class);
                    string_score = String.valueOf(score);
                    i.putExtra("jugador", nombre_jugador);
                    i.putExtra("nivel", tvnivel.getText().toString());
                    i.putExtra("score", string_score);
                    i.putExtra("GoP", "FELICITACIONES!! Ganaste :)");
                    startActivity(i);
                    iniciarComponentes();
                    AnimalDB.cargarDatos(getApplicationContext());
                    new MiTarea().execute();
                    habilitarBotones(false);
                    BaseDeDatos();
                    //finish();
                    //dialogo.cancel();
                }
            }
        }.start();
    }
    private class MiTarea extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private int numero = 0;
        private int totalgenerados = 4;
        private int numerosrestantes = totalgenerados - 1;
        private int contador;
        private int permitidos = 0;
        private int valorgenerado = -1;
        ArrayList<Integer> numeros = new ArrayList<>();
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AdivinarAnimal.this);
            dialog.setMessage("Generando ...");
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            do {
                numero = ((int) (Math.random() * AnimalDB.getTamaño()));
                if (!AnimalDB.isAdivinado(numero) && valorgenerado <= 0) {
                    valorgenerado = numero;
                    contador++;
                    numeros.add(numero);
                } else if (!numeros.contains(numero) && permitidos < numerosrestantes) {
                    numeros.add(numero);
                    contador++;
                    permitidos++;
                }
            } while (contador < totalgenerados);
            Collections.shuffle(numeros);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            AnimalDB.NUMEROGENERADO = valorgenerado;
            setSombra(valorgenerado);
            habilitarBotones(true);
            btn1.setText(AnimalDB.getNombre(numeros.get(0)));
            btn2.setText(AnimalDB.getNombre(numeros.get(1)));
            btn3.setText(AnimalDB.getNombre(numeros.get(2)));
            btn4.setText(AnimalDB.getNombre(numeros.get(3)));
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            super.onPostExecute(aVoid);
        }
    }
    public void habilitarBotones(boolean valor) {
        if (valor) {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);
            btn1.setClickable(true);
            btn2.setClickable(true);
            btn3.setClickable(true);
            btn4.setClickable(true);
        } else {
            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
            btn4.setVisibility(View.INVISIBLE);
        }
    }
    public void setSombra(int id) {
        int resId = getResources().getIdentifier(AnimalDB.getSombra(id), "drawable", getPackageName());
        imagen.setImageResource(resId);
    }
    public void setPokemon(int id) {
        int resId = getResources().getIdentifier(AnimalDB.getNombre(id), "drawable", getPackageName());
        imagen.setImageResource(resId);
    }
    public void BaseDeDatos(){
        int jugador=PreferenciasJuego.jugadorId;
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(getApplicationContext(),Utilidades.NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID_JUGADOR,jugador);
        values.put(Utilidades.CAMPO_PUNTOS,score);
        values.put(Utilidades.CAMPO_NIVEL,tvnivel.getText().toString());
        Long idResultante=db.insert(Utilidades.TABLA_PUNTAJE,Utilidades.CAMPO_ID_JUGADOR,values);
        if(idResultante!=-1){
            //Toast.makeText(actividad,"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"El puntaje a sido Registrado con Exito! "+idResultante,Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogSalida);
            builder.setMessage("¿Desea salir del juego?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if(score!=0){
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                iniciarComponentes();
                                AnimalDB.cargarDatos(getApplicationContext());
                                new MiTarea().execute();
                                habilitarBotones(false);
                                BaseDeDatos();
                                finish();
                            }else {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                iniciarComponentes();
                                AnimalDB.cargarDatos(getApplicationContext());
                                new MiTarea().execute();
                                habilitarBotones(false);
                                //BaseDeDatos();
                                finish();
                            }
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
