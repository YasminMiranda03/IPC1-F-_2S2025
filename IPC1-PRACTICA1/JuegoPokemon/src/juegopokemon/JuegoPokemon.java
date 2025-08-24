package juegopokemon;
import java.util.Scanner;   //para leer los datos
import java.time.LocalDateTime; //para el tiempo

//**Clase para representar al personaje*/
class Personaje{
    String nombre;
    String arma;
    String[] habilidades;      //vector
    private static int contadorID = 1;
    private int ID;
    int nivelPoder;
  
    //constructor sirve para que las variables no se confundan con los parametros
    public Personaje(String nombre, String arma, String[] habilidades, int ID, int nivelPoder) {
        this.nombre = nombre;
        this.arma = arma;
        this.habilidades = habilidades;
        this.ID = contadorID++;
        this.nivelPoder = nivelPoder;
    }
    //*Getters para la clase personaje*/
    public String getnombre(){
        return nombre;
    }
    public String getarma(){
        return arma;
    }
    public String[] gethabilidades(){
        return habilidades;
    }
    public int getID(){
        return ID;
    }
    public int getnivelPoder(){
        return nivelPoder;
    }
    //*Setters para la clase personaje*/
    public void setnombre(String nombre){
        this.nombre = nombre;
    }
    public void setarma(String arma){
        this.arma = arma;
    }
    public void sethablidades(String[] habilidades){
        this.habilidades = habilidades;
    }
    public void setnivelPoder(int nivelPoder){
        this.nivelPoder = nivelPoder;
    }
    @Override
    public String toString(){
        return "--Datos del personaje--" + 
                "\nNombre: " + nombre + 
                "\nArma: " + arma + 
                "\nHablilidades: " + habilidades + 
                "\nNivel de Poder: " + nivelPoder +
                "\nID: " + ID;
}
}
//*Clase para representar las peleas*/
class Pelea{
   Personaje personaje1;
   Personaje personaje2;
   LocalDateTime fechaHora; //atributo para la fecha y hora
   //*Constructor*/
   public Pelea(Personaje personaje1, Personaje personaje2, LocalDateTime fechaHora){
    this.personaje1 = personaje1;
    this.personaje2 = personaje2;
    this.fechaHora = fechaHora;
   }
   //*Getters para la clase Pelea*/
   public Personaje getpersonaje1(){
       return personaje1;
   }
   public Personaje getpersonaje2(){
       return personaje2;
   }
   public LocalDateTime getfechaHora(){
       return fechaHora;
   }
   
   //*Setters para la clase Pelea*/
   public void setpersonaje1(Personaje personaje1){
       this.personaje1 = personaje1;
   }
   public void setpersonaje2(Personaje personaje2){
       this.personaje2 = personaje2;
   }
    @Override
   public String toString(){
       return "Pelea entre " + personaje1.getnombre() + "vs" + personaje2.getnombre() + "Fecha y hora: " + fechaHora;
   }
 }

//*Clase para almacenar el historial de peleas*/
class HistorialPeleas{
    Pelea[] peleas;  //representacion de un vector
    int cantidadPeleas;
    //*Constructor*/
    public HistorialPeleas(){    
        this.peleas = new Pelea[10];    //10 es la cantidad maxima de peleas
        this.cantidadPeleas = 0;    //aqui inicia el contador de cantidadPeleas en 0
    }
    //*Getters par HistorialPeleas
    public void registroPelea(Pelea nuevaPelea){
        if (cantidadPeleas < peleas.length){
            this.peleas[cantidadPeleas] = nuevaPelea;
            this.cantidadPeleas++;
            System.out.println("Se registro una nueva pelea");
        }
        else{
            System.out.println("El historial esta lleno");
        }
    }
    public void mostrarHistorial(){
        if (cantidadPeleas == 0){
            System.out.println("No hay registro de peleas");
        }
        else
            System.out.println("El historial de peleas es: ");
        for (int i = 0; i < cantidadPeleas; i++){
            System.out.println(peleas[i]);
        }
        }
    }
 
class HistorialPersonajes{
    
}
//*Clase para almacenar el registro del estudiante*/
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

//*Clase del menú*/
class Menu{
    public static void mostrarMenu(){
    System.out.println("----MENU PRINCIPAL---");
    System.out.println("1. Agregar personaje");
    System.out.println("2. Modificar personaje");
    System.out.println("3. Eliminar personaje");
    System.out.println("4. Datos del personaje");
    System.out.println("5. Listado de personajes");
    System.out.println("6. Historial de peleas");
    System.out.println("7. Historial de personajes");
    System.out.println("8. Ver datos del estudiante");
    System.out.println("9. Salir");
    }
}

//*Clase Principal*/
public class JuegoPokemon{
static final int NUM_PERSONAJES = 25;
static Personaje[] personajes = new Personaje[NUM_PERSONAJES];
static Scanner sc = new Scanner(System.in);
static Estudiante estudiante;   //declaracion de estudiante
static HistorialPeleas historial = new HistorialPeleas();

public static void main(String[] args){
    System.out.print("Ingrese su nombre: ");
    String nombre = sc.nextLine();
    System.out.print("Ingrese su carnet: ");
    int carnet = sc.nextInt();
    sc.nextLine();  //limpia el buffer
    estudiante = new Estudiante(nombre, carnet);
    
    Menu menuPrincipal = new Menu();
    int opcion;
    do {
        menuPrincipal.mostrarMenu();
        opcion = LeerEntero("Seleccione una opcion: ");
        switch(opcion) {
            case 1:
                agregarPersonaje();
                break;
            case 2:
                System.out.println("Usted va a modificar un personaje");
                modificarPersonaje();
                break;
            case 3:
                System.out.println("Usted va a eliminar un personaje");
                eliminarPersonaje();
                break;
            case 4:
                System.out.println("Usted va a agregar datos de un personaje");
                datosPersonaje();
                break;
            case 5:
                System.out.println("Usted va a ver el listado de personajes");
                listadoPersonajes();
                break;
            case 6:
                System.out.println("--Historial de peleas--");
                registrarPelea();
                break;
            case 7:
                System.out.println("--Historial de personajes--");
                historialPeleas();
                break;
            case 8:
                System.out.println("--Datos del estudiante--");
                datosEstudiantes();
                break;
            case 9:
                System.out.println("--Saliendo--");
                break;
            default:
                System.out.println("opción no valida");       
        }
        System.out.println();
    } while (opcion != 9);
}
//**Métodos de lectura*/
static String leerTexto(String mensaje){
    System.out.print(mensaje);
    return sc.nextLine();
}
static int LeerEntero(String mensaje){
    System.out.print(mensaje);
    return sc.nextInt();
}
    
   
//**Funciones del menú*/
static void agregarPersonaje(){
    String nombre = leerTexto("Ingrese el nombre del personaje: ");
    sc.nextLine();
    String arma = leerTexto("Ingrese el arma: ");
    int ID = LeerEntero("Ingrese el ID del personaje: ");
    int nivelPoder = LeerEntero("Ingrese el nivel de poder entre 1-100: ");
    sc.nextLine();
    String[] habilidades = new String[3];
    for (int i = 0; i < habilidades.length; i++){
        habilidades[i] = leerTexto("Ingrese la habilidad " + (i + 1));
    }
    Personaje nuevo = new Personaje(nombre, arma, habilidades, ID, nivelPoder);
        for(int i = 0; i < personajes.length; i++){
            if (personajes[i] == null){
                personajes[i] = nuevo;
                System.out.println("El personaje ha sido agregado");
                return;
            }
        }
        System.out.println("No se pueden agregar mas personajes");
}
static void modificarPersonaje(){
    int ID = LeerEntero("Ingrese el ID del personaje: ");
    for (Personaje p : personajes){
        if (p != null && p.getID() == ID){
            sc.nextLine();
            p.setarma(leerTexto("Nueva arma: "));
            p.setnivelPoder(LeerEntero("Nuevo nivel de poder: "));
            System.out.println("El personaje ha sido modificado");
            return;
        }
    }
    System.out.println("No se encontro el personaje");
}
static void eliminarPersonaje(){
    int ID = LeerEntero("Ingrese el ID del personaje a eliminar: ");
    for (int i = 0; i < personajes.length; i++){
      if  (personajes[i] != null && personajes[i].getID() == ID){
          personajes[i] = null;
          System.out.println("Personaje eliminado");
          return;
      }
    }
    System.out.println("No se encontro el personaje");
}
static void datosPersonaje(){
    int ID = LeerEntero("Ingrese el ID del personaje: ");
    for (Personaje p : personajes){
        if (p != null && p.getID() == ID){
            System.out.println(p);
            return;
        }
    }
    System.out.println("No se encontro el personaje");
}
static void listadoPersonajes(){
    boolean existencia = false; //verifica existencia de los personajes
    for (Personaje p : personajes){
        if (p != null){
            System.out.println(p);
            existencia = true;
        }
    }
    if (!existencia)System.out.println("NO hay personajes registrados");
}
static void registrarPelea(){
    int ID1 = LeerEntero("Ingrese el ID del personaje 1: ");
    int ID2 = LeerEntero("Ingrese el ID del personaje 2: ");
    Personaje p1 = buscarPersonajePorID(ID1);
    Personaje p2 = buscarPersonajePorID(ID2);
    if (p1 == null || p2 == null) {
            System.out.println("Uno o ambos personajes no existen.");
            return;
        }
        Pelea nuevaPelea = new Pelea(p1, p2, LocalDateTime.now());
        historial.registrarPelea(nuevaPelea);
    }

    static void historialPeleas() {
        historial.mostrarHistorial();
    }

    static void datosEstudiantes() {
        System.out.println(estudiante.toString());
    }
    //*Metodo para buscar*/
    static Personaje buscarPersonajePorID(int ID){
        for (Personaje p : personajes){
            if (p != null && p.getID() == ID) return p;
        }
        return null;
    }
}