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
    private Historial historial;
    private JTextArea areaTexto;        //para mostrar los resultados en gui
    private boolean enCurso;
    
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
        barraP1.setMaximum(500);
        barraP1.setValue(p1.getHp());

        barraP2.setMaximum(500);
        barraP2.setValue(p2.getHp());
    }
    
   public Personaje iniciar() {
        Thread t1 = new Thread(() -> atacar(p1, p2, barraP2));
        Thread t2 = new Thread(() -> atacar(p2, p1, barraP1));

        t1.start();
        t2.start();

        try {
            t1.join(); // espera a que termine t1
            t2.join(); // espera a que termine t2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Determinar ganador
        if (p1.getHp() > 0) {
            return p1;
        } else {
            return p2;
        }
    }

    

    // Método para realizar el ataque
    private void atacar(Personaje atacante, Personaje defensor, JProgressBar barraDefensor) {
        while (enCurso && atacante.getHp() > 0 && defensor.getHp() > 0) {
            try {
                // Espera entre ataques (1 segundo)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Calcular daño simple
            int dano = (int) (Math.random() * 10 + 1);
            int nuevaVida = defensor.getHp() - dano;

            if (nuevaVida < 0) {
                nuevaVida = 0;
            }
            defensor.setHp(nuevaVida);

            // Actualizamos la interfaz gráfica
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    barraDefensor.setValue(defensor.getHp());
                    barraDefensor.repaint();
                    areaTexto.append(atacante.getNombre() + " ataca a " +
                            defensor.getNombre() + " y le quita " + dano + " de vida\n");
                }
            });
        }

        // Ver quién ganó
        if (enCurso) {
            enCurso = false;
            Personaje ganador;
            if (p1.getHp() > 0) {
                ganador = p1;
            } else {
                ganador = p2;
            }

            // Mostrar mensaje en el área de texto
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    areaTexto.append(ganador.getNombre() + " gana la batalla!\n");
                }
            });

            historial.registrarBatalla(p1, p2, ganador);
        }
    }    
}