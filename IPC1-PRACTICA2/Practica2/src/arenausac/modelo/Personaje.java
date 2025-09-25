/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class Personaje {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private String arma;
    private int hp;
    private int ataque;
    private int velocidad;
    private int agilidad;
    private int defensa;
    
    public Personaje(String nombre, String arma, int hp, int ataque, int velocidad, int agilidad, int defensa){
        this.id = contadorId++;
        this.nombre = nombre;
        this.arma = arma;
        
    }
    
}
