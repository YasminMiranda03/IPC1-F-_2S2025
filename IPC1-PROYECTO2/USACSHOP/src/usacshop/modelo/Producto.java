/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
import java.io.Serializable;

public abstract class Producto implements Serializable{
    protected String codigo;
    protected String nombre;
    protected String categoria;
    protected int stock; 
    
    public Producto(String codigo, String nombre, String categoria){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = 0;
    }
    
    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCategoria(){
        return categoria;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public abstract String getDetalle();
}
