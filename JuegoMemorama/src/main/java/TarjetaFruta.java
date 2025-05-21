import java.awt.Image;
import javax.swing.*;

public class TarjetaFruta extends Tarjeta {
    private boolean esCitrica;

    public TarjetaFruta(String nombre, boolean esCitrica) {
        super(nombre);
        this.esCitrica = esCitrica;
    }

    public boolean esCitrica() {
        return esCitrica;
    }

    @Override
    public Icon getImagen() {
  String ruta = "imagenes/frutas/" + getNombre() + ".png";
    return redimensionarImagen(ruta, 150, 150);    }

    public ImageIcon redimensionarImagen(String ruta, int ancho, int alto) {
    ImageIcon iconoOriginal = new ImageIcon(ruta);
    Image imagenRedimensionada = iconoOriginal.getImage()
            .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenRedimensionada);
}

    @Override
    public String getInformacion() {
         return "Fruta: " + nombre + "-" + 
               (esCitrica ? "Es citrico" : "No es citrico");
    }

    @Override
    public void efectoEspecial(Jugador jugador) {
        
          if (esCitrica) {
        jugador.sumarPunto(); 
        JOptionPane.showMessageDialog(null, 
            "Fruta citrica \n" + jugador.getNombre() + " gana 10 puntos extras.");
    }
    }

}
