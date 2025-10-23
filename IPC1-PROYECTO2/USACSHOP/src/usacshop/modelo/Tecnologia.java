/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class Tecnologia extends Producto {
    private String mesesGarantia;

    public Tecnologia(String codigo, String nombre, String categoria, String mesesGarantia) {
        super(codigo, nombre, categoria);
        this.mesesGarantia = mesesGarantia;
    }

   

    @Override
    public String getDetalle() {
        return "Garantía: " + mesesGarantia + " meses";
    }
}
