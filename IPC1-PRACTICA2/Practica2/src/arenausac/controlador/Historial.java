/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Katherin Yasmin
 */
public class Historial {
    private RegistroBatalla[] registros;
    private int cantidad;
    
    public Historial(int max){
        registros = new RegistroBatalla[max];
        cantidad = 0;
    }
    
    public void agregarRegistro(RegistroBatalla r){
        if (cantidad < registros.length){
            registros[cantidad] = r;
            cantidad++;
        } else{
            System.out.println("El historial esta lleno");
        }
    }
    
    public void mostrarHistorial(){
        if (cantidad == 0){
            System.out.println("No hay batallas registradas");
        } else{
            System.out.println("--Historial de batalas--");
            for (int i = 0; i < cantidad; i++){
                System.out.println(registros[i]);
            }
        }
    }
    
    public void guardarHistorial(String nombreArchivo) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo));
            for (int i = 0; i < cantidad; i++) {
                RegistroBatalla r = registros[i];
                // Guardamos cada campo separado por coma
                pw.println(r.toString());
            }
            pw.close();
            System.out.println("Historial guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar historial: " + e.getMessage());
        }
    }
}
