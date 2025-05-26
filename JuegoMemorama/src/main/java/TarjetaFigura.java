
import java.awt.Image;
import java.util.Map;
import java.util.Set;
import javax.swing.*;

public class TarjetaFigura extends Tarjeta {

    private String color;

    private static final Map<String, Integer> FIGURAS_LADOS = Map.of(
            "cuadrado", 4,
            "rombo", 4,
            "pentagono", 5,
            "rectangulo", 4
    );

    private static final Map<String, String> FIGURAS_COLORES = Map.of(
            "circulo", "verde",
            "cuadrado", "rojo",
            "ovalo", "verde soldado",
            "pentagono", "morado",
            "rectangulo", "naranja",
            "rombo", "azul"
    );

    public TarjetaFigura(String nombre) {
        super(nombre);
        this.color = FIGURAS_COLORES.getOrDefault(nombre.toLowerCase(), "");
    }

    public String getFiguraColor() {
        return color;
    }

    @Override
    public Icon getImagen() {
        String ruta = "imagenes/figuras/" + getNombre() + ".png";
        return redimensionarImagen(ruta, 150, 150);
    }

    @Override
    public String getInformacion() {
        return "Figura: " + nombre + " - Color: " + color;

    }

    @Override
    public void efectoEspecial(Jugador jugador) {

        String nombreFigura = nombre.toLowerCase();

        // Si la figura es circulo u ovalo, no se suman puntos extras
        if (nombreFigura.equals("circulo") || nombreFigura.equals("ovalo")) {
            return;
        }

        int lados = FIGURAS_LADOS.getOrDefault(nombreFigura, 0);
        jugador.sumarPuntos(lados);
        JOptionPane.showMessageDialog(null,
                "+ " + lados + " puntos extras. Por los lados de la figura.");
    }

}
