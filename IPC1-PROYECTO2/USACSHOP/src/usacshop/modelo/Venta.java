/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.modelo;

/**
 *
 * @author Kathern Yasmin
 */
public class Venta {
    private String codigoProducto;
    private int cantidadVendida;
    private double total;

    public Venta(String codigoProducto, int cantidadVendida, double total) {
        this.codigoProducto = codigoProducto;
        this.cantidadVendida = cantidadVendida;
        this.total = total;
    }

    public String getCodigoProducto() { 
        return codigoProducto; 
    }
    public int getCantidadVendida() { 
        return cantidadVendida; 
    }
    public double getTotal() { 
        return total; 
    }
}
