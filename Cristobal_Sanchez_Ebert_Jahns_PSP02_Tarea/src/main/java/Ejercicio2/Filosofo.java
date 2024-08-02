/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ebert
 */
public class Filosofo extends Thread {

    private Mesa mesa;
    private int comensal;
    private int indiceComensal;
    
    public Filosofo(Mesa m, int comensal){
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
        this.mesa = m;
    }

    public void run() {
        while (true) {
            try {
                this.pensando();
                System.out.println("Filosofo " + this.comensal + " Hambriento");
                mesa.cogerPalillos(this.indiceComensal);
                this.comiendo();
                System.out.println("Filosofo " + this.comensal + " Termina de comer, Libres palillos: " + (this.mesa.palilloIzquierda(this.indiceComensal) + 1) + ", " + (this.mesa.palilloDerecho(this.indiceComensal) + 1));
                mesa.dejarPalillos(this.indiceComensal);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 
     * @throws InterruptedException 
     */
    public void pensando() throws InterruptedException {
        System.out.println("Filosofo " + this.comensal + " Pensando");
        Thread.sleep(2000);
    }

    /**
     * 
     * @throws InterruptedException 
     */
    public void comiendo() throws InterruptedException {
        System.out.println("Filosofo " + this.comensal + " Comiendo");
        Thread.sleep(2000);
    }

}
