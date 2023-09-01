package co.christian.animapp;

public class PersonajeVo {
    private String nombre;
    private int foto;
    //private int sonido;

    public PersonajeVo(){

    }

    public PersonajeVo(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
        //this.sonido = sonido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    /*public int getSonido() {
        return sonido;
    }

    public void setSonido(int sonido) {
        this.sonido = sonido;
    }*/
}
