/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;

/**
 *
 * @author Katherin Yasmin
 */
import arenausac.modelo.Personaje;

public class RegistroBatalla {
    private Personaje p1;
    private Personaje p2;
    private Personaje ganador;

    public RegistroBatalla(Personaje p1, Personaje p2, Personaje ganador) {
        this.p1 = p1;
        this.p2 = p2;
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "Batalla entre " + p1.getNombre() + " y " + p2.getNombre()
               + "Ganador: " + ganador.getNombre();
    }
}
