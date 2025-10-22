/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.controlador;

import usacshop.modelo.Vendedor;
import java.io.*;

/**
 *
 * @author APROJUSA
 */
public class VendedorControlador {
    private final String archivo = "vendedores.txt";

    public boolean registrarVendedor(Vendedor v) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(v.getCodigo() + "," + v.getNombre() + "," + v.getGenero() + "," + v.getContrasena());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vendedor buscarVendedor(String codigo, String contrasena) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    if (codigo.equalsIgnoreCase(datos[0].trim()) && 
                        contrasena.equals(datos[3].trim())) {
                        return new Vendedor(datos[0], datos[1], datos[2], datos[3]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
