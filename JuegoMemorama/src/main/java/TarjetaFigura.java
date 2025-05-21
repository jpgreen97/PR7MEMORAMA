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
    
    public TarjetaFigura(String nombre, String color) {
        super(nombre);
        this.color = obtenerColorNombre(nombre);
    }

    public String getFiguraColor() {
        return color;
    }


    @Override
    public Icon getImagen() {
          String ruta = "imagenes/figuras/" + getNombre() + ".png";
    return redimensionarImagen(ruta, 150, 150);
    }

         
public ImageIcon redimensionarImagen(String ruta, int ancho, int alto) {
    ImageIcon iconoOriginal = new ImageIcon(ruta);
    Image imagenRedimensionada = iconoOriginal.getImage()
            .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenRedimensionada);
}

    @Override
    public String getInformacion() {
        return "Figura: " + nombre + " - Color: " + color;

    }
    
     //Obtienes el nombre con switch
    private String obtenerColorNombre(String nombre) {
        switch(nombre.toLowerCase()) {
            case "circulo": return "verde";
            case "cuadrado": return "rojo";
            case "ovalo": return "verde soldado";
            case "pentagono": return "morado";
            case "rectangulo": return "naranja";
            case "rombo": return "azul";
            default: return nombre;
        }
        
    }

    @Override
        public void efectoEspecial(Jugador jugador) {
        String figura = getNombre().toLowerCase();

        if (FIGURAS_LADOS.containsKey(figura)) {
        int lados = FIGURAS_LADOS.get(figura);
        for (int i = 0; i < lados; i++) {
            jugador.sumarPunto();
        }
        JOptionPane.showMessageDialog(null, 
            "Puntos multiplicados por " + lados + " (lados del " + figura + ")");
    }   
    }


}
