/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4_3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class ServidorHilo extends Thread {

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

            flujoSalida.writeUTF("Ingresa el usuario");
            String usuario = flujoEntrada.readUTF().trim();

            flujoSalida.writeUTF("Ingresa la contraseña");
            String contraseña = flujoEntrada.readUTF().trim();

            if (usuario.equals("ebert") && contraseña.equals("123456")) {

                flujoSalida.writeBoolean(true);

                boolean salir = false;

                while (!salir) {

                    flujoSalida.writeUTF("\nIngresa uno de estos comandos (dir / get / salir) : ");
                    //dir para ficheros en 
                    String opcion = flujoEntrada.readUTF().trim();
                    System.out.println(opcion);

                    switch (opcion) {

                        case "dir":

                            File directorioActual = new File("../Cristobal_Sanchez_Ebert_Jahns_PSP04_Tarea");
                            File[] ficheros = directorioActual.listFiles();

                            ArrayList<String> listaFicheros = new ArrayList<>();
                            for (int i = 0; i < ficheros.length; i++) {
                                if (ficheros[i].isFile()) {
                                    listaFicheros.add(ficheros[i].getName());
                                }
                            }

                            flujoSalida.writeInt(listaFicheros.size());

                            for (String nombresFichero : listaFicheros) {
                                flujoSalida.writeUTF(nombresFichero);
                            }

                            break;

                        case "get":

                            String ruta = flujoEntrada.readUTF();

                            File fichero = new File("../Cristobal_Sanchez_Ebert_Jahns_PSP04_Tarea/" + ruta);

                            if (fichero.exists()) {

                                flujoSalida.writeBoolean(true);
                                BufferedReader br = new BufferedReader(new FileReader("../Cristobal_Sanchez_Ebert_Jahns_PSP04_Tarea/" + ruta));

                                String linea = "";
                                String contenido = "";

                                while ((linea = br.readLine()) != null) {
                                    contenido += linea + "\n";
                                }

                                br.close();

                                byte[] contenidoFichero = contenido.getBytes();

                                flujoSalida.writeInt(contenidoFichero.length);

                                for (int i = 0; i < contenidoFichero.length; i++) {
                                    flujoSalida.writeByte(contenidoFichero[i]);
                                }

                            } else {
                                flujoSalida.writeBoolean(false);
                            }

                            break;
                        case "salir":
                            salir = true;
                            break;
                    }
                }

            } else {

                flujoSalida.writeBoolean(false);
            }

            sCliente.close();
            System.out.println("El cliente esta Desconectado");

        } catch (IOException ex) {

            Logger.getLogger(Ejercicio4_2.ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                flujoEntrada.close();
                flujoSalida.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio4_2.ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
