/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

import java.io.Serializable;

/**
 *
 * @author Katherin Yasmin
 */
public class Cliente implements Serializable{
    private String codigo;
    private String nombre;
    private String genero;
    private String fechaNacimiento;
    
    public Cliente(String codigo, String nombre, String genero, String fechaNacimiento){
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public String getGenero(){
        return genero;
    }
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
}
