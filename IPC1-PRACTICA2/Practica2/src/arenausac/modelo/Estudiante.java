/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class Estudiante {
    private String nombre;
    private String carne;
    

    public Estudiante(String nombre, String carne) {
        this.nombre = nombre;
        this.carne = carne;
        
    }

    @Override
    public String toString() {
        return "DATOS DEL ESTUDIANTE" +
               "Nombre: " + nombre + "\n" +
               "Carne: " + carne + "\n";
    }
}
