package com.ejercicios.juegomemorama;

import javax.swing.*;

public class MenuInicial {

    public static String mostrar() {
        String[] opciones = { "Banderas", "Figuras", "Frutas" };
        return (String) JOptionPane.showInputDialog(
                null,
                "Elige el tipo de tarjeta que quieres para jugar:",
                "Memorama",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
    }

    public static void main(String[] args) {
        String seleccion = mostrar();
        if (seleccion != null) {
            JOptionPane.showMessageDialog(null, "Elegiste: " + seleccion);
            // para cagrar las imagenes
        }
    }
}
