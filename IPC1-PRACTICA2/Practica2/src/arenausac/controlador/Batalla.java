/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;

import arenausac.modelo.Personaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author APROJUSA
 */
public class Batalla {
    private Personaje p1;
    private Personaje p2;
    private boolean enCurso;
    
    public Batalla(Personaje p1, Personaje p2){
        this.p1 = p1;
        this.p2 = p2;
        this.enCurso = true;
    }
    
   public void iniciar() {
        System.out.println("=== BATALLA ===");
        System.out.println(p1.getNombre() + " VS " + p2.getNombre());

        // Hilo para el primer personaje
        Thread hilo1 = new Thread(new Luchador(p1, p2));
        // Hilo para el segundo personaje
        Thread hilo2 = new Thread(new Luchador(p2, p1));

        hilo1.start();
        hilo2.start();
    }

    // Clase interna que representa el comportamiento de un luchador
    class Luchador implements Runnable {
        private Personaje atacante;
        private Personaje defensor;

        public Luchador(Personaje atacante, Personaje defensor) {
            this.atacante = atacante;
            this.defensor = defensor;
        }

        @Override
        public void run() {
            while (enCurso && atacante.getHp() > 0 && defensor.getHp() > 0) {
                atacar(atacante, defensor);

                // la velocidad decide el tiempo entre ataques
                int tiempo = 1000 / atacante.getVelocidad();
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para realizar el ataque
    private void atacar(Personaje atacante, Personaje defensor) {
        int danio = atacante.getAtaque() - defensor.getDefensa();
        if (danio < 0) {
            danio = 0; // no puede ser negativo
        }

        int nuevoHp = defensor.getHp() - danio;
        defensor.setHp(nuevoHp);

        System.out.println(atacante.getNombre() + " golpea a " + defensor.getNombre() +
                " por " + danio + " puntos. HP restante de " + defensor.getNombre() + ": " + defensor.getHp());

        if (defensor.getHp() <= 0) {
            System.out.println(defensor.getNombre() + " ha sido derrotado.");
            System.out.println("Ganador: " + atacante.getNombre());
            enCurso = false; // termina la pelea
            
            //guardar en el historial 
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            RegistroBatalla registro = new RegistroBatalla(p1.getNombre(), p2.getNombre(), atacante.getNombre(), fecha);
            historial.agregarRegistro(registro);
        }
    }
}
