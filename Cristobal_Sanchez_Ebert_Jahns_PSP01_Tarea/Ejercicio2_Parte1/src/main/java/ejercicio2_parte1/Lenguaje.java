/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ejercicio2_parte1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class Lenguaje {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * declarando variables 
         */
        String letras = "abcdefghijklmnopqrstuvyxz";
        String miFicheroDeLenguaje;
        FileLock block = null;

        /**
         * proceso para generar conjunto de letras de forma aleatoria
         */
        if (args.length == 2) {

            try {
               
                int cantidadLineas = Integer.parseInt(args[0]);  //cantidad de lineas donde cada linea va ateer un conjunto de letras
                String nombreSistemaOperativo = System.getProperty("os.name"); //obtiene el nombre del sistema operativo

                if (nombreSistemaOperativo.toUpperCase().contains("WIN")) { 
                    miFicheroDeLenguaje = args[1].replace("\\", "\\\\");
                } else {
                    miFicheroDeLenguaje = args[1];
                }

                File archivo = new File(miFicheroDeLenguaje); // crea el fichero con nombre miFichero.txt

                if (!archivo.exists()) {

                    archivo.createNewFile(); //si el archivo no existe crea uno  nuevo
                }

                RandomAccessFile raf = new RandomAccessFile(archivo, "rwd");//permite escribir datos en el fichero.

                block = raf.getChannel().lock();

                raf.seek(archivo.length());

   
                for (int i = 0; i < cantidadLineas; i++) { // este bucle for genera cada conjunto de letras por linea
                    String linea = "";
                    int numeroCaracteres = generaNumeroAleatorio(1, 10);
                    for (int j = 0; j < numeroCaracteres; j++) {
                        linea += letras.charAt(generaNumeroAleatorio(0, letras.length() - 1));
                    }

                    raf.writeChars(linea + "\n");
                }

                block.release();
                block = null;
                raf.close();

            } catch (IOException ex) {
                Logger.getLogger(Lenguaje.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("el programa debe tener 2 parametros");
        }

    }

    /**
     * Metodo para obtener numeros aleatorios que se encuntren entre el paramentro
     * minimo y maximo
     * @param min
     * @param max
     * @return 
     */
    private static int generaNumeroAleatorio(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + (min));
        return num;
    }
}
