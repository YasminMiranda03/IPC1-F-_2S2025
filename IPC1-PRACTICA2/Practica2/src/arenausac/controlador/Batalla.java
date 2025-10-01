/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.controlador;

import arenausac.modelo.Personaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 *
 * @author Katherin Yasmin
 */
public class Batalla {
    private Personaje p1;
    private Personaje p2;
    private boolean enCurso;
    private Historial historial;
    private JTextArea areaTexto;        //para mostrar los resultados en gui
    private volatile boolean enBatalla = true;
    
    private JProgressBar barraP1;
    private JProgressBar barraP2;
    
    
    public Batalla(Personaje p1, Personaje p2, Historial historial, JTextArea areaTexto, JProgressBar barraP1, JProgressBar barraP2){
        this.p1 = p1;
        this.p2 = p2;
        this.enCurso = true;
        this.historial = historial;
        this.areaTexto = areaTexto;
        
        this.barraP1 = barraP1;
        this.barraP2 = barraP2; 
        
        //Inicializando las barras con la vida inicial
        SwingUtilities.invokeLater(() -> {
           barraP1.setValue(p1.getHp());
           barraP2.setValue(p2.getHp());
        });
    }
    
   public void iniciar() {
        System.out.println("BATALLA");
        Thread t1 = new Thread(() -> atacar(p1, p2));
        Thread t2 = new Thread(() -> atacar(p2, p1));

        t1.start();
        t2.start();
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
        while (enBatalla && atacante.getHp() > 0 && defensor.getHp() > 0) {
            try {
                // Velocidad: mientras más alta, menos tiempo espera
                Thread.sleep(1000 / atacante.getVelocidad());

                // ¿El defensor esquiva?
                if (Math.random() * 10 < defensor.getAgilidad()) {
                    log(defensor.getNombre() + " esquivó el ataque de " + atacante.getNombre());
                    continue;
                }

                // Calcular daño real (ataque - defensa)
                int daño = atacante.getAtaque() - defensor.getDefensa();
                if (daño < 0) daño = 0;

                defensor.setHp(defensor.getHp() - daño);

                log(atacante.getNombre() + " atacó a " + defensor.getNombre()
                        + " causando " + daño + " de daño. Vida restante de "
                        + defensor.getNombre() + ": " + defensor.getHp());

                // Verificar si el defensor murió
                if (defensor.getHp() <= 0) {
                    enBatalla = false;
                    log(atacante.getNombre() + " ganó la batalla contra " + defensor.getNombre());
                    historial.registrarBatalla(p1, p2, atacante); // guarda en historial
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if(defensor == p1){
            SwingUtilities.invokeLater(() -> barraP1.setValue(defensor.getHp()));
        } else {
            SwingUtilities.invokeLater(() -> barraP2.setValue(defensor.getHp()));
        }
}
    private void log(String mensaje){
        SwingUtilities.invokeLater(() -> areaTexto.append(mensaje + "\n"));
    }
}