/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package usacshop.vista;
import usacshop.controlador.Bitacora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Katherin Yasmin
 */
public class PedidosView extends javax.swing.JFrame {
    
   
    /**
     * Creates new form PedidosView
     */
    public PedidosView() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Pedidos Activos");
        cargarPedidos();
    }
    private void cargarPedidos() {
        DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
        model.setRowCount(0);

        File archivo = new File("pedidos.txt");
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(this, "No hay pedidos pendientes.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6 && datos[5].equalsIgnoreCase("Pendiente")){
                    model.addRow(new Object[]{datos[0], datos[1], datos[2], datos[3]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage());
        }
    }
     private void confirmarPedido(String codigoProducto, String codigoCliente, double total) {
        File archivo = new File("pedidos.txt");
        File temp = new File("pedidos_temp.txt");
        File confirmados = new File("pedidos_confirmados.txt");

        try (
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            PrintWriter pwTemp = new PrintWriter(new FileWriter(temp));
            PrintWriter pwConf = new PrintWriter(new FileWriter(confirmados, true))
        ) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    if (datos[0].equals(codigoProducto) && datos[2].equals(codigoCliente)) {
                        // Guardar como confirmado
                        pwConf.println(datos[0] + "," + datos[1] + "," + datos[2] + "," +
                                       datos[3] + "," + datos[4] + ",Confirmado");
                        actualizarStock(codigoProducto);
                        registrarHistorial("VE001", codigoProducto, total);
                    } else {
                        pwTemp.println(linea);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al confirmar pedido: " + e.getMessage());
        }

        archivo.delete();
        temp.renameTo(archivo);
    }
    
    private void actualizarStock(String codigoProducto) {
        File stockFile = new File("stock.txt");
        File temp = new File("stock_temp.txt");

        try (
            BufferedReader br = new BufferedReader(new FileReader(stockFile));
            PrintWriter pw = new PrintWriter(new FileWriter(temp))
        ) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(codigoProducto)) {
                    int stock = Integer.parseInt(datos[1]);
                    stock = Math.max(stock - 1, 0); // Disminuir 1 (ajustar si guardas cantidad)
                    pw.println(codigoProducto + "," + stock);
                } else {
                    pw.println(linea);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar stock: " + e.getMessage());
        }

        stockFile.delete();
        temp.renameTo(stockFile);
    }
    
     private void registrarHistorial(String vendedor, String codigoProducto, double total) {
        File archivo = new File("historial.txt");
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now().withNano(0);

        String linea = fecha + "," + hora + "," + codigoProducto + "," + total + "," + vendedor;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar historial: " + e.getMessage());
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnConfirmarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Pedidos");

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Fecha generacion", "Codigo cliente", "Nombre", "Total", "Opciones"
            }
        ));
        jScrollPane1.setViewportView(tablaPedidos);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnConfirmarPedido.setText("Confirmar pedido");
        btnConfirmarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnRegresar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConfirmarPedido)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnConfirmarPedido)
                    .addComponent(btnActualizar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPedidoActionPerformed
        // TODO add your handling code here:
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para confirmar.");
            return;
        }

        String codigoProducto = tablaPedidos.getValueAt(fila, 0).toString();
        String codigoCliente = tablaPedidos.getValueAt(fila, 2).toString();
        double total = Double.parseDouble(tablaPedidos.getValueAt(fila, 4).toString());

        try {
            confirmarPedido(codigoProducto, codigoCliente, total);

            ((DefaultTableModel) tablaPedidos.getModel()).removeRow(fila);
            JOptionPane.showMessageDialog(this, "Pedido confirmado correctamente.");

            // Registrar evento exitoso en la bitácora
            Bitacora.registrarEvento("VENDEDOR", "VE001", "CONFIRMAR_PEDIDO", "EXITOSA",
                                     "Pedido confirmado: Producto " + codigoProducto + ", Cliente " + codigoCliente + ", Total $" + total);
        } catch (Exception e) {
            // Registrar evento fallido en la bitácora
            Bitacora.registrarEvento("VENDEDOR", "VE001", "CONFIRMAR_PEDIDO", "FALLIDA",
                                     "Error al confirmar pedido: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al confirmar pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmarPedidoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new VendedorView().setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cargarPedidos();
    }//GEN-LAST:event_btnActualizarActionPerformed

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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConfirmarPedido;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
