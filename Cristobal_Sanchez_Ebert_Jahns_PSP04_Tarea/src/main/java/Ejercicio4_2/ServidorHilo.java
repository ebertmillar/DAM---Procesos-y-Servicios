/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4_2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebert
 */
public class ServidorHilo extends Thread{

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

            String ruta = flujoEntrada.readUTF();

            File fichero = new File(ruta);

            if (fichero.exists()) {

                flujoSalida.writeBoolean(true);
                BufferedReader br = new BufferedReader(new FileReader(ruta));

                String linea = "";
                String contenido = "";
                
                while((linea = br.readLine()) != null){
                    contenido += linea+"\n";
                }
                
                br.close();
                
                byte[] contenidoFichero = contenido.getBytes();
                
                flujoSalida.writeInt(contenidoFichero.length);
                
                for (int i = 0; i < contenidoFichero.length; i++) {
                    flujoSalida.writeByte(contenidoFichero[i]);
                }
                
                sCliente.close();
                System.out.println("El cliente esta Desconectado");
                
            }else{
                flujoSalida.writeBoolean(false);
            }

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
