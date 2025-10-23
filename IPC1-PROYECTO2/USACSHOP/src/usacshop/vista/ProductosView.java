/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package usacshop.vista;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
/**
 *
 * @author Katherin Yasmin
 */
public class ProductosView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductosView.class.getName());
    
    DefaultTableModel modelo;
    
    private int contadorProductos = 1;  //contador para los productos
    /**
     * Creates new form ProductosView
     */
    public ProductosView() {
        initComponents();
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoria");
        modelo.addColumn("Acciones");
        
        tablaProductos.setModel(modelo);
        cargarProductos();      //cargar productos desde el archivo
        inicializarContador();
        
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaProductos.rowAtPoint(evt.getPoint());
                int columna = tablaProductos.columnAtPoint(evt.getPoint());

                if (columna == 3 && fila >= 0) { // columna "Acciones"
                    mostrarDetalleProducto(fila);
                }
            }
        });
    }
    
    public void cargarProductos(){
        modelo.setRowCount(0);
        File archivo = new File("productos.txt");
        if(archivo.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",", 4);
                    if (datos.length >= 4) {
                        modelo.addRow(new Object[]{
                            datos[0], datos[1], datos[2], "Ver detalle"});
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al leer productos: " + e.getMessage());
            }
        }
    }
    
    public void guardarProductos(String codigo, String nombre, String categoria, String detalle){
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("productos.txt", true))){
            bw.write(codigo + "," + nombre + "," + categoria + "," + detalle);
            bw.newLine();
        } catch (java.io.IOException e){
            JOptionPane.showMessageDialog(this, "Error al guardar producto: " + e.getMessage());
        }
    }
    
    private void mostrarDetalleProducto(int fila){
        String codigo = modelo.getValueAt(fila, 0).toString();
        String nombre = modelo.getValueAt(fila, 1).toString();
        String categoria = modelo.getValueAt(fila, 2).toString();
        String detalle = "";

        try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 4);
                if (datos[0].equals(codigo)) {
                    detalle = datos[3];
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer detalles: " + e.getMessage());
        }

        JOptionPane.showMessageDialog(this,
            "Código: " + codigo +
            "\nNombre: " + nombre +
            "\nCategoría: " + categoria +
            "\nDetalle: " + detalle,
            "Detalle del Producto",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void actualizarArchivo(int fila, String codigo, String nombre, String categoria, String detalle) {
        File archivo = new File("productos.txt");
        if (!archivo.exists()) return;

        try {
            // Leemos todo el archivo
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 4);
                if (datos[0].equals(codigo)) {
                    sb.append(codigo).append(",").append(nombre).append(",").append(categoria).append(",").append(detalle).append("\n");
                } else {
                    sb.append(linea).append("\n");
                }
            }
            br.close();

            // Reescribimos el archivo completo
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto: " + e.getMessage());
        }
    }
    
    //para validar si ya existe el codigo actual
    private boolean codigoExiste(String codigo) {
        File archivo = new File("productos.txt");
        if (!archivo.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 4);
                if (datos[0].equals(codigo)) {
                    return true; // Código ya existe
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo: " + e.getMessage());
        }
        return false;
    }

    // Método para buscar fila en la tabla por código
    private int buscarFilaPorCodigo(String codigo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equals(codigo)) {
                return i;
            }
        }
        return -1; // No encontrado
    }
    
    private void inicializarContador() {
        int max = 0;
        File archivo = new File("productos.txt");
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",", 4);
                    if (datos.length > 0 && datos[0].startsWith("P")) {
                        try {
                            int num = Integer.parseInt(datos[0].substring(1));
                            if (num > max) max = num;
                        } catch (NumberFormatException e) {
                            // ignorar códigos no válidos
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al leer productos: " + e.getMessage());
            }
        }
        contadorProductos = max + 1;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnCargarCSV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de productos");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Gestion de Productos");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Categoria", "Acciones"
            }
        ));
        jScrollPane2.setViewportView(tablaProductos);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar ");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCargarCSV.setText("Cargar");
        btnCargarCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(107, 107, 107))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargarCSV)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnRegresar)
                    .addComponent(btnCargarCSV))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        String codigo = "P" + contadorProductos;
        contadorProductos++;
        
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del producto:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        
        String[] categorias = {"Tecnologia", "Alimento", "General"};
        String categoria = (String) JOptionPane.showInputDialog(this, "Seleccione la categoria","Categoria", JOptionPane.QUESTION_MESSAGE, null, categorias, categorias[0]);
        if (categoria == null)
            return;
        
        String detalle = "";
        switch (categoria) {
            case "Tecnologia":
                detalle = JOptionPane.showInputDialog(this, "Meses de garantía:");
                break;
            case "Alimento":
                detalle = JOptionPane.showInputDialog(this, "Fecha de caducidad (dd/mm/aaaa):");
                break;
            case "General":
                detalle = JOptionPane.showInputDialog(this, "Material del producto:");
                break;
        }
        modelo.addRow(new Object[]{codigo, nombre, categoria, "Ver Detalle"});
        guardarProductos(codigo, nombre, categoria, detalle);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del producto a eliminar:");
        if (codigo == null || codigo.trim().isEmpty()) return;

        int fila = buscarFilaPorCodigo(codigo);
        if (fila >= 0) {
            modelo.removeRow(fila);

            // Actualizar archivo completo
            File archivo = new File("productos.txt");
            if (archivo.exists()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    StringBuilder sb = new StringBuilder();
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] datos = linea.split(",", 4);
                        if (!datos[0].equals(codigo)) {
                            sb.append(linea).append("\n");
                        }
                    }
                    br.close();

                    BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                    bw.write(sb.toString());
                    bw.close();

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar archivo: " + e.getMessage());
                }
            }

            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con ese código.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        AdminView admin = new AdminView();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del producto a modificar:");
        if (codigo == null || codigo.trim().isEmpty()) return;

        int fila = buscarFilaPorCodigo(codigo);
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con ese código.");
            return;
        }

        // Obtener datos actuales
        String nombreActual = modelo.getValueAt(fila, 1).toString();
        String categoriaActual = modelo.getValueAt(fila, 2).toString();
        String detalleActual = "";

        // Leer detalle del archivo
        try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 4);
                if (datos[0].equals(codigo) && datos.length >= 4) {
                    detalleActual = datos[3];
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo: " + e.getMessage());
        }

        // Pedir nuevos valores
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", nombreActual);
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) return;

        String[] categorias = {"Tecnologia", "Alimento", "General"};
        String nuevaCategoria = (String) JOptionPane.showInputDialog(this, "Seleccione la nueva categoría:",
                "Categoría", JOptionPane.QUESTION_MESSAGE, null, categorias, categoriaActual);
        if (nuevaCategoria == null) return;

        String nuevoDetalle = "";
        switch (nuevaCategoria) {
            case "Tecnologia":
                nuevoDetalle = JOptionPane.showInputDialog(this, "Meses de garantía:", detalleActual);
                break;
            case "Alimento":
                nuevoDetalle = JOptionPane.showInputDialog(this, "Fecha de caducidad (dd/mm/aaaa):", detalleActual);
                break;
            case "General":
                nuevoDetalle = JOptionPane.showInputDialog(this, "Material del producto:", detalleActual);
                break;
        }

        if (nuevoDetalle == null || nuevoDetalle.trim().isEmpty()) return;

        // Actualizar tabla
        modelo.setValueAt(nuevoNombre, fila, 1);
        modelo.setValueAt(nuevaCategoria, fila, 2);

        // Actualizar archivo
        actualizarArchivo(fila, codigo, nuevoNombre, nuevaCategoria, nuevoDetalle);

        JOptionPane.showMessageDialog(this, "Producto modificado correctamente.");
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCargarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCSVActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                int agregados = 0;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",", 4);
                    if (datos.length < 4) continue; // validar formato

                    String codigo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String categoria = datos[2].trim();
                    String detalle = datos[3].trim();

                    // Verificar unicidad de código
                    if (buscarFilaPorCodigo(codigo) >= 0) continue;

                    // Agregar a tabla
                    modelo.addRow(new Object[]{codigo, nombre, categoria, "Ver detalle"});

                    // Guardar en archivo
                    guardarProductos(codigo, nombre, categoria, detalle);

                    // Ajustar contador
                    if (codigo.startsWith("P")) {
                        try {
                            int num = Integer.parseInt(codigo.substring(1));
                            if (num >= contadorProductos) contadorProductos = num + 1;
                        } catch (NumberFormatException e) {
                            // ignorar
                        }
                    }

                    agregados++;
                }

                JOptionPane.showMessageDialog(this, agregados + " productos agregados exitosamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al leer archivo CSV: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnCargarCSVActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ProductosView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargarCSV;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
