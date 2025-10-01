/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;


import java.io.*;
import java.util.Scanner;
import javax.swing.JTextArea;

import arenausac.modelo.Personaje;
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
    
    public void mostrarHistorial(JTextArea areaTexto){
        if (cantidad == 0){
            areaTexto.append("No hay batallas registradas");
        } else{
            areaTexto.append("Historial de batallas: \n");
            for (int i = 0; i < cantidad; i++){
                areaTexto.append(registros[i].toString() + "\n");
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
    
    public void registrarBatalla(Personaje p1, Personaje p2, Personaje ganador) {
        RegistroBatalla registro = new RegistroBatalla(p1, p2, ganador);
        agregarRegistro(registro);
        System.out.println("Batalla registrada en historial: " + registro);
    }
}
