/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ebert
 */
public class Consumidor extends Thread{
    
    private Buffer buffer;
    private int consume;
    private final int LIMIT = 15;

    /**
     * 
     * @param buffer 
     */
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
        this.consume = 0;
    }
    
    public void run (){
        while (consume< LIMIT) {            
            
            try {
                char caracter = buffer.consumir();
                System.out.println("Recogido el caracter " +caracter+ " del buffer");
                consume++;
                sleep((int) (Math.random()*4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }
}
