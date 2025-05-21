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
    
     // pqneles para mostrar la informacion
    private JPanel panelInfo;
    private JLabel lblTurno;
    private JLabel[] lblPuntosJugadores;
    private JLabel lblInformacionHerencia;

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
            boton.setIcon(redimensionarImagen("imagenes/reverso.jpeg", 180, 180));
            int index = i;

            boton.addActionListener(e -> manejarSeleccion(index, boton));

            botones[i] = boton;
            panelTablero.add(boton);
        }

         panelInfo = new JPanel(new GridLayout(2, numJugadores + 1));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Informacion del Juego"));
        
        lblInformacionHerencia = new JLabel("Selecciona una tarjeta", SwingConstants.CENTER);
        panelInfo.add(lblInformacionHerencia);
        
        lblTurno = new JLabel("Turno: " + jugadores.get(turnoActual).getNombre(), SwingConstants.CENTER);
        panelInfo.add(lblTurno);
        
        lblPuntosJugadores = new JLabel[numJugadores];
        for (int i = 0; i < numJugadores; i++) {
            lblPuntosJugadores[i] = new JLabel(jugadores.get(i).getNombre() + ": 0 pts", SwingConstants.CENTER);
            panelInfo.add(lblPuntosJugadores[i]);
        }
        
        add(panelInfo, BorderLayout.NORTH);
        
        add(panelTablero, BorderLayout.CENTER);
    }

    private void manejarSeleccion(int index, JButton boton) {
        Tarjeta tarjeta = tarjetas.get(index);
        
        lblInformacionHerencia.setText(tarjeta.getInformacion());

        if (tarjeta.estaDescubierta() || boton == botonSeleccionado1) return;

        boton.setIcon(tarjeta.getImagen());
        tarjeta.voltear();

        if (tarjetaSeleccionada1 == null) {
            tarjetaSeleccionada1 = tarjeta;
            botonSeleccionado1 = boton;
            lblInformacionHerencia.setText("Selecciona otra tarjeta"); 
        } else {
            tarjetaSeleccionada2 = tarjeta;
            botonSeleccionado2 = boton;
            lblInformacionHerencia.setText("Verificando tarjeta."); // Feedback inmediato


            Timer timer = new Timer(250, e -> verificarPareja());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void verificarPareja() {
        if (tarjetaSeleccionada1.getNombre().equals(tarjetaSeleccionada2.getNombre())) {
            jugadores.get(turnoActual).sumarPunto();
            
            Jugador jugadorActual = jugadores.get(turnoActual);

            //jugadorActual.sumarPunto();
            
            tarjetaSeleccionada1.efectoEspecial(jugadorActual);

            // Mostrar información del par encontrado
        String infoPar = tarjetaSeleccionada1.getInformacion();
        lblInformacionHerencia.setText(infoPar);
        
            
             lblPuntosJugadores[turnoActual].setText(
                jugadores.get(turnoActual).getNombre() + ": " + 
                (jugadores.get(turnoActual).getPuntos() * 5) + " pts"
            );
        } else {
            tarjetaSeleccionada1.voltear();
            tarjetaSeleccionada2.voltear();
            botonSeleccionado1.setIcon(redimensionarImagen("imagenes/reverso.jpeg", 180, 180));
            botonSeleccionado2.setIcon(redimensionarImagen("imagenes/reverso.jpeg", 180, 180));
            turnoActual = (turnoActual + 1) % numJugadores;
            lblInformacionHerencia.setText("Intenta de nuevo"); 
            lblTurno.setText("Turno: " + jugadores.get(turnoActual).getNombre());

        }

        tarjetaSeleccionada1 = null;
        tarjetaSeleccionada2 = null;
        botonSeleccionado1 = null;
        botonSeleccionado2 = null;

        verificarFinDeJuego();
    }

    private void verificarFinDeJuego() {
        boolean terminado = tarjetas.stream().allMatch(Tarjeta::estaDescubierta);
        if (terminado) {
           // se obtiene la puntuacion maxima
        int maxPuntos = jugadores.stream()
                               .mapToInt(Jugador::getPuntos)
                               .max()
                               .orElse(0);
        
        // se filtran los jugadores por puntuacion
        List<Jugador> ganadores = jugadores.stream()
                                         .filter(j -> j.getPuntos() == maxPuntos)
                                         .toList();
        
        // mostrar mensaje de empate o ganador
        if (ganadores.size() > 1) {
            JOptionPane.showMessageDialog(this, "¡Fin del juego!\n¡Empate entre los jugadores!");
        } else {
            JOptionPane.showMessageDialog(this, "Fin del juego!!!\nGano: " + ganadores.get(0).getNombre());
        }
            dispose();
            
             //regresar al menu principal
        SwingUtilities.invokeLater(() -> {
            new MenuInicio().setVisible(true);
        });
        }
    }

    private List<Tarjeta> generarTarjetas() {
        List<Tarjeta> lista = new ArrayList<>();
        String[] nombresFiguras = {"cuadrado" , "rectangulo" ,"circulo" , "rombo" , "ovalo" , "pentagono"};
        String[] nombresBanderas = { "mexico" , "panama" , "elsalvador" , "venezuela" , "brasil" , "uruguay" }; 
        String [] nombresFrutas = {"calabaza" , "chile" , "limon" , "manzana" ,"kiwi" , "melon" };
        
        if(tipoTarjeta.equals("Figuras"))
        {
            
        for (String nombre : nombresFiguras) {
            for (int i = 0; i < 2; i++) {
                Tarjeta t = switch (tipoTarjeta) {
                    case "Figuras" -> new TarjetaFigura(nombre, "geometrica");
                    default -> null;
                };
                lista.add(t);
            }
        }
        
        }
        
        if(tipoTarjeta.equals("Banderas"))
        {
            
        for (String nombre : nombresBanderas) {
            for (int i = 0; i < 2; i++) {
                Tarjeta t = switch (tipoTarjeta) {
                    case "Banderas" -> new TarjetaBandera(nombre, "America");
                    default -> null;
                };
                lista.add(t);
            }
        }
        
        }
        
        if(tipoTarjeta.equals("Frutas"))
        {
            
        for (String nombre : nombresFrutas) {
            for (int i = 0; i < 2; i++) {
                Tarjeta t = switch (tipoTarjeta) {
                    case "Frutas" -> new TarjetaFruta(nombre, nombre.equals("kiwi") || nombre.equals("limon"));
                    default -> null;
                };
                lista.add(t);
            }
        }
        
        }
        
        return lista;
    }
    
   public ImageIcon redimensionarImagen(String ruta, int ancho, int alto) {
    ImageIcon iconoOriginal = new ImageIcon(ruta);
    Image imagenRedimensionada = iconoOriginal.getImage()
            .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenRedimensionada);
}
   
}
