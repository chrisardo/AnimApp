package co.eduardo.animapp;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Arrays;
public class AnimalDB {
    public static int ADIVINADOS;
    public static int INTENTOS = 3;
    public static int NUMEROGENERADO = 0;
    private static ArrayList<Animal> listaAnimal = new ArrayList<>(Arrays.asList(
            new Animal(0, "cangrejo", "s_cangrejo", false)
            , new Animal(1, "canguro", "s_canguro", false)
            , new Animal(2, "oveja", "s_oveja", false)
            , new Animal(3, "delfin", "s_delfin", false)
            , new Animal(4, "leon", "s_leon", false)
            , new Animal(5, "tigre", "s_tigre", false)
            , new Animal(6, "mono", "s_mono", false)
            , new Animal(7, "gato", "s_gato", false)
            , new Animal(8, "perro", "s_perro", false)
            , new Animal(9, "caballo", "s_caballo", false)
            , new Animal(10, "koala", "s_koala", false)
            , new Animal(11, "jirafa", "s_jirafa", false)
            , new Animal(12, "pinguino", "s_pinguino", false)
            , new Animal(13, "elefante", "s_elefante", false)
            , new Animal(14, "loro", "s_loro", false)
            , new Animal(15, "tortuga", "s_tortuga", false)
            , new Animal(16, "conejo", "s_conejo", false)
            , new Animal(17, "ardilla", "s_ardilla", false)
            , new Animal(18, "oso", "s_oso", false)
            , new Animal(19, "tiburon", "s_tiburon", false)
            , new Animal(20, "iguana", "s_iguana", false)
            , new Animal(21, "vaca", "s_vaca", false)
            , new Animal(22, "cerdo", "s_cerdo", false)
            , new Animal(23, "avestruz", "s_avestruz", false)
            , new Animal(24, "abeja", "s_abeja", false)
            , new Animal(25, "pulpo", "s_pulpo", false)
            , new Animal(26, "gallina", "s_gallina", false)
            , new Animal(27, "zorro", "s_zorro", false)
            , new Animal(28, "burro", "s_burro", false)
            , new Animal(29, "saltamonte", "s_saltamonte", false)
            , new Animal(30, "caracol", "s_caracol", false)
            , new Animal(31, "venado", "s_venado", false)
            , new Animal(32, "rata", "s_rata", false)
            , new Animal(33, "murcielago", "s_murcielago", false)
            , new Animal(34, "ornitorrinco", "s_ornitorrinco", false)
            , new Animal(35, "pato", "s_pato", false)
            , new Animal(36, "pollo", "s_pollo", false)
            , new Animal(37, "gallo", "s_gallo", false)
            , new Animal(38, "tucan", "s_tucan", false)
            , new Animal(39, "llama", "s_llama", false)
            , new Animal(40, "hormiga", "s_hormiga", false)
            , new Animal(41, "cocodrilo", "s_cocodrilo", false)
            , new Animal(42, "rinoceronte", "s_rinoceronte", false)
            , new Animal(43, "foca", "s_foca", false)
            , new Animal(44, "nutria", "s_nutria", false)
            , new Animal(45, "hipopotamo", "s_hipopotamo", false)
            , new Animal(46, "pelicano", "s_pelicano", false)
            , new Animal(47, "aguila", "s_aguila", false)
            , new Animal(48, "camello", "s_camello", false)
            , new Animal(49, "medusa", "s_medusa", false)
            , new Animal(50, "cebra", "s_cebra", false)
            , new Animal(51, "aguila", "s_aguila", false)
            , new Animal(52, "caballitodelmar", "s_caballitodelmar", false)
            , new Animal(53, "colibri", "s_colibri", false)
    ));
    public static String getNombre(int id) {
        return listaAnimal.get(id).getNombre().toLowerCase().replace(" ", "_");
    }
    public static void setAdivinado(int id, boolean valor) {
        listaAnimal.get(id).setAdivinado(valor);
    }
    public static boolean isWin() {
        return ADIVINADOS == getTamaño();
    }
    public static int getTamaño() {
        return listaAnimal.size();
    }
    public static boolean isAdivinado(int id) {
        return listaAnimal.get(id).isAdivinado();
    }
    public static boolean isAnimal(String x) {
        return listaAnimal.get(NUMEROGENERADO).getNombre().equalsIgnoreCase(x);
    }
    public static String getSombra(int id) {
        return listaAnimal.get(id).getSombra().toLowerCase().replace(" ", "_");
    }
    public static void cargarDatos(Context c) {
        SharedPreferences mispreferencias = c.getSharedPreferences("DatosJuego", Context.MODE_PRIVATE);
        INTENTOS = mispreferencias.getInt("INTENTOS", 4);
        ADIVINADOS = mispreferencias.getInt("ADIVINADOS", 0);
        for (Animal elemento: listaAnimal){
            elemento.setAdivinado(mispreferencias.getBoolean(elemento.getNombre(), false));
        }
    }
}
