/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ebert
 */
public class Buffer {

    private char[] buffer;
    private int siguienteValor;
    private boolean completo;
    private boolean vacio;

    /**
     * 
     * @param tamanio 
     */
    public Buffer(int tamanio) {
        this.buffer = new char[tamanio];
        this.siguienteValor = 0;
        this.completo = false;
        this.vacio = true;
    }

    public synchronized char consumir() {

        while (this.vacio) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.siguienteValor--;
        this.completo = false;

        if (this.siguienteValor == 0) {
            this.vacio = true;
        }

        notifyAll();

        return this.buffer[this.siguienteValor];
    }

    /**
     * 
     * @param c 
     */
    public synchronized void producir(char c) {

        while (this.completo) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.buffer[this.siguienteValor] = c;

        this.siguienteValor++;
        this.vacio = false;

        if (this.siguienteValor == this.buffer.length) {
            this.completo = true;
        }

        notifyAll();

    }

}
