package co.eduardo.animapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity_Nivel5 extends AppCompatActivity {
    private TextView tv_nombre, tv_score, tvnivel;
    private ImageView iv_Auno, iv_Ados, iv_vidas, iv_signo;
    private EditText et_respuesta;
    private MediaPlayer mp, mp_great, mp_bad;
    AlertDialog.Builder dialog; //Mensaje emergente
    private final int TIEMPO_ESPERA = 4000;
    TextView lblcuenta;
    Button btnRespuesta;
    int img_jugador,score, numAleatorio_uno, numAleatorio_dos, resultado,resultadodiv, vidas = 3;
    String nombre_jugador, string_score, string_vidas, genero_jugador;
    String numero [] = {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve","diez","once","doce","trece","catorce","quince","dieciseis","diecisiete","dieciocho","diecinueve","veinte"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__nivel5);
        Toast.makeText(this, "Nivel 5 - Sumas, Restas, Multiplicación y División", Toast.LENGTH_LONG).show();
        tv_nombre = (TextView)findViewById(R.id.textView_nombre);
        tv_score = (TextView)findViewById(R.id.textView_score);
        btnRespuesta = (Button) findViewById(R.id.button2);
        tvnivel = (TextView)findViewById(R.id.textnivel);
        iv_vidas = (ImageView)findViewById(R.id.imageView_vidas);
        iv_Auno = (ImageView)findViewById(R.id.imageView_NumUno);
        iv_Ados = (ImageView)findViewById(R.id.imageView_NumDos);
        iv_signo = (ImageView)findViewById(R.id.imageView_signo);
        lblcuenta = (TextView)findViewById(R.id.lblcuenta);
        et_respuesta = (EditText)findViewById(R.id.editText_resultado);
        nombre_jugador = getIntent().getStringExtra("jugador");
        genero_jugador = getIntent().getStringExtra("genero");
        img_jugador = getIntent().getIntExtra("img",0);
        tv_nombre.setText("Participante: " + nombre_jugador);
        string_score = getIntent().getStringExtra("score");
        score = Integer.parseInt(string_score);
        tv_score.setText("Aciertos: " + score);
        string_vidas = getIntent().getStringExtra("vidas");
        vidas = Integer.parseInt(string_vidas);
        if(vidas == 3){
            iv_vidas.setImageResource(R.drawable.tresvidas);
        } if(vidas == 2){
            iv_vidas.setImageResource(R.drawable.dosvidas);
        } if(vidas == 1){
            iv_vidas.setImageResource(R.drawable.unavida);
        }
        mp = MediaPlayer.create(this, R.raw.cuentaregresiva);
        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);
        NumAleatorio();
    }
    public void Comparar(View view){
        String respuesta = et_respuesta.getText().toString();
        if(!respuesta.equals("")){
            int respuesta_jugador = Integer.parseInt(respuesta);
            if(resultado == respuesta_jugador){
                mp.start();
                esperar();
            } else {
                mp_bad.start();
                vidas--;
                switch (vidas){
                    case 3:
                        iv_vidas.setImageResource(R.drawable.tresvidas);
                        break;
                    case 2:
                        Toast.makeText(this, "Te quedan 2 vidas", Toast.LENGTH_SHORT).show();
                        iv_vidas.setImageResource(R.drawable.dosvidas);
                        break;
                    case 1:
                        Toast.makeText(this, "Te queda 1 vida", Toast.LENGTH_SHORT).show();
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

                et_respuesta.setText("");
            }
        } else {
            Toast.makeText(this, "Escribe tu respuesta", Toast.LENGTH_SHORT).show();
        }
    }
    public void esperar() {
        new CountDownTimer(TIEMPO_ESPERA, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (btnRespuesta.isClickable() == true){
                    btnRespuesta.setVisibility(View.INVISIBLE);
                    btnRespuesta.setClickable(false);
                }
                lblcuenta.setText("Generando en " + (millisUntilFinished / 1000));
            }
            @Override
            public void onFinish() {
                btnRespuesta.setVisibility(View.VISIBLE);
                btnRespuesta.setClickable(true);
                lblcuenta.setText("");
                mp_great.start();
                score++;
                tv_score.setText("Aciertos: " + score);
                et_respuesta.setText("");
                NumAleatorio();
            }
        }.start();
    }
    public void NumAleatorio(){
        if(score <= 52){//41
            numAleatorio_uno = (int) (Math.random() * 21);
            numAleatorio_dos = (int) (Math.random() * 21);
            if (numAleatorio_dos==0){
                NumAleatorio();
            }else {
                resultado = numAleatorio_uno / numAleatorio_dos;
                if(resultado > 0){
                    for (int i = 0; i < numero.length; i++){
                        int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());
                        if(numAleatorio_uno == i){
                            iv_Auno.setImageResource(id);
                        }if(numAleatorio_dos == i){
                            iv_Ados.setImageResource(id);
                        }
                    }
                }else {
                    NumAleatorio();
                }
            }
        }else {
            Intent intent = new Intent(this, MainActivity2_Nivel6.class);
            string_score = String.valueOf(score);
            string_vidas = String.valueOf(vidas);
            intent.putExtra("jugador", nombre_jugador);
            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);
            intent.putExtra("genero", genero_jugador);
            intent.putExtra("img", img_jugador);
            startActivity(intent);
            finish();
        }
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
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            /*i.putExtra("jugador", nombre_jugador);
                            i.putExtra("genero", genero_jugador);*/
                            startActivity(i);
                            BaseDeDatos();
                            finish();
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
    @Override
    public void onBackPressed(){
    }
}