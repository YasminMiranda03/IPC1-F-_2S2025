/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usacshop.controlador;

import java.io.*;

/**
 *
 * @author Katherin Yasmin
 */
public class ClienteControlador {
    private static final String RUTA_CLIENTES = "clientes.txt";
    public static String buscarPorCodigo(String codigoBuscado){
        try (BufferedReader br = new BufferedReader (new FileReader(RUTA_CLIENTES))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",", -1);
                if (partes.length >= 4 && partes[0].equals(codigoBuscado)){
                    return linea;
                }
            }
        } catch (IOException e){
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }
    
    public static String buscarPorNombre(String nombreBuscado){
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CLIENTES))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",", -1);
                if (partes.length >= 4 && partes[1].equalsIgnoreCase(nombreBuscado)){
                    return linea;
                }
            }
        } catch (IOException e){
            System.out.println("Error al buscar al cliente" + e.getMessage());
        }
        return null;
    }
    
    public static String listarClientes(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CLIENTES))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (!linea.trim().isEmpty()){
                    sb.append(linea).append("\n");
                }
            }
        } catch (IOException e){
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return sb.toString();
    }
    
    //para eliminar los clientes por codigo
    public static boolean eliminarCliente(String codigoEliminar){
        File archivo = new File(RUTA_CLIENTES);
        File temp = new File("clientes_temp.txt");
        boolean eliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp))){
            String linea;
            while ((linea = br.readLine()) != null){
                if(linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",", -1);
                if (partes.length >= 4 && partes[0].equals(codigoEliminar)){
                    eliminado = true;
                    continue;
                }
                bw.write(linea);
                bw.newLine();
            }
            
        } catch (IOException e){
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            
        }
        if (eliminado){
            if(archivo.delete()){
                temp.renameTo(archivo);
            }
        } else{
            temp.delete();
        }
        return eliminado;
    }
    
    public static boolean actualizarCliente(String codigo, String nuevoNombre, String nuevoGenero, String nuevaContrasena){
        File archivo = new File(RUTA_CLIENTES);
        File temp = new File("clientes_temp.txt");
        boolean actualizado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",", -1);

                if (partes.length >= 4 && partes[0].equals(codigo)) {
                    bw.write(codigo + "," + nuevoNombre + "," + nuevoGenero + "," + nuevaContrasena);
                    actualizado = true;
                } else {
                    bw.write(linea);
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }

        // Reemplazar archivo si se actualizó correctamente
        if (actualizado) {
            if (archivo.delete()) {
                temp.renameTo(archivo);
            }
        } else {
            temp.delete();
        }

        return actualizado;
    }   
}
