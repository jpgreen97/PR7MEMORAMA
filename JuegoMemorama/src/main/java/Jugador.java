public class Jugador {
    private String nombre;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public void sumarPunto() {
        puntos++;
    }

     public void sumarPuntos(int cantidad) {
        puntos += cantidad;
    }
     
    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }
}
