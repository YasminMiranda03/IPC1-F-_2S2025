/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class ProductoGeneral extends Producto {
    private String material;

    public ProductoGeneral(String codigo, String nombre, String material) {
        super(codigo, nombre, "General");
        this.material = material;
    }

    @Override
    public String getDetalle() {
        return "Material: " + material;
    }
}
