package com.ejercicios.juegomemorama;

import javax.swing.*;

public abstract class Tarjeta {
    private boolean descubierta;

    public Tarjeta() {
        this.descubierta = false;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void voltear() {
        descubierta = !descubierta;
    }

    public abstract ImageIcon getImagen(); // imagen para mostrar en la GUI
}
