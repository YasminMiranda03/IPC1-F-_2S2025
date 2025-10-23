/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.controlador;

import usacshop.modelo.*;
import usacshop.modelo.Producto;
import usacshop.modelo.Venta;
import usacshop.vista.VendedorView;
import java.io.*;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author APROJUSA
 */
public class VendedorControlador {
    private VendedorView vista;
    private Producto[] productos = new Producto[100];   //ARREGLOOOOOOOOOOOOOO
    private int cantidadProductos;
    private final String PRODUCTOS_FILE = "productos.csv";
    private final String VENTAS_FILE = "ventas.csv";


    public VendedorControlador(VendedorView vista) {
        this.vista = vista;
        this.vista.setControlador(this);
        this.productos = new Producto[100]; //tamaño máximo del arreglo
        this.cantidadProductos = 0;
        cargarProductos();
        mostrarProductosEnTabla();
    }

    // Métodos para manejo de productos

    public void cargarProductos() {
        vista.limpiarTablaProductos(); // Limpia la tabla antes de recargar
        File archivo = new File("productos.txt");
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null && cantidadProductos < productos.length) {
                String[] datos = linea.split(",", 4);
                if (datos.length < 4) continue;

                String codigo = datos[0];
                String nombre = datos[1];
                String categoria = datos[2];
                String atributo = datos[3];

                Producto p = null;
                switch (categoria.toLowerCase()) {
                    case "tecnologia":
                        p = new Tecnologia(codigo, nombre, categoria, Integer.parseInt(atributo));
                        break;
                    case "alimento":
                        p = new Alimento(codigo, nombre, categoria, atributo);
                        break;
                    case "general":
                        p = new General(codigo, nombre, categoria, atributo);
                        break;
                }

                if (p != null) {
                    productos[cantidadProductos++] = p;   // guardar en arreglo
                    vista.agregarProductoATabla(p);      // agregar a la tabla
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al leer productos: " + e.getMessage());
        }
    }

    private void mostrarProductosEnTabla() {
        vista.limpiarTablaProductos();
        DefaultTableModel modelo = vista.getModeloTabla();
        for (int i = 0; i < cantidadProductos; i++) {
            modelo.addRow(new Object[]{
                productos[i].getCodigo(),
                productos[i].getNombre(),
                productos[i].getCategoria(),
                productos[i].getDetalle()
            });
        }
    }
    
    public void agregarProducto(Producto p) {
        if (cantidadProductos >= productos.length) {
            JOptionPane.showMessageDialog(vista, "Se alcanzó el límite de productos");
            return;
        }

        productos[cantidadProductos++] = p;
        guardarProductoEnArchivo(p);
        mostrarProductosEnTabla();
    }

    private void guardarProductoEnArchivo(Producto p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCTOS_FILE, true))) {
            bw.write(p.getCodigo() + "," + p.getNombre() + "," + p.getCategoria() + "," + p.getDetalle());
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar producto: " + e.getMessage());
        }
    }

    public void guardarProducto(Producto p) {
        try (FileWriter fw = new FileWriter(PRODUCTOS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(p.getCodigo() + "," + p.getNombre() + "," + p.getCategoria());
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar producto: " + e.getMessage());
        }
    }

    private double calcularTotalVentas() {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(VENTAS_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    total += Double.parseDouble(datos[2]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al calcular total de ventas: " + e.getMessage());
        }
        return total;
    }
    /** Registrar ingreso de stock de un producto */
    public void registrarIngresoStock(String codigoProducto, int cantidad, String usuario) {
        if (!productoExiste(codigoProducto)) {
            JOptionPane.showMessageDialog(vista, "El producto no existe: " + codigoProducto);
            return;
        }

        // Guardar movimiento en historial
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("historial.csv", true))) {
            String fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            bw.write(fechaHora + "," + usuario + "," + codigoProducto + "," + cantidad);
            bw.newLine();
            JOptionPane.showMessageDialog(vista, "Stock registrado correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al registrar ingreso de stock: " + e.getMessage());
        }
    }

    /** Cargar stock desde CSV: Formato: Código,Cantidad */
    public void cargarStockCSV(File archivoCSV, String usuario) {
        if (!archivoCSV.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 2);
                if (datos.length >= 2) {
                    String codigo = datos[0];
                    int cantidad = Integer.parseInt(datos[1]);
                    registrarIngresoStock(codigo, cantidad, usuario);
                }
            }
            JOptionPane.showMessageDialog(vista, "Carga de stock finalizada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al leer CSV: " + e.getMessage());
        }
    }

    /** Ver historial completo */
    public void verHistorial() {
        File archivo = new File("historial.csv");
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(vista, "No hay historial de movimientos");
            return;
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(vista, sb.toString(), "Historial de movimientos", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al leer historial: " + e.getMessage());
        }
    }

    /** Valida existencia de un producto por código */
    private boolean productoExiste(String codigo) {
        File archivo = new File("productos.txt");
        if (!archivo.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 4);
                if (datos[0].equals(codigo)) return true;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al validar producto: " + e.getMessage());
        }
        return false;
    }
    private Producto buscarProducto(String codigo) {
        for (int i = 0; i < cantidadProductos; i++) {
            if (productos[i].getCodigo().equals(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    public void actualizarTabla() {
        cargarProductos();
    }
    
}
