/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ebert
 */
public class MainProductorConsumidor {

    public static void main(String[] args) {
        try {
            Buffer buffer = new Buffer(6);
            Productor productor = new Productor(buffer);
            Consumidor consumidor = new Consumidor(buffer);
            
            
            productor.start();
            Thread.sleep(3000);
            consumidor.start();
            
            productor.join();
            consumidor.join();
            
            System.out.println("Programa Terminado");
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MainProductorConsumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
