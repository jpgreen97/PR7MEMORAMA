import java.awt.Image;
import java.util.Map;
import java.util.Set;
import javax.swing.*;

public class TarjetaBandera extends Tarjeta {
    private String continente;
    private static final Set<String> PAISES_BONUS = Set.of(
        "mexico", "brasil", "venezuela" 
    );
    
       // Países con su respectivo continente
    private static final Map<String, String> PAISES_CONTINENTES = Map.of(
        "mexico", "América",
        "panama", "América",
        "elsalvador", "América",
        "venezuela", "América",
        "brasil", "América",
        "uruguay", "América"
    );
    
    public TarjetaBandera(String nombre) {
        super(nombre);
        this.continente = PAISES_CONTINENTES.getOrDefault(nombre.toLowerCase(), "Desconocido");
    }

     @Override
     public Icon getImagen() {
        String ruta = "imagenes/banderas/" + getNombre() + ".png";
    return redimensionarImagen(ruta, 180, 180);

    }

    @Override
    public String getInformacion() {
        return "Pais: " + getNombre() + " - Continente: " + continente;
    }
    
    @Override
    public void efectoEspecial(Jugador jugador) {
         if (PAISES_BONUS.contains(getNombre().toLowerCase())) {
            jugador.sumarPuntos(5); //Sumas 5 puntos extras por la bandera elegida
            JOptionPane.showMessageDialog(null, 
                "+5 puntos extras por la bandera de " + getNombre());
        }
    }
}
