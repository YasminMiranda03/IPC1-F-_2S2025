/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;
import arenausac.modelo.Personaje;

/**
 *
 * @author APROJUSA
 */
public class ArenaUSAC {
    private Personaje[] personajes;
    private int cantidad;
    
    public ArenaUSAC(int maxPersonajes){
        personajes = new Personaje[maxPersonajes];
        cantidad = 0;
    }
    
    public void agregarPersonaje(Personaje p){
        if (cantidad >= personajes.length){
            System.out.println("Error, no se pueden agregar mas personajes");
            return;
        }
        
        for (int i = 0; i< cantidad; i++){
            if (personajes[i].getNombre().equalsIgnoreCase(p.getNombre())){
                System.out.println("Error, ya existe un personaje con ese nombre");
                return;
            }
        }
        
        personajes[cantidad] = p;
        cantidad++;
        System.out.println("Personaje agregado: " + p.getNombre());
    }
}
