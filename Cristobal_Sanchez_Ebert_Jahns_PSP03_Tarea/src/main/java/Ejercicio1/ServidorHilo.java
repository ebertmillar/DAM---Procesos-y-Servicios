/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class ServidorHilo extends Thread{
    
    private Socket sCliente;

    public ServidorHilo(Socket sCliente) {
        this.sCliente = sCliente;
    }
    
    public void run() {

        System.out.println("El Cliente esta conectado");
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;

        try {
            flujoEntrada = new DataInputStream(sCliente.getInputStream());
            flujoSalida = new DataOutputStream(sCliente.getOutputStream());

            Random random = new Random();
            int numAleatorio = random.nextInt(100) + 1;
            int numeroUsuario;

            System.out.println("Numero generado: " + numAleatorio);

            do {

                flujoSalida.writeUTF("Escribe un numero entre 1 y 100: ");

                numeroUsuario = flujoEntrada.readInt();

                System.out.println("Numero Recibido: " + numeroUsuario);

                if (numeroUsuario == numAleatorio) {
                    flujoSalida.writeUTF("Has ganado!!!");
                } else if (numeroUsuario < numAleatorio) {
                    flujoSalida.writeUTF("El numero que necesitas es mayor");
                } else {
                    flujoSalida.writeUTF("El numero que necesitas es menor");
                }

                flujoSalida.writeBoolean(numeroUsuario == numAleatorio);

            } while (numeroUsuario != numAleatorio);
            
            sCliente.close();
            System.out.println("El cliente esta Desconectado");

        } catch (IOException ex) {
            
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                flujoEntrada.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
