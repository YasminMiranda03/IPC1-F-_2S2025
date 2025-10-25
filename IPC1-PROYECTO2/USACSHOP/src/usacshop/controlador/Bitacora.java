/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.controlador;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Katherin Yasmin
 */
public class Bitacora {
    private static final String ARCHIVO_BITACORA = "bitacora.txt";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Método principal para registrar un evento
    public static void registrarEvento(String tipoUsuario, String codigoUsuario, 
                                       String operacion, String estado, String descripcion) {
        String fechaHora = LocalDateTime.now().format(FORMATO_FECHA);
        String linea = String.format("[%s] | %s | %s | %s | %s | %s",
                                     fechaHora, tipoUsuario, codigoUsuario, operacion, estado, descripcion);

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ARCHIVO_BITACORA, true)))) {
            pw.println(linea);
        } catch (IOException e) {
            System.err.println("Error al escribir en la bitácora: " + e.getMessage());
        }
    }
}
