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
    protected String detalle;
    protected int stock;
    
    public Producto(String codigo, String nombre, String categoria, String detalle){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.detalle = detalle;
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
    public abstract String getDetalle();
    public int getStock() { 
        return stock; 
    }
    
    //--------------------------------------------------------
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void aumentarStock(int cantidad) { 
        this.stock += cantidad; 
    }
    public void disminuirStock(int cantidad) { 
        this.stock -= cantidad; 
    }
}
