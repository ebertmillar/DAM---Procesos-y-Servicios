/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ejercicio2_parte2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ebert
 */
public class Colaborar {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                for (int i = 1; i <= 10; i++) {

                    System.out.println("Lanzar el proceso: " + i);

                    String comando = "java -jar lenguaje.jar " + (i * 10) + " " + args[0];
                    System.out.println("Comando lanzado " + comando);

                    Runtime.getRuntime().exec(comando);

                }
            } catch(SecurityException ex){
                System.out.println("Error" + ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(Colaborar.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Debe tener 1 parametro");
        }

    }
}
