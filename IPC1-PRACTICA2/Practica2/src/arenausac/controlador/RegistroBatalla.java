/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;

/**
 *
 * @author Katherin Yasmin
 */
public class RegistroBatalla {
    private String personaje1;
    private String personaje2;
    private String ganador;
    private String fecha;
    
    public RegistroBatalla(String personaje1, String personaje2, String ganador, String fecha){
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
        this.ganador = ganador;
        this.fecha = fecha;
    }
    @Override
    public String toString(){
        return fecha + "   " + personaje1 + " vs " + personaje2 + "Ganador: " + ganador;
    }
}
