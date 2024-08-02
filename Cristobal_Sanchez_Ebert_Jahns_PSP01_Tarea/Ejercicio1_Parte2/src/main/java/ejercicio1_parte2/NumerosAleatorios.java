/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ejercicio1_parte2;

/**
 *
 * @author Ebert
 */
public class NumerosAleatorios {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * declaración de variables
         */
        int cantidadNumeros = 40;
        int numeros;

        /**
         * proceso que va permitir generar 40 numeros aleatroios entre 0 y 100
         */
        for (int i = 0; i < cantidadNumeros; i++) {
            numeros = (int) (Math.floor(Math.random() * (100 - 0 + 1) + 0));
            System.out.print(numeros + " ");
        }
    }
}
