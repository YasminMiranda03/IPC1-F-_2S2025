/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class Cliente extends Usuario{
    private String fechaNacimiento;
    
    public Cliente(String codigo, String nombre, String genero, String contraseña){
        super(codigo, nombre, genero, contraseña);
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
}
