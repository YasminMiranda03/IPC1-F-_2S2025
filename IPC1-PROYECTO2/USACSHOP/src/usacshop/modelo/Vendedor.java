/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Katherin Yasmin
 */
public class Vendedor extends Usuario{
    private int ventasConfirmadas = 0;
    
    public Vendedor(String codigo, String nombre, String genero, String contraseña){
        super(codigo, nombre, genero, contraseña);
    }
    
    public int getVentasConfirmadas(){
        return ventasConfirmadas;
    }
    
    public void incrementarVentas(){
        ventasConfirmadas++;
    }
}
