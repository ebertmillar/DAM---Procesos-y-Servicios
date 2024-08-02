/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1_parte1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class OrdenarNumeros {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * codigo que permite ordenar los numeros de forma ascendente
         */
        try {
            System.out.println("Ingresa Numeros ");
            /**
             * Se instancian las clases que nos van a permirtir obtener la entrada
             * de datos
             */
            InputStreamReader entrada = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(entrada);

            String lineaTexto = null;

            /**
             * proceso para ordenar numeros de forma ascendete
             */
            while ((lineaTexto = br.readLine()) != null) {
                String datos[] = lineaTexto.split(" ");
                int numeros[] = new int[datos.length];

                for (int i = 0; i < numeros.length; i++) {
                    numeros[i] = Integer.parseInt(datos[i]);
                }

                Arrays.sort(numeros); //ordena los numeros de forma ascendente

                for (int i = 0; i < numeros.length; i++) {
                    System.out.print(numeros[i] + " ");
                }

                System.out.println("");

            }

        } catch (IOException ex) {
            Logger.getLogger(OrdenarNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
