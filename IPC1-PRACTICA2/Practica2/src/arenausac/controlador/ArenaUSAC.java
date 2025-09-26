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
    
    public void listarPersonajes(){
        if (cantidad == 0){
            System.out.println("No hay personajes registrados");
        } else {
            for (int i = 0; i < cantidad; i++){
                System.out.println(personajes[i]);
            }
        }
    }
    
    public void eliminarPersonaje(int id){
        boolean encontrado = false;
        for (int i = 0; i < cantidad; i++){
            if(personajes[i].getId() == id){
                for (int j = i; j < cantidad -1; j++){
                    personajes[j] = personajes[j + 1];
                }
                personajes[cantidad - 1] = null;
                cantidad--;
                encontrado = true;
                System.out.println("Personaje con ID " + id + "eliminado");
                break;
            }
        }
        if (!encontrado){
            System.out.println("No se encontro el personaje con ID " + id);
        }
    }
    
    public Personaje buscarPorNombre(String nombre){
        for (int i = 0; i < cantidad; i++){
            if (personajes[i].getNombre().equalsIgnoreCase(nombre)){
                return personajes[i];
            }
        }
        return null;
    }
    
    public void modificarPersonaje(int id, String arma, int hp, int ataque, int velocidad, int agilidad, int defensa){
        for (int i = 0; i < cantidad; i++){
            if (personajes[i].getId() == id){
                personajes[i] = new Personaje(personajes[i].getNombre(), arma, int hp, int ataque, int velocidad, int agilidad, int defensa){
                System.out.println("Personaje con ID " + id + "modificado");
                return;
                }
            }
            System.out.println("No se encontro el personaje con ID " + id);
        }
    }
    
    public Personaje buscarPorId(int id){
        for (int i = 0; i < cantidad; i++){
            if (personajes[i].getId() == id){
                return personajes[i];
            }
        }
        return null;
    }
}
