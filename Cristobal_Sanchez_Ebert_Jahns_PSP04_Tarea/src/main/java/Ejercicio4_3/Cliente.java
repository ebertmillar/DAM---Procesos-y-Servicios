/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio4_3;

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

                //codigo para la tarea 4
                String mensaje = flujoEntrada.readUTF().trim();
                System.out.println(mensaje);
                String usuario = teclado.next();
                flujoSalida.writeUTF(usuario);

                mensaje = flujoEntrada.readUTF().trim();
                System.out.println(mensaje);
                String contraseña = teclado.next();
                flujoSalida.writeUTF(contraseña);

                boolean correcto = flujoEntrada.readBoolean();

                if (correcto) {

                    boolean salir = false;

                    while (!salir) {
                        mensaje = flujoEntrada.readUTF().trim();
                        System.out.println(mensaje);
                        String opcion = teclado.next();
                        flujoSalida.writeUTF(opcion);

                        switch (opcion) {

                            case "dir":

                                
                                int cantidadFicheros = flujoEntrada.readInt();
                                System.out.println("Hay " + cantidadFicheros+ " Ficheros en el directorio actual ");
                                System.out.println("Nombre de los ficheros: ");
                                
                                       
                                for (int i = 0; i < cantidadFicheros; i++) {
                                    String nombreFichero = flujoEntrada.readUTF().trim();
                                    System.out.println(nombreFichero);
                                }

                                break;

                            case "get":

                                System.out.println("Introduce la ruta del fichero");
                                String ruta = teclado.next();

                                flujoSalida.writeUTF(ruta);

                                boolean existe = flujoEntrada.readBoolean();

                                if (existe) {

                                    int longitud = flujoEntrada.readInt();

                                    byte[] contenido = new byte[longitud];

                                    for (int i = 0; i < longitud; i++) {
                                        contenido[i] = flujoEntrada.readByte();
                                    }

                                    String contenidoFichero = new String(contenido);

                                    System.out.println(contenidoFichero);

                                } else {
                                    System.out.println("No existe el fichero, ingresa uno que si exista");
                                }
                                break;

                            case "salir":
                                salir = true;
                                break;
                        }

                    }

                } else {
                    System.out.println("Usuario o contraseña no existe");
                }
                
                sCliente.close();

            }

        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4_2.Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
