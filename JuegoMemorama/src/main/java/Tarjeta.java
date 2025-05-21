import javax.swing.*;

public abstract class Tarjeta {
    protected String nombre;
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

    public abstract Icon getImagen(); 
    
    public abstract String getInformacion();
    
    public abstract void efectoEspecial(Jugador jugador); 

}
