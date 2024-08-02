/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio4_1;

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
    static final int PUERTO = 2000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Socket sCliente = new Socket(HOST, PUERTO);

            //establecer comunicacion con el servidor
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());

            boolean salir = false;

            do {

                String mensaje = flujoEntrada.readUTF();
                System.out.println(mensaje);

                Scanner teclado = new Scanner(System.in);
                int num = teclado.nextInt();
                flujoSalida.writeInt(num);

                mensaje = flujoEntrada.readUTF();
                System.out.println(mensaje);

                salir = flujoEntrada.readBoolean();

            } while (!salir);

            sCliente.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
