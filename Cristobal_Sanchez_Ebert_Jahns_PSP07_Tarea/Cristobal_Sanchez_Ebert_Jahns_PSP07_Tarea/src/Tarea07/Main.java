/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea07;

import java.io.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;

/**
 *
 * @author Ebert
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Definición de variables para el nombre de usuario, contraseña y la ruta del archivo
        String username = "ebert";
        String password = "123456";
        String filePath = "ficheroPrueba.txt";

        try {

            // Leer el contenido del archivo
            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] fileContent = new byte[fileInputStream.available()];
            fileInputStream.read(fileContent);
            fileInputStream.close();

            // Imprimir el contenido del archivo en la consola
            System.out.println("Cadena de texto del ficheroPrueba.txt :");
            System.out.println(new String(fileContent));

            // Generar la clave a partir del nombre de usuario y contraseña
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((username + password).getBytes());
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128, random);
            SecretKey clave = keyGen.generateKey();

            // Obtener la representación de la clave en Base64
            String claveBase64 = Base64.getEncoder().encodeToString(clave.getEncoded());
            System.out.println("\nClave generada (en formato Base64): " + claveBase64);

            // Crear el cifrador AES con el modo y relleno especificados
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clave);

            // Encriptar el contenido del archivo
            byte[] textoEncriptado = cipher.doFinal(fileContent);

            // Escribir el texto encriptado en un archivo
            FileOutputStream fos = new FileOutputStream("fichero.cifrado");
            fos.write(textoEncriptado);
            fos.close();

            System.out.println("Texto encriptado y almacenado en el archivo 'fichero.cifrado'");

            // Leer el archivo encriptado y desencriptarlo
            FileInputStream fis = new FileInputStream("fichero.cifrado");
            byte[] textoEncriptadoLeido = new byte[fis.available()];
            fis.read(textoEncriptadoLeido);
            fis.close();

            System.out.println("\nEl contenido del archivo encriptado es:");
            System.out.println(new String(textoEncriptadoLeido));

            cipher.init(Cipher.DECRYPT_MODE, clave);

            // Desencriptar el texto leído
            byte[] textoDesencriptado = cipher.doFinal(textoEncriptadoLeido);

            // Imprimir el texto desencriptado leído en la consola
            System.out.println("\nEl archivo se ha desencriptado y la cadena de texto es:");
            System.out.println(new String(textoDesencriptado));
            System.out.println("\nGenerado el fichero de desencriptación 'fichero_desencriptado.txt'");

            // Escribir el texto desencriptado en un archivo
            FileOutputStream fos2 = new FileOutputStream("fichero_desencriptado.txt");
            fos2.write(textoDesencriptado);
            fos2.close();

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
        }
    }
}
