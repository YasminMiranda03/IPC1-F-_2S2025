/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class General extends Producto{
     private String material;

    public General(String codigo, String nombre, String categoria, String material) {
        super(codigo, nombre, categoria, "Material: " + material);
        this.material = material;
    }

    @Override
    public String getDetalle() {
        return "Material: " + material;
    }
}
