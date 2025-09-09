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
                    j = j++
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
                            i = i++
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
                     i = i++
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
