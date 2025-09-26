/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica2;

import arenausac.controlador.ArenaUSAC;
import arenausac.controlador.Batalla;
import arenausac.controlador.Historial;
import arenausac.controlador.RegistroBatalla;
import arenausac.modelo.Personaje;
import arenausac.modelo.Estudiante;
import java.util.Scanner;

/**
 *
 * @author Katherin Yasmin
 */

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println("Bienvenido a ArenaUSAC");
        
        System.out.println("Ingrese los datos del estudiante: ");
        System.out.println("Nombre: ");
        String nombreEst = sc.nextLine();
        System.out.println("Carnet: ");
        String carneEst = sc.nextLine();
        
        Estudiante estudiante = new Estudiante(nombreEst, carneEst);
        
        ArenaUSAC arena = new ArenaUSAC(10);
        Historial historial = new Historial(20);
        
        int opcion;

        do {
            System.out.println("==== ArenaUSAC ====");
            System.out.println("1. Agregar personaje");
            System.out.println("2. Listar personajes");
            System.out.println("3. Buscar personaje por nombre");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Modificar personaje");
            System.out.println("6. Iniciar batalla");
            System.out.println("7. Mostrar historial de batallas");
            System.out.println("8. Guardar personajes en archivo");
            System.out.println("9. Cargar personajes desde archivo");
            System.out.println("10. Guardar historial en archivo");
            System.out.println("11. Mostrar datos del estudiante");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("== Agregar ==");
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Arma: ");
                    String arma = sc.nextLine();
                    System.out.print("HP entre 100-500: ");
                    int hp = sc.nextInt();
                    System.out.print("Ataque entre 10-100: ");
                    int ataque = sc.nextInt();
                    System.out.print("Velocidad entre 1-10: ");
                    int velocidad = sc.nextInt();
                    System.out.print("Agilidad entre 1-10: ");
                    int agilidad = sc.nextInt();
                    System.out.print("Defensa entre 1-50: ");
                    int defensa = sc.nextInt();

                    Personaje p = new Personaje(nombre, arma, hp, ataque, velocidad, agilidad, defensa);
                    arena.agregarPersonaje(p);
                    break;

                case 2:
                    System.out.println("== Listado ==");
                    arena.listarPersonajes();
                    break;

                case 3:
                    System.out.println("== Buscar ==");
                    System.out.print("Nombre: ");
                    String buscar = sc.nextLine();
                    Personaje encontrado = arena.buscarPorNombre(buscar);
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontro ese personaje.");
                    }
                    break;

                case 4:
                    System.out.println("== Eliminar ==");
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    arena.eliminarPersonaje(id);
                    break;

                case 5:
                    System.out.println("== Modificar ==");
                    System.out.print("ID: ");
                    int idMod = sc.nextInt();
                    sc.nextLine(); // limpiar buffer
                    System.out.print("Nueva arma: ");
                    String armaNueva = sc.nextLine();
                    System.out.print("Nuevo HP entre 100-500: ");
                    int hpNuevo = sc.nextInt();
                    System.out.print("Nuevo Ataque entre 10-100: ");
                    int atkNuevo = sc.nextInt();
                    System.out.print("Nueva Velocidad entre 1-10: ");
                    int velNueva = sc.nextInt();
                    System.out.print("Nueva Agilidad entre 1-10: ");
                    int agiNueva = sc.nextInt();
                    System.out.print("Nueva Defensa entre 1-50: ");
                    int defNueva = sc.nextInt();

                    arena.modificarPersonaje(idMod, armaNueva, hpNuevo, atkNuevo, velNueva, agiNueva, defNueva);
                    break;
                    
                case 6:
                    System.out.println("--Batalla--");
                    System.out.println("ID del primer personaje");
                    int id1 = sc.nextInt();
                    System.out.println("ID del segundo personaje: ");
                    int id2 = sc.nextInt();
                    
                    Personaje p1 = arena.buscarPorId(id1);
                    Personaje p2 = arena.buscarPorId(id2);
                    
                    if (p1 != null && p2 != null){
                        Batalla batalla = new Batalla(p1, p2, historial);
                        batalla.iniciar();
                    } else{
                        System.out.println("Alguno de los personajes no existe");
                    }
                    break;
                    
                case 7:
                    historial.mostrarHistorial();
                    break;
                    
                case 8:
                    arena.guardarPersonajes("personajes.txt");
                    break;
                    
                case 9:
                    arena.cargarPersonajes("personajes.txt");
                    break;
        
                case 10:
                    historial.guardarHistorial("historial.txt");
                    break;
                    
                case 11:
                    System.out.println(estudiante.toString());
                    break;
                    
                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }
    
}
