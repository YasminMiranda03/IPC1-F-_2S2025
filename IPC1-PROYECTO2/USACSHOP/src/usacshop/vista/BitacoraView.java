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
import java.time.LocalDate;
import java.time.LocalTime;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Katherin Yasmin
 */
public class BitacoraView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BitacoraView.class.getName());
    
    /**
     * Creates new form BitacoraView
     */
    public BitacoraView() {
        initComponents();
       
        setLocationRelativeTo(null);
        setTitle("Bitácora del Sistema");
        cargarBitacora(); // cargar al abrir la ventana
    }
    
    public BitacoraView(JFrame ventanaAnterior) {
        initComponents();
        setLocationRelativeTo(null);
       
        cargarBitacora();
    }
    private void cargarBitacora() {
    DefaultTableModel modelo = (DefaultTableModel) tablaBitacora.getModel();
    modelo.setRowCount(0); // limpiar tabla
    File archivo = new File("bitacora.txt");
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(this, "No hay registros en la bitácora.", "Información", JOptionPane.INFORMATION_MESSAGE);
            tablaBitacora.setModel(modelo);
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader("bitacora.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(" \\| ");
                if (datos.length == 6) {
                modelo.addRow(datos);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la bitácora: " + e.getMessage());
        }
    }
    private void filtrarPorUsuario() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código de usuario:");
        if (codigo == null || codigo.isEmpty()) return;

        DefaultTableModel modelo = (DefaultTableModel) tablaBitacora.getModel();
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            if (!modelo.getValueAt(i, 2).toString().equalsIgnoreCase(codigo)) {
                modelo.removeRow(i);
            }
        }
    }
    private void exportarCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("bitacora_exportada.csv"))) {
            DefaultTableModel modelo = (DefaultTableModel) tablaBitacora.getModel();
            // Cabecera
            for (int c = 0; c < modelo.getColumnCount(); c++) {
                pw.print(modelo.getColumnName(c));
                if (c < modelo.getColumnCount()-1) pw.print(",");
            }
            pw.println();
            // Datos
            for (int r = 0; r < modelo.getRowCount(); r++) {
                for (int c = 0; c < modelo.getColumnCount(); c++) {
                    pw.print(modelo.getValueAt(r,c));
                    if (c < modelo.getColumnCount()-1) pw.print(",");
                }
                pw.println();
            }
            JOptionPane.showMessageDialog(this, "Bitácora exportada correctamente a CSV.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al exportar CSV: " + e.getMessage());
        }
    }
    private void exportarPDF() {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("bitacora_exportada.pdf"));
        document.open();

        // Título del PDF
        document.add(new Paragraph("BITÁCORA DEL SISTEMA"));
        document.add(new Paragraph(" ")); // línea en blanco

        // Crear la tabla con el mismo número de columnas que la JTable
        DefaultTableModel modelo = (DefaultTableModel) tablaBitacora.getModel();
        PdfPTable tablaPDF = new PdfPTable(modelo.getColumnCount());

        // Agregar encabezados
        for (int c = 0; c < modelo.getColumnCount(); c++) {
            tablaPDF.addCell(modelo.getColumnName(c));
        }

        // Agregar filas
        for (int r = 0; r < modelo.getRowCount(); r++) {
            for (int c = 0; c < modelo.getColumnCount(); c++) {
                String valor = modelo.getValueAt(r, c).toString();
                tablaPDF.addCell(valor);
            }
        }

        // Agregar tabla al documento
        document.add(tablaPDF);

        JOptionPane.showMessageDialog(this, "Bitácora exportada correctamente a PDF.");

    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(this, "Error al exportar PDF: " + e.getMessage());
    } finally {
        document.close();
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
        tablaBitacora = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Bitacora del sistema");

        tablaBitacora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Fecha hora", "Tipo usuario", "Codigo usuario", "operacion", "Estado", "Descripcion"
            }
        ));
        jScrollPane1.setViewportView(tablaBitacora);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField1.setText("jTextField1");

        jButton1.setText("Filtrar");

        jButton2.setText("Exportar CSV");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Exportar PDF");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnCerrar)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cargarBitacora();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
      this.dispose();
        new AdminView().setVisible(true);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        exportarPDF();
    }//GEN-LAST:event_jButton3ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new BitacoraView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaBitacora;
    // End of variables declaration//GEN-END:variables
}
