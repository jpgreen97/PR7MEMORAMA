import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class MemoramaJuego extends JFrame {
    private int numJugadores;
    private String tipoTarjeta;
    private List<Jugador> jugadores;
    private int turnoActual = 0;

    private List<Tarjeta> tarjetas;
    private JButton[] botones;
    private Tarjeta tarjetaSeleccionada1 = null;
    private Tarjeta tarjetaSeleccionada2 = null;
    private JButton botonSeleccionado1 = null;
    private JButton botonSeleccionado2 = null;

    public MemoramaJuego(String tipoTarjeta, int numJugadores) {
        this.tipoTarjeta = tipoTarjeta;
        this.numJugadores = numJugadores;
        this.jugadores = new ArrayList<>();

        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }

        setTitle("Memorama - estas jugando con " + tipoTarjeta);
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tarjetas = generarTarjetas();
        Collections.shuffle(tarjetas); // Mezclar

        JPanel panelTablero = new JPanel(new GridLayout(4, 4)); // 16 cartas (8 pares)
        botones = new JButton[tarjetas.size()];

        for (int i = 0; i < tarjetas.size(); i++) {
            JButton boton = new JButton();
            boton.setIcon(new ImageIcon("imagenes/reverso.png")); // imagen oculta
            int index = i;

            boton.addActionListener(e -> manejarSeleccion(index, boton));

            botones[i] = boton;
            panelTablero.add(boton);
        }

        add(panelTablero, BorderLayout.CENTER);
        actualizarTitulo();
    }

    private void manejarSeleccion(int index, JButton boton) {
        Tarjeta tarjeta = tarjetas.get(index);

        if (tarjeta.estaDescubierta() || boton == botonSeleccionado1) return;

        boton.setIcon(tarjeta.getImagen());
        tarjeta.voltear();

        if (tarjetaSeleccionada1 == null) {
            tarjetaSeleccionada1 = tarjeta;
            botonSeleccionado1 = boton;
        } else {
            tarjetaSeleccionada2 = tarjeta;
            botonSeleccionado2 = boton;

            Timer timer = new Timer(1000, e -> verificarPareja());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void verificarPareja() {
        if (tarjetaSeleccionada1.getNombre().equals(tarjetaSeleccionada2.getNombre())) {
            jugadores.get(turnoActual).sumarPunto();
        } else {
            tarjetaSeleccionada1.voltear();
            tarjetaSeleccionada2.voltear();
            botonSeleccionado1.setIcon(new ImageIcon("imagenes/reverso.png"));
            botonSeleccionado2.setIcon(new ImageIcon("imagenes/reverso.png"));
            turnoActual = (turnoActual + 1) % numJugadores;
        }

        tarjetaSeleccionada1 = null;
        tarjetaSeleccionada2 = null;
        botonSeleccionado1 = null;
        botonSeleccionado2 = null;

        actualizarTitulo();
        verificarFinDeJuego();
    }

    private void actualizarTitulo() {
        setTitle("Turno de " + jugadores.get(turnoActual).getNombre());
    }

    private void verificarFinDeJuego() {
        boolean terminado = tarjetas.stream().allMatch(Tarjeta::estaDescubierta);
        if (terminado) {
            Jugador ganador = jugadores.stream().max(Comparator.comparingInt(Jugador::getPuntos)).get();
            JOptionPane.showMessageDialog(this, "Fin del juego!!!\nGano: " + ganador.getNombre());
            dispose();
        }
    }

    private List<Tarjeta> generarTarjetas() {
        List<Tarjeta> lista = new ArrayList<>();
        String[] nombres = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho"}; // 8 pares

        for (String nombre : nombres) {
            for (int i = 0; i < 2; i++) {
                Tarjeta t = switch (tipoTarjeta) {
                    case "Figuras" -> new TarjetaFigura(nombre, "geometrica");
                    case "Frutas" -> new TarjetaFruta(nombre, nombre.equals("naranja") || nombre.equals("limon"));
                    case "Banderas" -> new TarjetaBandera(nombre, "America");
                    default -> null;
                };
                lista.add(t);
            }
        }
        return lista;
    }
}
