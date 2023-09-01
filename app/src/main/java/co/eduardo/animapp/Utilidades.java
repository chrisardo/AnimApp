package co.eduardo.animapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utilidades {
    public static final int LIST=1;
    public static int visualizacion=LIST;
    //permite tener la referencia del avatar seleccionado para el momento del registro en la BD
    public static AvatarVo avatarSeleccion=null;
    public static int avatarIdSeleccion=0;

    public static ArrayList<AvatarVo> listaAvatars=null;
    public static ArrayList<JugadorVo> listaJugadores=null;

    public static int correctas, incorrectas, puntaje,nivelJuego;

    public static final String NOMBRE_BD="stroopers_bd";
    //Constantes campos tabla usuario
    public static final String TABLA_JUGADOR="jugador";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_GENERO="genero";
    public static final String CAMPO_AVATAR="avatar";

    //Constantes campos tabla puntaje
    public static final String TABLA_PUNTAJE="puntaje_jugador";
    public static final String CAMPO_ID_JUGADOR="id";
    public static final String CAMPO_PUNTOS="puntos";
    public static final String CAMPO_NIVEL="nivel";


    public static final String CREAR_TABLA_JUGADOR="CREATE TABLE "+TABLA_JUGADOR+" ("+CAMPO_ID+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_GENERO+" TEXT,"+CAMPO_AVATAR+" INTEGER)";
    public static final String CREAR_TABLA_PUNTAJES="CREATE TABLE "+TABLA_PUNTAJE+" ("+CAMPO_ID_JUGADOR+" INTEGER, "
            +CAMPO_PUNTOS+" INTEGER,"+" INTEGER,"+CAMPO_NIVEL+" TEXT)";


    public static void obtenerListaAvatars() {
        //se instancian los avatars y se llena la lista
        listaAvatars=new ArrayList<AvatarVo>();
        listaAvatars.add(new AvatarVo(1,R.drawable.caballo,"Avatar1"));
        listaAvatars.add(new AvatarVo(2,R.drawable.canguro,"Avatar2"));
        listaAvatars.add(new AvatarVo(3,R.drawable.gato,"Avatar3"));
        listaAvatars.add(new AvatarVo(4,R.drawable.perro,"Avatar4"));
        listaAvatars.add(new AvatarVo(5,R.drawable.koala,"Avatar5"));
        listaAvatars.add(new AvatarVo(6,R.drawable.oveja,"Avatar6"));
        listaAvatars.add(new AvatarVo(7,R.drawable.jirafa,"Avatar7"));
        listaAvatars.add(new AvatarVo(8,R.drawable.pinguin,"Avatar8"));
        listaAvatars.add(new AvatarVo(9,R.drawable.mono,"Avatar9"));
        listaAvatars.add(new AvatarVo(10,R.drawable.delfin,"Avatar10"));
        listaAvatars.add(new AvatarVo(11,R.drawable.cangrejo,"Avatar11"));
        listaAvatars.add(new AvatarVo(12,R.drawable.leon,"Avatar12"));
        listaAvatars.add(new AvatarVo(13,R.drawable.tigre,"Avatar13"));
        listaAvatars.add(new AvatarVo(14,R.drawable.conejo,"Avatar14"));
        listaAvatars.add(new AvatarVo(15,R.drawable.elefante,"Avatar15"));
        listaAvatars.add(new AvatarVo(16,R.drawable.loro,"Avatar16"));
        listaAvatars.add(new AvatarVo(17,R.drawable.tortuga,"Avatar17"));
        listaAvatars.add(new AvatarVo(18,R.drawable.ardilla,"Avatar18"));
        listaAvatars.add(new AvatarVo(19,R.drawable.oso,"Avatar19"));
        listaAvatars.add(new AvatarVo(20,R.drawable.tiburon,"Avatar20"));
        listaAvatars.add(new AvatarVo(21,R.drawable.iguana,"Avatar21"));
        listaAvatars.add(new AvatarVo(22,R.drawable.vaca,"Avatar22"));
        listaAvatars.add(new AvatarVo(23,R.drawable.cerdo,"Avatar23"));
        listaAvatars.add(new AvatarVo(24,R.drawable.abeja,"Avatar24"));
    }

    public static void consultarListaJugadores(Activity actividad) {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        JugadorVo jugador=null;
        listaJugadores=new ArrayList<JugadorVo>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_JUGADOR,null);

        while (cursor.moveToNext()){
            jugador=new JugadorVo();
            jugador.setId(cursor.getInt(0));
            jugador.setNombre(cursor.getString(1));
            jugador.setGenero(cursor.getString(2));
            jugador.setAvatar(cursor.getInt(3));

            listaJugadores.add(jugador);
        }

        db.close();
    }
}
