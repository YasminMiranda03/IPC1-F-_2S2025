/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventariotienda;

/**
 *
 * @author APROJUSA
 */
import java.util.Scanner;   //para leer los datos que se ingresn en el teclado
import java.io.FileWriter;  //clase para escribir en archivos
import java.io.File;    //clase para representar archivos en el sistema
import java.io.FileOutputStream;
import java.time.LocalDateTime; //para la fecha y hora
import java.time.format.DateTimeFormatter;

//libreria para el pdf
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//-----------------------------------------------------------------

//CLASE PRODUCTO
class Producto {
    //atributos del producto
    public String nombreProducto;
    public String categoriaProducto;
    public int cantidadProducto;
    public String codigoProducto;
    public double precioProducto;
    
    //constructor con datos
    public Producto(String nombre, String categoria, double precio, int cantidad, String codigo){
        nombreProducto = nombre;    //asigna el nombre recibido
        categoriaProducto = categoria;  //adina la categoria recibida
        precioProducto = precio;
        cantidadProducto = cantidad;
        codigoProducto = codigo;
    }
    
    //metodo para mostrar los datos de un producto
    public void mostrarProducto(){
        System.out.println("Nombre:" + nombreProducto); //imprime los datos en pantalla
        System.out.println("Categoria:" + categoriaProducto);
        System.out.println("Código:" + codigoProducto);
        System.out.println("Cantidad:" + cantidadProducto);
        System.out.println("Precio en Q:" + precioProducto);
    }
}
//------------------------------------------------------------------------------------

//CLASE INVENTARIO
class Inventario{
    //aqui guardo los productos
    public Producto[] listaProductos = new Producto[50];    //arreglo de 50 espacios
    public int totalProductos = 0;  //contador que indica cuantos productos se han registrado
    
    //metodo para agregar un producto
            public boolean agregarProducto(Producto nuevoProducto){
                if (totalProductos >= 50){      //verifica si el inventario ya esta lleno
                    System.out.println("Inventario lleno");
                    return false;
                }
                 //verificacion de duplicado para ver si el codigo no se repite(recorrido)
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
                    i++;
                }
            listaProductos[totalProductos] = nuevoProducto; //si no esta repetido entonces se agrega
            totalProductos++;   // se incrementa el contador para reflejar el nuevo total de productos
            System.out.println("Producto agregado");
            return true;    // retorno true para indicar que la inserción fue exitosa
            }
            
            //metodo para buscar un producto por codigo nombre o categoria
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
            i++;
        }
        if (encontrado == false) {
            System.out.println("No se encontro nada");
                }
            }
            
            //metodo para eliminar un producto por codigo
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
                    i++;
                }
                if (indiceEliminar == -1){
                    System.out.println("No existe ese codigo");
                    return false;
                }
                //mover los productos para no dejar espacios
                int j = indiceEliminar;
                while (j < totalProductos -1){
                    listaProductos[j] = listaProductos[j + 1];
                    j++;
                }
                listaProductos[totalProductos -1] = null;   //deja en null la ultima posicion porque quedo duplicada porque se desplazo 
                totalProductos = totalProductos -1; //decrementa el contador total de productos porque se elimino uno 
                System.out.println("Eliminado");
                return true;
            }
            
            //aqui se muestran todos los productos
            public void mostrarInventario(){    //metodo que imprime todos los productos registrados
                if(totalProductos == 0){
                    System.out.println("Inventario vacio");
                } else{
                    int i = 0;  //indice de recorrido inicializado en 0
                    System.out.println("--Inventario--");
                        while ( i < totalProductos){    //recorre todas las posiciones ocupadas
                            if (listaProductos[i] != null){
                                listaProductos[i].mostrarProducto();    // llamar al método mostrarProducto() del objeto para imprimir sus datos
                            }
                            i++;    //incrementa el indice para avanzar al siguiente producto
                        }
                    }
                }
            
            //obtener un producto para usarlo en las ventas
             public Producto obtenerProducto(String codigoBuscar){
                 int i = 0; //indice inicial para el recorrido
                 while (i < totalProductos){    //recorre todas las posiciones
                     Producto productoActual = listaProductos[i];   //obtiene el producto en la posicion i
                     if (productoActual != null){   //comprueba que no sea null antes de acceder 
                         if (productoActual.codigoProducto != null){    //comprueba que el codigo no sea null
                             if (productoActual.codigoProducto.equalsIgnoreCase(codigoBuscar)){ //compara codigos sin distinguir entre mayusculas y minusculas
                                 return productoActual; //si coincide devuelve la referencia al producto encontrado
                             }
                         }
                     }
                     i++;   //avanza al siguiente indice
                 }
                 return null;   //si no se encuentra
             }
}
//--------------------------------------------------------------------------------------------------------

//CLASE VENTA
class Venta{
    //metodo estatico para registrar una venta
    public static void registrarVenta(Inventario inventario, String codigoVenta,int cantidadVenta, String usuario){
        Producto productoVendido = inventario.obtenerProducto(codigoVenta);
        if (productoVendido == null){
            System.out.println("No existe el producto");
            Bitacora.registrarAccion("Registrar venta", "Fallida", usuario);
            return;
        } else {
            //aqui se comienza a verificar el stock
            if (productoVendido.cantidadProducto < cantidadVenta){
                System.out.println("No hay suficiente stock");
                Bitacora.registrarAccion("Registrar venta", "Fallida", usuario);
                return;
            }else {
                //restar al stock
                productoVendido.cantidadProducto = productoVendido.cantidadProducto - cantidadVenta;
                double totalVenta = productoVendido.precioProducto * cantidadVenta;
                
                //aqui se obtiene la fecha y hora
                LocalDateTime fechaHora = LocalDateTime.now();
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaTexto = fechaHora.format(formatoFecha); 
                
                //aqui se guarda en el archivo
                try {
                    FileWriter archivoVentas = new FileWriter("ventas.txt", true);
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
//----------------------------------------------------------------------------------

//CLASE BITACORA
class Bitacora{
    //registrar una accion en un archivo
    public static void registrarAccion(String accion, String resultado, String usuario) {
        try {
            LocalDateTime fechaHora = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaTexto = fechaHora.format(formatoFecha);
            FileWriter archivo = new FileWriter("bitacora.txt", true);
            archivo.write("Usuario: " + usuario + " | Accion: " + accion + " | Resultado: " + resultado + " | Fecha: " + fechaTexto + "\n");
            archivo.close();
        } catch (Exception e) {
            System.out.println("No se pudo escribir en bitacora");
        }
    }

    // mostrar lo que hay en la bitacora
    public static void mostrarBitacora() {
        try {
            Scanner lector = new Scanner(new File("bitacora.txt"));
            System.out.println("===== BITACORA =====");
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                System.out.println(linea);
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("No hay bitacora o no se pudo leer");
        }
    }
}
//----------------------------------------------------------------------------------

//CLASE REPORTE DE PDF
class ReportePDF{
    //generar el reporte en pdf del stock
     public static void generarReporteStock(Inventario inventario, String usuario) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String nombreArchivo = formato.format(fechaHora) + "_Stock.pdf";
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(new File(nombreArchivo)));
            doc.open();
            doc.add(new Paragraph("Reporte de Stock\n\n"));
            
            //aqui se crea la tabla con 5 columnas
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Categoria");
            tabla.addCell("Precio");
            tabla.addCell("Cantidad");
            
            //aqui recorro todos los productos para ponerlos en la tabla
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
                i++;
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
     
     //generara el reporte de ventas 
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
//------------------------------------------------------------------------------------------------

//CLASE ESTUDIANTE
class Estudiante{
    String nombreEstudiante;
    String carnet;
    
    // constructor vacio (para que funcione Estudiante e = new Estudiante(); )
    public Estudiante(){
        
    }
    //constructor ahora si con parametros
    public Estudiante(String nombre, String carnet){
        nombreEstudiante = nombre;
        this.carnet = carnet;
    }
    
    //mostrar los datos
    public void mostrarDatos(){
        System.out.println("--Datos del estudiante--");
        System.out.println("NOmbre: " + nombreEstudiante);
        System.out.println("Carnet: " + carnet);
        System.out.println("Sección F");
    }
}
//-------------------------------------------------------------------


//clase (main) principal
public class InventarioTIenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);   //objeto scanner para leer los datos que se ingresan en el teclado
        Inventario inventario = new Inventario();
        String nombreUsuario;   
        Estudiante estudiante;
        
        System.out.println("Ingrese su usuario: ");
        nombreUsuario = scanner.nextLine();
        
        //pedir los datos del estudiante
        System.out.println("Ingrese su nombre: ");
        String nombreEstudiante = scanner.nextLine();
        
        System.out.println("Ingrese su carnet: ");
        String carnet = scanner.nextLine();
        
        //crear objeto estudiante con los datos ingresados
        estudiante = new Estudiante(nombreEstudiante, carnet);
        
        
        //Productos ya cargados
        Producto blusaMorada = new Producto("Blusa", "Ropa de mujer", 150.00, 10, "C001");
        Producto pantalonNegro = new Producto("Pantalon negro", "Pantalon de mujer", 200, 20,"C002");
        Producto chaquetaCuero = new Producto("Chaqueta de cuero", "Chaqueta", 500.50, 2,"C003" );
        Producto vestidoFlores = new Producto("Vestido de Flores", "Vestido", 190.25, 15, "C004");
        
        inventario.agregarProducto(blusaMorada);
        inventario.agregarProducto(pantalonNegro);
        inventario.agregarProducto(chaquetaCuero);
        inventario.agregarProducto(vestidoFlores);
        //--------------------------------------------
        //comienza el menu
        int opcion = 0;
        do{
            System.out.println("--Menu--");
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
            System.out.println("Seleccione una opcion: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Opcion invalida");
                opcion = 0;
            }
            
            //se comienzan a ejecutar las opciones que se elijan
            switch (opcion){
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
                inventario.buscarProducto(criterioBusqueda);
                Bitacora.registrarAccion("Buscar producto", "Correcta", nombreUsuario);
                break;
                
                case 3:
                System.out.println("Codigo a eliminar: ");
                String codigoEliminar = scanner.nextLine();
                boolean eliminado = inventario.eliminarProducto(codigoEliminar);
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
                    ReportePDF.generarReporteStock(inventario, nombreUsuario);
                    break;
                    
                case 7:
                    ReportePDF.generarReporteVentas(nombreUsuario);
                    break;
                    
                case 8:
                    Bitacora.mostrarBitacora();
                    break;
                    
                case 9:
                    estudiante.mostrarDatos();
                    Bitacora.registrarAccion("Ver datos del estudiante", "Correcta", nombreUsuario);
                    break;
                    
                case 10:
                    System.out.println("Adios");
                    break;
            }
        } while (opcion != 10);
        scanner.close();
    }
    
}
