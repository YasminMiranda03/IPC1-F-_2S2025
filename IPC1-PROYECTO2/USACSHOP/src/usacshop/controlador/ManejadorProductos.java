/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.controlador;

import usacshop.modelo.Producto;

import java.io.*;

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
    
    //-----------PARA MANEJAR EL STOCK-----------
    // Aumentar stock de un producto
    public boolean aumentarStock(String codigo, int cantidad) {
        Producto p = buscarProductoPorCodigo(codigo);
        if (p != null) {
            p.setStock(p.getStock() + cantidad);
            return true;
        }
        return false;
    }

    // Disminuir stock de un producto (sin dejarlo negativo)
    public boolean disminuirStock(String codigo, int cantidad) {
        Producto p = buscarProductoPorCodigo(codigo);
        if (p != null) {
            if (p.getStock() >= cantidad) {
                p.setStock(p.getStock() - cantidad);
                return true;
            } else {
                System.out.println("No hay suficiente stock disponible para " + p.getNombre());
            }
        }
        return false;
    }

    // Verificar si hay stock disponible
    public boolean hayStockDisponible(String codigo, int cantidad) {
        Producto p = buscarProductoPorCodigo(codigo);
        return (p != null && p.getStock() >= cantidad);
    }
    public void cargarStockDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String codigo = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);

                    // Buscar el producto existente y asignarle el stock
                    Producto p = buscarProductoPorCodigo(codigo);
                    if (p != null) {
                        p.setStock(cantidad);
                    }
                }
            }
            System.out.println("Stock cargado correctamente desde " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de stock: " + e.getMessage());
        }
    }

    public void guardarStockEnArchivo(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int i = 0; i < contador; i++) {
                Producto p = productos[i];
                if (p != null) {
                    bw.write(p.getCodigo() + "," + p.getStock());
                    bw.newLine();
                }
            }
            System.out.println("Stock actualizado en archivo: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de stock: " + e.getMessage());
        }
    }
}
