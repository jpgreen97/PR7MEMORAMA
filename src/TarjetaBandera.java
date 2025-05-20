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

    @Override
    public Icon getImagen() {
        return null;
    }

    // @Override
    // public Icon getImagen() {
     //   return new ImageIcon("imagenes/banderas/" + getNombre() + ".png");
    //}

    //public String descripcionDetallada() {
      //  return "Bandera de " + getNombre() + " (" + continente + ")";
    //}
}
