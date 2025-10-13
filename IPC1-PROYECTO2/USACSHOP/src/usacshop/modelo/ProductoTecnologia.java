/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class ProductoTecnologia extends Producto {
    private int mesesGarantia;

    public ProductoTecnologia(String codigo, String nombre, int mesesGarantia) {
        super(codigo, nombre, "Tecnología");
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public String getDetalle() {
        return "Garantía: " + mesesGarantia + " meses";
    }
}
