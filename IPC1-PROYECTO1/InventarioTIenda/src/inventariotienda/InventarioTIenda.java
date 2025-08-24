/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventariotienda;

/**
 *
 * @author APROJUSA
 */
class AgregarProducto{
    
}

class BuscarProducto{
    
}

class EliminarProducto{
    
}

class RegistrarVenta{
    
}

class GenerarReporte{
    
}

class Estudiante{
    String nombre;
    int carnet;
    
    public Estudiante(String nombre, int carnet){
        this.nombre = nombre;
        this.carnet = carnet;
    }
    //*Getters para la clase estudiante*/
    public String getnombre(){
        return nombre;
    }
    public int getcarnet(){
        return carnet;
    }
    //*Setters para la clase estudiante*/
    public void setnombre(String nombre){
        this.nombre = nombre;
    }
    public void setcarnet(int carnet){
        this.carnet = carnet;
    }
    @Override
    public String toString(){
        return "Nombre:" + nombre + "\n" + "Carnet: " + carnet; 
    }
}

class Bitacora{
    
}
class Menu{
    
}

public class InventarioTIenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
