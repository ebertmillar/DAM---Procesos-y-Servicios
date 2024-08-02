/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ebert
 */
public class Mesa {
    
    private Semaphore[] palillos;

    public Mesa(int numPalillos) {
        this.palillos = new Semaphore[numPalillos];
        
        for (int i = 0; i < numPalillos; i++) {
            this.palillos[i] = new Semaphore(1);
        }      
    }
    
    public int palilloIzquierda(int i){
    
        return i;
    }

    public int palilloDerecho(int i){
    
        if(i==0){
            return this.palillos.length - 1;
        }else{
            return i - 1;        
        }
    }
    
    /**
     * 
     * @param filosofo
     * @throws InterruptedException 
     */
    public void cogerPalillos(int filosofo) throws InterruptedException{
        this.palillos[this.palilloIzquierda(filosofo)].acquire();
        this.palillos[this.palilloDerecho(filosofo)].acquire();    
    }
    
    /**
     * 
     * @param filosofo 
     */
    public void dejarPalillos(int filosofo){
        this.palillos[this.palilloIzquierda(filosofo)].release();
        this.palillos[this.palilloDerecho(filosofo)].release();        
    }
    
    
    
    
}
