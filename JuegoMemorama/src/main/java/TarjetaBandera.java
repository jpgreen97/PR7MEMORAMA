import java.awt.Image;
import java.util.Set;
import javax.swing.*;

public class TarjetaBandera extends Tarjeta {
    private String pais;
    private String continente;
    private static final Set<String> PAISES_BONUS = Set.of(
        "mexico", "brasil", "venezuela" 
    );
    public TarjetaBandera(String nombre, String continente) {
        super(nombre);
        this.continente = continente;
        this.pais = obtenerPaisPorNombre(nombre);
    }

    public String getPais() {
        return pais;
    }

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


    @Override
    public String getInformacion() {
    return "Pais:  " +  pais  + " - Continente:  " +  continente;

    }
    
    //Obtienes el nombre con switch
    private String obtenerPaisPorNombre(String nombre) {
        switch(nombre.toLowerCase()) {
            case "mexico": return "Mexico";
            case "panama": return "Panama";
            case "elsalvador": return "El Salvador";
            case "venezuela": return "Venezuela";
            case "brasil": return "Brasil";
            case "uruguay": return "Uruguay";
            default: return nombre;
        }
        
    }

    @Override
    public void efectoEspecial(Jugador jugador) {
         if (PAISES_BONUS.contains(pais.toLowerCase())) {
            jugador.sumarPunto(); 
            JOptionPane.showMessageDialog(null, 
                "Puntos dobles por bandera de " + pais);
        }
    }
}
