/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.controlador;

import usacshop.modelo.Producto;

/**
 *
 * @author Katherin Yasmin
 */
public class ManejadorProductos {
    private Producto[] productos;
    private int contador;

    // Constructor: define el tamaño máximo del arreglo
    public ManejadorProductos(int maxProductos) {
        productos = new Producto[maxProductos];
        contador = 0;
    }

    // Agregar un producto nuevo
    public void agregarProducto(Producto p) {
        if (contador < productos.length) {
            productos[contador] = p;
            contador++;
        } else {
            System.out.println("No se pueden agregar más productos (arreglo lleno).");
        }
    }

    // Buscar un producto por su código
    public Producto buscarProductoPorCodigo(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equals(codigo)) {
                return productos[i];
            }
        }
        return null; // no se encontró
    }

    // Obtener todos los productos registrados
    public Producto[] getProductos() {
        return productos;
    }

    // Cantidad actual de productos
    public int getCantidad() {
        return contador;
    }

    // Eliminar producto por código (opcional)
    public boolean eliminarProducto(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equals(codigo)) {
                // mover todos los siguientes una posición atrás
                for (int j = i; j < contador - 1; j++) {
                    productos[j] = productos[j + 1];
                }
                productos[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    // Mostrar todos los productos (para depuración en consola)
    public void mostrarProductos() {
        for (int i = 0; i < contador; i++) {
            Producto p = productos[i];
            System.out.println(p.getCodigo() + " - " + p.getNombre() + " - " +
                               p.getCategoria() + " - " + p.getDetalle() +
                               " - Stock: " + p.getStock());
        }
    }
}
