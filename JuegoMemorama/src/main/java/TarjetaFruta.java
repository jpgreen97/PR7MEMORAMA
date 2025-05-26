import java.awt.Image;
import java.util.Set;
import javax.swing.*;

public class TarjetaFruta extends Tarjeta {

    //Frutas citricas
     private static final Set<String> FRUTAS_CITRICAS = Set.of(
        "limon", "naranja"
    );
     
    public TarjetaFruta(String nombre) {
        super(nombre);
    }

    public boolean esCitrica() {
        return FRUTAS_CITRICAS.contains(getNombre().toLowerCase());
    }

    @Override
    public Icon getImagen() {
  String ruta = "imagenes/frutas/" + getNombre() + ".png";
    return redimensionarImagen(ruta, 150, 150);    }


    @Override
    public String getInformacion() {
         return "Fruta: " + nombre + "-" + 
               (esCitrica() ? "Es citrico" : "No es citrico");//Condicion para saber si es citrico o no
    }

    @Override
    public void efectoEspecial(Jugador jugador) {
        if (esCitrica()) {
            jugador.sumarPuntos(10); // suma 10 puntos extra si es cítrica
            JOptionPane.showMessageDialog(null,
                "+10 puntos extras por ser una fruta citrica");
        }
    }

}
