/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventariotienda;

/**
 *
 * @author APROJUSA
 */
import java.util.Scanner;
import java.io.FileWritter;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//CLASE PRODUCTO
class Producto {
    public String nombreProducto;
    public String categoriaProducto;
    public int cantidadProducto;
    public int codigoProducto;
    public double precioProducto;
    
    public Producto(String nombre, String categoria, int cantidad, int codigo, double precio){
        nombreProducto = nombre;
        categoriaProducto = categoria;
        codigoProducto = codigo;
        cantidadProducto = cantidad;
        precioProducto = precio;
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
    public Producto[] listaProductos = new Producto[50];
            public int totalProductos = 0;
            
            public boolean agregarProducto(Producto nuevoProducto){
                if (totalProductos >= 50){
                    System.out.println("Inventario lleno");
                    return false;
                }
                 //verificacion de duplicado 
                int i = 0;
                while (i < totalProductos) {
                    Producto productoActual = listaProductos[i];
                    if (productoActual != null) {
                        if (productoActual.codigoProducto != null) {
                            if (productoActual.codigoProducto.equalsIgnoreCase(nuevoProducto.codigoProducto)) {
                                System.out.println("Ya existe un producto con ese codigo");
                                return false;
                            }
                        }
                    }
                    i = i++;
                }
            
            listaProductos[totalProductos] = nuevoProducto;
            totalProductos++;
            System.out.println("Producto agregado");
            return true;
            }
            
            public void buscarProducto(String criterioBusqueda){ //
                boolean encontrado = false;
                int i = 0;
                while (i < totalProductos) {
                    Producto productoActual = listaProductos[i];
                    if (productoActual != null) {
                        if (productoActual.codigoProducto != null) {
                            if (productoActual.codigoProducto.equalsIgnoreCase(criterioBusqueda)) {
                                productoActual.mostrarProducto();
                                encontrado = true;
                    }
                }
                if (productoActual.nombreProducto != null) {
                    if (productoActual.nombreProducto.equalsIgnoreCase(criterioBusqueda)) {
                        productoActual.mostrarProducto();
                        encontrado = true;
                    }
                }
                if (productoActual.categoriaProducto != null) {
                    if (productoActual.categoriaProducto.equalsIgnoreCase(criterioBusqueda)) {
                        productoActual.mostrarProducto();
                        encontrado = true;
                    }
                }
            }
            i = i++;
        }
        if (encontrado == false) {
            System.out.println("No se encontro nada");
                }
            }
            public boolean eliminarProducto(String codigoEliminar){
                int indiceEliminar = -1;
                int i = 0;
                while (i < totalProductos){
                    Producto productoActual = listaProductos[i];
                    if (productoActual != null){
                        if (productoActual.codigoProducto != null){
                            if(productoActual.codigoProducto.equalsIgnoreCase(codigoEliminar)){
                                indiceEliminar = i;
                                break;
                            }
                        }
                    }
                    i = i++;
                }
                if (indiceEliminar == -1){
                    System.out.println("No existe ese codigo");
                    return false;
                }
                int j = indiceEliminar;
                while (j < totalProductos -1){
                    listaProductos[j] = listaProductos[j + 1];
                    j = j++;
                }
                listaProductos[totalProductos -1] = null;
                totalProductos = totalProductos -1;
                System.out.println("Eliminado");
                return true;
            }
            public void mostrarInventario(){
                if(totalProductos == 0){
                    System.out.println("Inventario vacio");
                } else{
                    int i = 0;
                    System.out.println("--Inventario--");
                        while ( i < totalProductos){
                            if (listaProductos[i] != null){
                                listaProductos[i].mostrarProducto();
                            }
                            i = i++;
                        }
                    }
                }
            
             public Producto obtenerProducto(String codigoBuscar){
                 int i = 0;
                 while (i < totalProductos){
                     Producto productoActual = listaProductos[i];
                     if (productoActual != null){
                         if (productoActual.codigoProducto != null){
                             if (productoActual.codigoProducto.equalsIgnoreCase(codigoBuscar)){
                                 return productoActual;
                             }
                         }
                     }
                     i = i++;
                 }
                 return null;
             }
}
//CLASE VENTA
class Venta{
    public static void registrarVenta(Inventario inventario, String codigoVenta,int cantidadVenta, String usuario){
        Producto productoVendido = inventario.obtenerProducto(codigoVenta);
        if (productoVendido == null){
            System.out.println("No existe el producto");
            Bitacora.registrarAccion("Registrar venta", "Fallida", usuario);
            return;
        } else {
            if (productoVendido.cantidadProducto < cantidadVenta){
                System.out.println("No hay suficiente stock");
                Bitacora.registrarAccion("Registrar venta", "Fallida", usuario);
                return;
            }else {
               productoVendido.cantidadProducto = productoVendido.cantidadProducto - cantidadVenta;
                double totalVenta = productoVendido.precioProducto * cantidadVenta;

                LocalDateTime fechaHora = LocalDateTime.now();
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaTexto = fechaHora.format(formatoFecha); 
                
                try {
                    FileWritter archivoVentas = new FileWritter("ventas.txt", true);
                    archivoVentas.write("Producto: " + productoVendido.nombreProducto + " | Codigo: " + productoVendido.codigoProducto +
                                        " | Cantidad: " + cantidadVenta + " | Total: Q" + totalVenta +
                                        " | Fecha: " + fechaTexto + "\n");
                    archivoVentas.close();
                    System.out.println("Venta registrada!");
                    Bitacora.registrarAccion("Registrar venta", "Correcta", usuario);
                }catch (Exception e){
                System.out.println("Error guardando en venta");
                Bitacora.registrarAccion("Registrar venta", "error", usuario);
                }
            }
        }
    }
}
//CLASE BITACORA
class Bitacora{
    public static void registrarAccion(String accion, String resultado, String usuario){
        try{
            LocalDateTime fechaHora = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_ss");
            String nombreArchivo = formato.format(fechaHora) + "_Venta.pdf";
            
            Document doc = new Document();
            try{
                PdfWriter.get.Instance(doc, new FileOutputStrea,(nombreArchivo));
                doc.open();
                doc.add(new Paragraph("Reporte de ventas\n\n"));
                
                try {
                    scanner lector = new Scanner(new File("ventas.txt"));
                    while (lector.hasNextLine()){
                        String linea = lector.nextLine();
                        doc.add(new Paragraph(linea));
                    }
                    lector.close();
                }
                catch (Exception e){
                    doc.add(new Paragraph("No hay ventas registradas"));
                }
                System.out.println("PDF de ventas creado" + nombreArchivo);
                Bitacora.registrarAccion("Generar reporte de ventas", "Correcta", usuario);
            }
            catch (Exception e){
                System.out.println("No se pudo generar el PDf de ventas");
                Bitacora.registrarAccion("Generar reporde de ventas", "Error", usuario);
            }
            finally{
                doc.close();
            }
        }
    }
}

//CLASE REPORTE DE PDF
class ReportePDF{
     public static void generarReporteStock(Inventario inventario, String usuario) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String nombreArchivo = formato.format(fechaHora) + "_Stock.pdf";
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(new File(nombreArchivo)));
            doc.open();
            doc.add(new Paragraph("Reporte de Stock\n\n"));
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Categoria");
            tabla.addCell("Precio");
            tabla.addCell("Cantidad");

            int i = 0;
            while (i < inventario.totalProductos) {
                Producto p = inventario.listaProductos[i];
                if (p != null) {
                    tabla.addCell(p.codigoProducto);
                    tabla.addCell(p.nombreProducto);
                    tabla.addCell(p.categoriaProducto);
                    tabla.addCell("Q" + p.precioProducto);
                    tabla.addCell(String.valueOf(p.cantidadProducto));
                }
                i = i + 1;
            }

            doc.add(tabla);
            System.out.println("PDF stock creado: " + nombreArchivo);
            Bitacora.registrarAccion("Generar reporte stock", "Correcta", usuario);
        } catch (Exception e) {
            System.out.println("No se pudo generar el PDF de stock");
            Bitacora.registrarAccion("Generar reporte stock", "Error", usuario);
        } finally {
            try {
                doc.close();
            } catch (Exception e) {}
        }
    }

    public static void generarReporteVentas(String usuario) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String nombreArchivo = formato.format(fechaHora) + "_Venta.pdf";
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();
            doc.add(new Paragraph("Reporte de Ventas\n\n"));
            try {
                Scanner lector = new Scanner(new File("ventas.txt"));
                while (lector.hasNextLine()) {
                    String linea = lector.nextLine();
                    doc.add(new Paragraph(linea));
                }
                lector.close();
            } catch (Exception e) {
                doc.add(new Paragraph("No hay ventas registradas"));
            }
            System.out.println("PDF ventas creado: " + nombreArchivo);
            Bitacora.registrarAccion("Generar reporte ventas", "Correcta", usuario);
        } catch (Exception e) {
            System.out.println("No se pudo generar el PDF de ventas");
            Bitacora.registrarAccion("Generar reporte ventas", "Error", usuario);
        } finally {
            try {
                doc.close();
            } catch (Exception e) {}
        }
    }
}          
//CLASE ESTUDIANTE
class Estudiante{
    String nombreEstudiante;
    int carnet;
    
    public Estudiante(String nombre, int carnet){
        nombreEstudiante = nombre;
        carnet = carnet;
    }
    public void mostrarDatos(){
        System.out.println("--Datos del estudiante--");
        System.out.println("NOmbre: " + nombreEstudiante);
        System.out.println("Carnet: " + carnet);
        System.out.println("Sección F");
    }
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
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        Estudiante estudiante = new Estudiante();
        
        System.out.println("Ingrese su usuario: ");
        String nombreUsuario = scanner.nextLine();
        
        //Productos ya cargados
        Producto blusaMorada = new Producto("Blusa", "Ropa de mujer", 150.00, 10, "C001");
        Producto pantalonNegro = new Producto("Pantalon negro", "Pantalon de mujer", 200, 20,"C002");
        Producto chaquetaCuero = new Producto("Chaqueta de cuero", "Chaqueta", 500.50, 2,"C003" );
        Producto vestidoFlores = new Producto("Vestido de Flores", "Vestido", 190.25, 15, "C004");
        
        inventario.agregarProducto(blusaMorada);
        inventario.agregarProducto(pantalonNegro);
        inventario.agregarProducto(chaquetaCuero);
        inventario.agregarProducto(vestidoFlores);
        
        //comienza el menu
        int opcion = 0;
        do{
            System.out.println("--Menú--");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Registrar venta");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Generar reporte stock PDF");
            System.out.println("7. Generar reporte de ventas PDF");
            System.out.println("8. Mostrar bitacora");
            System.out.println("9. Ver datos estudiante");
            System.out.println("10. Salir");
            System.out.println("Opcion: ");
            
            try {
                opcion = Integrer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Opcion invalida");
                opcion = 0;
            }
            while (opcion){
                case 1:
                System.out.println("Nombre: ");
                String nombre = scanner.nextLine();
                
                System.out.println("Categoria: ");
                String categoria = scanner.nextLine();
                
                System.out.println("Precio: ");
                double precio = 0;
                    try{
                        precio = Double.parseDouble(scanner.nextLine());
                    } catch (Exception e){
                        precio = 0;
                    }
                System.out.println("Cantidad: ");
                int cantidad = 0;
                    try{
                        precio = Double.parseDouble(scanner.nextLine());
                    } catch (Exception e){
                        precio = 0;
                    }
                    System.out.println("Código: ");
                    String codigo = scanner.nextLine();
                    
                    Producto nuevoProducto = new Producto(nombre, categoria, precio, cantidad, codigo);
                    boolean agregado = inventario.agregarProducto(nuevoProducto);
                    if (agregado == true){
                        Bitacora.registrarAccion("Agregar producto", "Correcta", nombreUsuario);
                    } else {
                        Bitacora.registrarAccion("Agregar producto", "Fallida", nombreUsuario);
                        } 
                    break;
                    
                case 2:
                System.out.println("Criterio de busqueda; ");
                String criterioBusqueda = scanner.nextLine();
                Inventario.buscarProducto(criterioBusqueda);
                Bitacora.registrarAccion("Buscar producto", "Correcta", nombreUsuario);
                break;
                
                case 3:
                System.out.println("Codigo a eliminar: ");
                String codigoEliminar = scanner.nextLine();
                boolean eliminado = inventario.elimincarProducto(codigoEliminar);
                if (eliminado == true){
                    Bitacora.registrarAccion("Eliminar producto", "Correcta", nombreUsuario);
                } else{
                    Bitacora.registrarAccion("Eliminar producto", "Fallida", nombreUsuario);
                }
                break;
                
                case 4:
                System.out.println("Codigo del producto; ");
                String codigoVenta = scanner.nextLine();
                System.out.println("Cantidad a vender: ");
                int cantidadVenta = 0;
                try {
                    cantidadVenta = Integer.parseInt(scanner.nextLine());
                } catch (Exception e){
                    cantidadVenta = 0;
                }
                Venta.registrarVenta(inventario, codigoVenta, cantidadVenta, nombreUsuario);
                break;
                
                case 5:
                    inventario.mostrarInventario();
                    Bitacora.registrarAccion("Mostrar inventario", "Correcta", nombreUsuario);
                    break;
                    
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
            }
        }
    }
    
}
