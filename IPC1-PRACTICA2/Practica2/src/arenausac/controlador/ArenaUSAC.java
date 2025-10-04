/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;

import arenausac.modelo.Personaje;
import java.io.*;

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
                personajes[i] = new Personaje(personajes[i].getNombre(),
                        arma, hp, ataque, velocidad, agilidad, defensa);{
             
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
    
    public Personaje[] getListaPersonajes() {
        Personaje[] lista = new Personaje[cantidad];
        for (int i = 0; i < cantidad; i++) {
            lista[i] = personajes[i];
        }
        return lista;
    }
    
    public void guardarPersonajes(String nombreArchivo) {
    try {
        PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo));
        for (int i = 0; i < cantidad; i++) {
            Personaje p = personajes[i];
            // Guardamos los atributos separados por coma
            pw.println(p.getId() + "," + p.getNombre() + "," + p.getArma() + "," +
                       p.getHp() + "," + p.getAtaque() + "," + p.getVelocidad() + "," +
                       p.getAgilidad() + "," + p.getDefensa());
        }
        pw.close();
        System.out.println("Personajes guardados en " + nombreArchivo);
    } catch (IOException e) {
        System.out.println("Error al guardar personajes: " + e.getMessage());
    }
}

    public void cargarPersonajes(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (!archivo.exists()) {
                System.out.println("No hay archivo de personajes para cargar.");
                return;
            }
            Scanner lector = new Scanner(archivo);
            cantidad = 0; // reiniciar
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(",");
                // [0]=id , [1]=nombre , [2]=arma , [3]=hp , [4]=ataque , [5]=velocidad , [6]=agilidad , [7]=defensa
                String nombre = datos[1];
                String arma = datos[2];
                int hp = Integer.parseInt(datos[3]);
                int ataque = Integer.parseInt(datos[4]);
                int velocidad = Integer.parseInt(datos[5]);
                int agilidad = Integer.parseInt(datos[6]);
                int defensa = Integer.parseInt(datos[7]);

                Personaje p = new Personaje(nombre, arma, hp, ataque, velocidad, agilidad, defensa);
                personajes[cantidad] = p;
                cantidad++;
            }
            lector.close();
            System.out.println("Personajes cargados desde " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("Error al cargar personajes: " + e.getMessage());
        }
    }
}
