    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;

    public class MenuInicio extends JFrame {

        private JComboBox<String> tipoTarjetaCombo;
        private JComboBox<Integer> cantidadJugadoresCombo;
        private JButton botonIniciar;


        public MenuInicio() {
            setTitle("Memorama - Menu");
            setSize(400, 250);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));


            //tipo de tarjeta
            JPanel panelTipo = new JPanel();
            panelTipo.add(new JLabel("Tipo de Tarjeta:"));
            String[] tipos = {"Figuras", "Frutas", "Banderas"};
            tipoTarjetaCombo = new JComboBox<>(tipos);
            panelTipo.add(tipoTarjetaCombo);



            //cantidad de jugadores
            JPanel panelJugadores = new JPanel();
            panelJugadores.add(new JLabel("Numero de Jugadores:"));
            Integer[] cantidades = {2, 3, 4};
            cantidadJugadoresCombo = new JComboBox<>(cantidades);
            panelJugadores.add(cantidadJugadoresCombo);

            botonIniciar = new JButton("Iniciar Juego");
            botonIniciar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    iniciarJuego();
                }
            });

            add(new JLabel("MEMORAMA", SwingConstants.CENTER));
            add(panelTipo);
            add(panelJugadores);
            add(botonIniciar);
        }

        private void iniciarJuego() {
            String tipoSeleccionado = (String) tipoTarjetaCombo.getSelectedItem();
            int numJugadores = (int) cantidadJugadoresCombo.getSelectedItem();
            new MemoramaJuego(tipoSeleccionado, numJugadores).setVisible(true);
            this.dispose();
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new MenuInicio().setVisible(true);
            });
        }
    }
