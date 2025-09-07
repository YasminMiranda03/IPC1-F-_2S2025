/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventariotienda;

/**
 *
 * @author APROJUSA
 */
class Producto {
    String nombre;
    String categoria;
    int cantidad;
    int codigo;
    double precio;
    
    public Producto(String nombre, String categoria, int cantidad, int codigo, double precio){
        nombre = nombre;
        categoria = categoria;
        codigo = codigo;
        cantidad = cantidad;
        precio = precio;
    }
    public void mostrarProducto(){
        System.out.println("Nombre:" + nombre);
        System.out.println("Categoria:" + categoria);
        System.out.println("Código:" + codigo);
        System.out.println("Cantidad:" + cantidad);
        System.out.println("Precio en Q:" + precio);
    }
}
class Inventario{
    public Producto[] productos = new Producto[50]
            public int contador = 0;
            public boolean agregarProducto(Producto){
                if (contador >= 50){
                    System.out.println("Inventario lleno");
                    return false;
                }
                int i = 0;
                while (i < contador){
                    if (producto[i] != null && productos[i].codigo != null && producto[i].codigo.equallsIgnoreCase(p.codigo)){
                        System.out.println("Ya existe un producto con ese codigo");
                        return false;
                    }
                    i = i + 1;
                }
            }
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
    do{
        System.out.println("--Menú--");
        System.out.println("1. Agregar nuevo producto")
        System.out.println("2. Ver inventario")
        System.out.println("3. Eliminar producto")
        switch(opcion){
            case 1:
                System.out.println("funciona?");
                break;
            case 2:
                System.out.println("a lo mejor y si");
                break;
            case 3:
                System.out.println("ojala que si");
                break;
        }
                
    }
    while{
        opcion (!= 9);
    }
}

public class InventarioTIenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("");
    }
    
}
