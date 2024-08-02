/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp05_ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ebert
 */
public class ServidorHTTP {

    /**
     * **************************************************************************
     * procedimiento principal que asigna a cada petición entrante un socket
     * cliente, por donde se enviará la respuesta una vez procesada
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {

        //Asociamos al servidor el puerto 8066
        ServerSocket socServidor = new ServerSocket(8066);
        imprimeDisponible();
        Socket socCliente;
        HiloDespachador hilo;

        // Ciclo infinito para manejar conexiones entrantes de los clientes
        while (true) {
            //acepta una petición, y le asigna un socket cliente para la respuesta
            socCliente = socServidor.accept();
            
           //crea un nuevo hilo para despacharla por el socketCliente que le asignó
            hilo = new HiloDespachador(socCliente);
            hilo.start();
    
        }
        /**
         * **************************************************************************
         * muestra un mensaje en la Salida que confirma el arranque, y da
         * algunas indicaciones posteriores
         */ 
    }
    
    
    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
                + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
                + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
                + "localhost:8066/q\n para simular un error");
    }
    
}
