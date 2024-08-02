/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio2;

/**
 *
 * @author ebert
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesa mesa = new Mesa(5);
        for (int i = 1; i < 5; i++) {
            Filosofo filosofo = new Filosofo(mesa, i);
            filosofo.start();
        }
    }
    
}
