/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio4_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class Cliente {

    static final String HOST = "localhost";
    static final int PUERTO = 1500;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Socket sCliente = new Socket(HOST, PUERTO);

            try ( //establecer comunicacion con el servidor
                DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream())) {
                DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
                
                Scanner teclado = new Scanner(System.in);
                
                System.out.println("Introduce la ruta del fichero:");
                String ruta = teclado.next();
                
                flujoSalida.writeUTF(ruta);
                
                boolean existe = flujoEntrada.readBoolean();
                
                if(existe){
                    
                    int longitud = flujoEntrada.readInt();
                    
                    byte[] contenido = new byte[longitud];
                    
                    for (int i = 0; i < longitud; i++) {
                        contenido[i] = flujoEntrada.readByte();
                    }
                    
                    String contenidoFichero = new String(contenido);
                    
                    System.out.println(contenidoFichero);
                    
                    sCliente.close();
                    
                }else{
                    System.out.println("No existe el fichero!");
                }
               
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4_2.Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
