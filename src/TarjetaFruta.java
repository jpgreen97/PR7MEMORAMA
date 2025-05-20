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
        return null;
    }

    //@Override
    //public Icon getImagen() {
        //return new ImageIcon("imagenes/frutas/" + getNombre() + ".png");
    //}

    //public String descripcionDetallada() {
        //return "Fruta: " + getNombre() + (esCitrica ? " (cítrica)" : " (no cítrica)");
    //}
}
