import java.awt.Image;
import javax.swing.*;

public class TarjetaBandera extends Tarjeta {
    private String continente;

    public TarjetaBandera(String nombre, String continente) {
        super(nombre);
        this.continente = continente;
    }

    public String getContinente() {
        return continente;
    }

//    @Override
//    public Icon getImagen() {
//        return null;
//    }

     @Override
     public Icon getImagen() {
        
        
        String ruta = "imagenes/banderas/" + getNombre() + ".png";
    // Tamaño deseado (ej: 100x100 píxeles)
    return redimensionarImagen(ruta, 180, 180);

    }

     
public ImageIcon redimensionarImagen(String ruta, int ancho, int alto) {
    ImageIcon iconoOriginal = new ImageIcon(ruta);
    Image imagenRedimensionada = iconoOriginal.getImage()
            .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenRedimensionada);
}

    //public String descripcionDetallada() {
      //  return "Bandera de " + getNombre() + " (" + continente + ")";
    //}
}
