package co.christian.animapp;

public class Animal {
    private int id;
    private String nombre;
    private String sombra;
    private boolean adivinado;

    public Animal(int id, String nombre, String sombra, boolean adivinado) {
        this.id = id;
        this.nombre = nombre;
        this.sombra = sombra;
        this.adivinado = adivinado;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSombra() {
        return sombra;
    }

    public void setSombra(String sombra) {
        this.sombra = sombra;
    }

    public boolean isAdivinado() {
        return adivinado;
    }

    public void setAdivinado(boolean adivinado) {
        this.adivinado = adivinado;
    }
}
