import javax.swing.*;

public abstract class Tarjeta {
    private String nombre;
    private boolean descubierta;

    public Tarjeta(String nombre) {
        this.nombre = nombre;
        this.descubierta = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void voltear() {
        descubierta = !descubierta;
    }

    public abstract Icon getImagen(); // Imagen específica por tipo
}
