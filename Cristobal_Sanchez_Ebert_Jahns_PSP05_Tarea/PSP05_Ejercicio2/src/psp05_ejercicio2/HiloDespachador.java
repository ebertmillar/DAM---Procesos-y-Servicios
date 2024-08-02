/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp05_ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class HiloDespachador extends Thread {

    private Socket socketCliente;

    //constructor
    public HiloDespachador(Socket socketCliente) {
        this.socketCliente = socketCliente;

    }

    @Override
    public void run() {

        // Mensaje indicando que se está atendiendo al cliente
        System.out.println("Atendiendo al cliente : " + socketCliente.toString());

        try {

            // Procesa la petición del cliente
            procesaPeticion(socketCliente);
            //cierra la conexión entrante
            socketCliente.close();
            System.out.println("cliente atendido");
        } catch (IOException ex) {

            Logger.getLogger(HiloDespachador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *****************************************************************************
     * procesa la petición recibida
     *
     * @throws IOException
     */
    private static void procesaPeticion(Socket socketCliente) throws IOException {
        //variables locales
        String peticion;
        String html;

        //Flujo de entrada
        InputStreamReader inSR = new InputStreamReader(
                socketCliente.getInputStream());
        //espacio en memoria para la entrada de peticiones
        BufferedReader bufLeer = new BufferedReader(inSR);

        //objeto de java.io que entre otras características, permite escribir 
        //'línea a línea' en un flujo de salida
        PrintWriter printWriter = new PrintWriter(
                socketCliente.getOutputStream(), true);

        //mensaje petición cliente
        peticion = bufLeer.readLine();

        //para compactar la petición y facilitar así su análisis, suprimimos todos 
        //los espacios en blanco que contenga
        peticion = peticion.replaceAll(" ", "");

        //si realmente se trata de una petición 'GET' (que es la única que vamos a
        //implementar en nuestro Servidor)
        if (peticion.startsWith("GET")) {
            //extrae la subcadena entre 'GET' y 'HTTP/1.1'
            peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

            //si corresponde a la página de inicio
            if (peticion.length() == 0 || peticion.equals("/")) {
                //sirve la página
                System.out.println("Cliente está conectado en http://localhost:8066/");
                html = Paginas.html_index;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length());
                printWriter.println("\n");
                printWriter.println(html);
            } //si corresponde a la página del Quijote
            else if (peticion.equals("/quijote")) {
                //sirve la página
                System.out.println("Cliente está conectado a http://localhost:8066/quijote");
                html = Paginas.html_quijote;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length());
                printWriter.println("\n");
                printWriter.println(html);
            } //en cualquier otro caso
            else {
                //sirve la página
                System.out.println("Cliente  conectado en  http://localhost:8066" + peticion + " (No existe)");
                html = Paginas.html_noEncontrado;
                printWriter.println(Mensajes.lineaInicial_NotFound);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length());
                printWriter.println("\n");
                printWriter.println(html);
            }

        }
    }

}
