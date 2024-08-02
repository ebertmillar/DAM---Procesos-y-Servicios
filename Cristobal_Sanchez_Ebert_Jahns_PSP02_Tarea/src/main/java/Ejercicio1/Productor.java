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
public class Productor extends Thread{
    
    private Buffer buffer;
    private final String letras ="ABCDEFGHIJKLMNOPQRSTUVWYXZ";
    private int produce;
    private final int LIMIT = 15;

    /**
     * 
     * @param buffer 
     */
    public Productor(Buffer buffer) {
        this.buffer = buffer;
        this.produce = 0;
    }
    
    public void run (){
        while (produce< LIMIT) {            
            
            try {
                char caracter  = letras.charAt((int)(Math.random() * letras.length()));
                buffer.producir(caracter);
                produce++;
                System.out.println("Depositado el caracter " + caracter + " en el buffer");
                sleep((long) (Math.random() * 4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }
    
    
}
