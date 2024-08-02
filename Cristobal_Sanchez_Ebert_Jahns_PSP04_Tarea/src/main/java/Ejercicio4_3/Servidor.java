/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio4_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class Servidor {

    static final int PUERTO=1500; 
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         try {
            // Inicio la escucha del servidor en el Puerto 2000
            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            
            while (true) {                
                Socket sCliente = skServidor.accept();
                ServidorHilo sh = new ServidorHilo(sCliente);
                sh.start();
            }
                        
        }  catch (IOException ex) {
            Logger.getLogger(Ejercicio4_2.Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}


