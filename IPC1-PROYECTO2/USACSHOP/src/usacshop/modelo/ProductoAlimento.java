/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class ProductoAlimento extends Producto{
    private String fechaCaducidad;
    public ProductoAlimento(String codigo, String nombre, String fechaCaducidad){
        super(codigo, nombre, "Alimento");
        this.fechaCaducidad = fechaCaducidad;
    }
    
    @Override
    public String getDetalle(){
        returns; "Caduca el;¡: " + fechaCaducidad;
    }
            
}
