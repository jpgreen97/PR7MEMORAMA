import java.awt.Image;
import javax.swing.*;

public class TarjetaFigura extends Tarjeta {
    private String tipoFigura; // nuevo atributo

    public TarjetaFigura(String nombre, String tipoFigura) {
        super(nombre);
        this.tipoFigura = tipoFigura;
    }

    public String getTipoFigura() {
        return tipoFigura;
    }


    @Override
    public Icon getImagen() {
          String ruta = "imagenes/figuras/" + getNombre() + ".png";
    // Tamaño deseado (ej: 100x100 píxeles)
    return redimensionarImagen(ruta, 150, 150);
    }

         
public ImageIcon redimensionarImagen(String ruta, int ancho, int alto) {
    ImageIcon iconoOriginal = new ImageIcon(ruta);
    Image imagenRedimensionada = iconoOriginal.getImage()
            .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenRedimensionada);
}

   // public String descripcionDetallada() {
        //return "Figura: " + getNombre() + " (" + tipoFigura + ")";
   // }
}
