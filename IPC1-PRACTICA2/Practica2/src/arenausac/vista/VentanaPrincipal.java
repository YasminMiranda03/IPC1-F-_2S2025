/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arenausac.vista;

/**
 *
 * @author Katherin Yasmin
 */

import javax.swing.*;
import java.awt.*;
import arenausac.controlador.ArenaUSAC;
import arenausac.controlador.Historial;
import arenausac.controlador.Batalla;
import arenausac.modelo.Personaje;
import arenausac.modelo.Estudiante;

public class VentanaPrincipal extends JFrame{
    private ArenaUSAC arena;
    private Historial historial;
    private Estudiante estudiante;

    public VentanaPrincipal() {
        // Inicializar arena y historial
        arena = new ArenaUSAC(10);
        historial = new Historial(20);

        // Pedir datos del estudiante
        String nombreEst = JOptionPane.showInputDialog(this, "Nombre del estudiante:");
        String carneEst = JOptionPane.showInputDialog(this, "Carnet:");
        estudiante = new Estudiante(nombreEst, carneEst);

        // Configurar ventana
        setTitle("ArenaUSAC");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponentes();
    }

    private void initComponentes() {
        // Área de texto para mostrar información
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        // Botones
        JButton btnAgregar = new JButton("Agregar Personaje");
        JButton btnListar = new JButton("Listar Personajes");
        JButton btnBuscar = new JButton("Buscar Personaje");
        JButton btnEliminar = new JButton("Eliminar Personaje");
        JButton btnModificar = new JButton("Modificar Personaje");
        JButton btnBatalla = new JButton("Iniciar Batalla");
        JButton btnHistorial = new JButton("Mostrar Historial");
        JButton btnGuardarP = new JButton("Guardar Personajes");
        JButton btnCargarP = new JButton("Cargar Personajes");
        JButton btnGuardarH = new JButton("Guardar Historial");
        JButton btnEstudiante = new JButton("Datos del Estudiante");

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 2, 5, 5));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnListar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBatalla);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnGuardarP);
        panelBotones.add(btnCargarP);
        panelBotones.add(btnGuardarH);
        panelBotones.add(btnEstudiante);

        // Layout general
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        // ===== Listeners =====

        // Agregar personaje
        btnAgregar.addActionListener(e -> {
            try {
                String nombre = JOptionPane.showInputDialog(this, "Nombre:");
                String arma = JOptionPane.showInputDialog(this, "Arma:");
                int hp = Integer.parseInt(JOptionPane.showInputDialog(this, "HP (100-500):"));
                int ataque = Integer.parseInt(JOptionPane.showInputDialog(this, "Ataque (10-100):"));
                int velocidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Velocidad (1-10):"));
                int agilidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Agilidad (1-10):"));
                int defensa = Integer.parseInt(JOptionPane.showInputDialog(this, "Defensa (1-50):"));

                // Validar rangos
                if (hp < 100 || hp > 500 || ataque < 10 || ataque > 100 || velocidad < 1 || velocidad > 10
                        || agilidad < 1 || agilidad > 10 || defensa < 1 || defensa > 50) {
                    JOptionPane.showMessageDialog(this, "Valores fuera de rango");
                    return;
                }

                Personaje p = new Personaje(nombre, arma, hp, ataque, velocidad, agilidad, defensa);
                arena.agregarPersonaje(p);
                JOptionPane.showMessageDialog(this, "Personaje agregado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: ingrese números válidos");
            }
        });

        // Listar personajes
        btnListar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Personaje p : arena.getListaPersonajes()) {
                sb.append(p.toString()).append("\n");
            }
            JTextArea text = new JTextArea(sb.toString());
            text.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(text), "Lista de Personajes", JOptionPane.INFORMATION_MESSAGE);
        });

        // Buscar personaje por nombre
        btnBuscar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del personaje:");
            Personaje p = arena.buscarPorNombre(nombre);
            if (p != null) {
                JOptionPane.showMessageDialog(this, p.toString(), "Personaje encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ese personaje");
            }
        });

        // Eliminar personaje por ID
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del personaje a eliminar:"));
                arena.eliminarPersonaje(id);
                JOptionPane.showMessageDialog(this, "Personaje eliminado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido");
            }
        });

        // Modificar personaje
        btnModificar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del personaje a modificar:"));
                String arma = JOptionPane.showInputDialog(this, "Nueva arma:");
                int hp = Integer.parseInt(JOptionPane.showInputDialog(this, "Nuevo HP (100-500):"));
                int ataque = Integer.parseInt(JOptionPane.showInputDialog(this, "Nuevo Ataque (10-100):"));
                int velocidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Nueva Velocidad (1-10):"));
                int agilidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Nueva Agilidad (1-10):"));
                int defensa = Integer.parseInt(JOptionPane.showInputDialog(this, "Nueva Defensa (1-50):"));

                arena.modificarPersonaje(id, arma, hp, ataque, velocidad, agilidad, defensa);
                JOptionPane.showMessageDialog(this, "Personaje modificado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en los valores ingresados");
            }
        });

        // Iniciar batalla
        btnBatalla.addActionListener(e -> {
            try {
                int id1 = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del primer personaje:"));
                int id2 = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del segundo personaje:"));
                Personaje p1 = arena.buscarPorId(id1);
                Personaje p2 = arena.buscarPorId(id2);

                if (p1 != null && p2 != null) {
                    new Thread(() -> {
                        Batalla batalla = new Batalla(p1, p2, Historial historial);
                        batalla.iniciar();
                    }).start();
                    JOptionPane.showMessageDialog(this, "Batalla iniciada en segundo plano");
                } else {
                    JOptionPane.showMessageDialog(this, "Alguno de los personajes no existe");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido");
            }
        });

        // Mostrar historial
        btnHistorial.addActionListener(e -> historial.mostrarHistorial());

        // Guardar personajes
        btnGuardarP.addActionListener(e -> {
            try {
                arena.guardarPersonajes("personajes.txt");
                JOptionPane.showMessageDialog(this, "Personajes guardados");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        // Cargar personajes
        btnCargarP.addActionListener(e -> {
            try {
                arena.cargarPersonajes("personajes.txt");
                JOptionPane.showMessageDialog(this, "Personajes cargados");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage());
            }
        });

        // Guardar historial
        btnGuardarH.addActionListener(e -> {
            try {
                historial.guardarHistorial("historial.txt");
                JOptionPane.showMessageDialog(this, "Historial guardado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar historial: " + ex.getMessage());
            }
        });

        // Mostrar datos del estudiante
        btnEstudiante.addActionListener(e -> JOptionPane.showMessageDialog(this, estudiante.toString(), "Datos del estudiante", JOptionPane.INFORMATION_MESSAGE));
    }
}
