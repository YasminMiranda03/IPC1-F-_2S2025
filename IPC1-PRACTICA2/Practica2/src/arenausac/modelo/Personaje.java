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
        
        if (hp < 100) {
            this.hp = 100;
        } else if (hp > 500) {
            this.hp = 500;
        } else {
            this.hp = hp;
        }

        if (ataque < 10) {
            this.ataque = 10;
        } else if (ataque > 100) {
            this.ataque = 100;
        } else {
            this.ataque = ataque;
        }

        if (velocidad < 1) {
            this.velocidad = 1;
        } else if (velocidad > 10) {
            this.velocidad = 10;
        } else {
            this.velocidad = velocidad;
        }

        if (agilidad < 1) {
            this.agilidad = 1;
        } else if (agilidad > 10) {
            this.agilidad = 10;
        } else {
            this.agilidad = agilidad;
        }

        if (defensa < 1) {
            this.defensa = 1;
        } else if (defensa > 50) {
            this.defensa = 50;
        } else {
            this.defensa = defensa;
        }
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getArma() { return arma; }
    public int getHp() { return hp; }
    public int getAtaque() { return ataque; }
    public int getVelocidad() { return velocidad; }
    public int getAgilidad() { return agilidad; }
    public int getDefensa() { return defensa; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " (" + arma + ") "
             + "HP:" + hp + " Atk:" + ataque
             + " Vel:" + velocidad + " Agi:" + agilidad + " Def:" + defensa;
    }
    
}
