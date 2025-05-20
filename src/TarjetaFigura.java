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
        return null;
    }

    //@Override
    //public Icon getImagen() {
        //return new ImageIcon("imagenes/figuras/" + getNombre() + ".png");
    //}

   // public String descripcionDetallada() {
        //return "Figura: " + getNombre() + " (" + tipoFigura + ")";
   // }
}
