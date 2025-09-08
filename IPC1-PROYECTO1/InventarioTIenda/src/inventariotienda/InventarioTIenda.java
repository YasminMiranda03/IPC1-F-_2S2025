/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventariotienda;

/**
 *
 * @author APROJUSA
 */
//CLASE PRODUCTO
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
//CLASE INVENTARIO
class Inventario{
    public Producto[] listaProductos = new Producto[50]
            public int totalProductos = 0;
            public boolean agregarProducto(Producto nuevoProducto){
                if (totalProductos >= 50){
                    System.out.println("Inventario lleno");
                    return false;
                }
                 //verificacion de duplicado 
                for (int i = 0; i < totalProductos; i ++){
                    if (listaProductos[i] != null && listaProductos[i].codigoProducto != null && listaProductos[i].codigoProducto.equalsIgnoreCase(nuevoProducto.codigoProducto))
                        System.out.println("Ya existe un producto con ese codigo");
                    return false;
                }
            
            listaProductos[totalProductos] = nuevoProducto;
            totalProductos++;
            System.out.println("Producto agregado");
            return true
            }
            public void buscarProducto(String busqueda){ //
                boolean encontrado = false;
                for (int i = 0; i < totalProductos; i++){
                    Producto productoActual = listaProductos[i];
                    if (productoActual != null){
                        if (productoActual.codigoProducto.equalsIgnoreCase(busqueda) ||
                            productoActual.nombre.equalsIgnoreCase(busqueda) ||
                            productoActual.categoriaProducto.equalsIgnoreCase(busqueda)){
                            productoActual.mostrarProducto();
                            encontrado = true
                        }
                    }
                }
                if (!encontrado){
                    System.out.println("No se encontro nada");
                }
            public boolean eliminarProducto(String codigoEliminar){
                int indiceEliminar = -1;
                for (int i = 0; i < totalProductos; i++){
                    if (listaProductos[i] != null && listaProductos[i].codigoProducto.equalsIgnoreCase(codigoEliminar)){
                        indiceEliminar = i;
                        break;
                    }
                }
                if (indiceEliminar == -1){
                    System.out.println("No existe ese codigo");
                    return false;
                }
                for (int j = indiceEliminar; j < totalProductos -1; j++){
                    listaProductos[j] = productos[j + 1];
                }
                listaProductos[totalProductos -1] = null;
                totalProductos--; //aqui porque lleva --
                System.out.println("Eliminado");
                return true;
            }
            public void mostrarInventario(){
                if(totalProductos == 0){
                    System.out.println("Inventario vacio");
                }
                    else{
                    System.out.println("--Inventario--");
                        for(int i = 0; totalProductos; i++){
                            if(listaProductos[i] != null){
                                listaProductos[i].mostrarProducto();
                            }
                        }
                    }
                }
             public Producto obtenerProducto(String codigoBuscar) {
        for (int i = 0; i < totalProductos; i++) {
            if (listaProductos[i] != null &&
                listaProductos[i].codigoProducto.equalsIgnoreCase(codigoBuscar)) {
                return listaProductos[i];
            }
        }
        return null;
    }
}
            
//CLASE ESTUDIANTE
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
