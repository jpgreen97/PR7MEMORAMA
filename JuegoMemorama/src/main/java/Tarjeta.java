import java.awt.Image;
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

    protected static ImageIcon redimensionarImagen(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenRedimensionada = iconoOriginal.getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
    
}
