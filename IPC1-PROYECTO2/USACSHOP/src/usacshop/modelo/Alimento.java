/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author APROJUSA
 */
public class Alimento extends Producto {
    private String fechaCaducidad;

    public Alimento(String codigo, String nombre, String categoria, String fechaCaducidad) {
        super(codigo, nombre, categoria, "Fecha de caducidad: " + fechaCaducidad);
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String getDetalle() {
        return "Fecha de caducidad: " + fechaCaducidad;
    }
}
