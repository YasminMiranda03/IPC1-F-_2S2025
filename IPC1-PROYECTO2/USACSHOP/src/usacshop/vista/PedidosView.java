/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package usacshop.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
/**
 *
 * @author Katherin Yasmin
 */
public class PedidosView extends javax.swing.JFrame {
    
    private JTable tablaPedidos;
    private DefaultTableModel modeloPedidos;
    /**
     * Creates new form PedidosView
     */
    public PedidosView() {
        setTitle("Pedidos Activos");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        // Columnas de la tabla
        String[] columnas = {"Código", "Fecha", "Código Cliente", "Nombre Cliente", "Total", "Opciones"};
        modeloPedidos = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Solo columna "Opciones" editable
            }
        };

        tablaPedidos = new JTable(modeloPedidos);

        // Configurar renderer y editor del botón con Swing puro
        tablaPedidos.getColumn("Opciones").setCellRenderer(new JTableButtonRenderer());
        tablaPedidos.getColumn("Opciones").setCellEditor(new JTableButtonEditor());

        // Scroll
        JScrollPane scroll = new JScrollPane(tablaPedidos);
        panel.add(scroll);

        // Cargar pedidos desde archivo
        cargarPedidos();
    }
    
    private void cargarPedidos() {
        modeloPedidos.setRowCount(0);
        File file = new File("pedidos.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    modeloPedidos.addRow(new Object[]{
                            datos[0], // Código producto
                            datos[1], // Fecha
                            datos[2], // Código cliente
                            datos[3], // Nombre cliente
                            datos[4], // Total
                            "Confirmar"
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage());
        }
    }
    
     private class JTableButtonRenderer extends JButton implements TableCellRenderer {
        public JTableButtonRenderer() { setOpaque(true); }
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                                boolean isSelected, boolean hasFocus,
                                                                int row, int column) {
            setText("Confirmar");
            return this;
        }
    }
    private class JTableButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private int fila;

        public JTableButtonEditor() {
            button = new JButton("Confirmar");
            button.addActionListener(e -> {
                confirmarPedido(fila);
                fireEditingStopped();
            });
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                              boolean isSelected, int row, int column) {
            this.fila = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Confirmar";
        }
    }

     private void confirmarPedido(int fila) {
        String codigo = tablaPedidos.getValueAt(fila, 0).toString();
        String fecha = tablaPedidos.getValueAt(fila, 1).toString();

        File filePedidos = new File("pedidos.txt");
        File tempFile = new File("pedidos_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(filePedidos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.contains(codigo + "," + fecha)) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al confirmar pedido: " + e.getMessage());
            return;
        }

        filePedidos.delete();
        tempFile.renameTo(filePedidos);

        // Eliminar fila de la tabla
        modeloPedidos.removeRow(fila);
        JOptionPane.showMessageDialog(this, "Pedido confirmado: " + codigo);
    }

    // Actualiza stock en productos.txt y en memoria
    private void actualizarStock(String codigoProducto, int cantidad) {
        File fileProductos = new File("productos.txt");
        File tempFile = new File("productos_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(fileProductos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(codigoProducto)) {
                    int stockActual = Integer.parseInt(datos.length >=5 ? datos[4] : "0");
                    int nuevoStock = stockActual - cantidad;
                    if (nuevoStock < 0) nuevoStock = 0;
                    // Reescribir línea con stock actualizado
                    if (datos.length >=5) {
                        datos[4] = String.valueOf(nuevoStock);
                    } else {
                        // Agregar stock como 5ta columna
                        String[] tmp = new String[5];
                        tmp[0] = datos[0]; tmp[1] = datos[1]; tmp[2] = datos[2]; tmp[3] = datos[3]; tmp[4] = String.valueOf(nuevoStock);
                        datos = tmp;
                    }
                }
                bw.write(String.join(",", datos));
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar stock: " + e.getMessage());
            return;
        }

        // Reemplazar archivo original
        fileProductos.delete();
        tempFile.renameTo(fileProductos);
    }

    // Elimina el pedido de pedidos.txt y de la tabla
    private void eliminarPedidoArchivo(int fila) {
        String codigo = tablaPedidos.getValueAt(fila, 0).toString();
        String fecha = tablaPedidos.getValueAt(fila, 1).toString();
        File filePedidos = new File("pedidos.txt");
        File tempFile = new File("pedidos_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(filePedidos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.contains(codigo + "," + fecha)) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar pedido: " + e.getMessage());
            return;
        }

        filePedidos.delete();
        tempFile.renameTo(filePedidos);

        // Eliminar fila de la tabla
        modeloPedidos.removeRow(fila);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PedidosView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
