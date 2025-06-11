package veterinaria.view;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import veterinaria.businesslogic.dao.DireccionVeterinarioDAO;
import veterinaria.businesslogic.dao.VeterinarioDAO;
import veterinaria.businesslogic.dto.DireccionVeterinarioDTO;
import veterinaria.businesslogic.dto.VeterinarioDTO;


/**
 *
 * @author User
 */
public class GUICRUDVeterinario extends javax.swing.JFrame {

    private static final Logger logger = LogManager.getLogger(GUICRUDVeterinario.class);
    
    public GUICRUDVeterinario() {
        initComponents();
        configurarSeleccionTabla();
        inicializarVentana();
    }
    
    public void inicializarVentana() {
    configurarSeleccionTabla();
    llenarTablaVeterinarios();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVeterinarios = new javax.swing.JTable();
        botonRegistrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Dueños de mascotas");

        tablaVeterinarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Telefono", "Usuario", "Calle", "Colonia", "Numero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaVeterinarios);

        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        botonRegresar.setText("Regresar");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botonRegistrar)
                        .addComponent(botonEliminar)
                        .addComponent(botonEditar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonRegresar)
                        .addGap(6, 6, 6)))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel1)
                .addContainerGap(326, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(botonRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(botonEditar)
                        .addGap(18, 18, 18)
                        .addComponent(botonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonRegresar)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        GUIRegistroVeterinario controlador = new GUIRegistroVeterinario();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        int filaSeleccionada = tablaVeterinarios.getSelectedRow();
    
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un veterinario para eliminar.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener la cédula del veterinario seleccionado
        int cedula = (Integer) tablaVeterinarios.getValueAt(filaSeleccionada, 0);
        String nombreVeterinario = (String) tablaVeterinarios.getValueAt(filaSeleccionada, 1);

        // Confirmar eliminación
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea eliminar al veterinario " + nombreVeterinario + 
            " (Cédula: " + cedula + ")?\n\nEsta acción no se puede deshacer.", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
                boolean eliminado = veterinarioDAO.eliminarVeterinario(cedula);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, 
                        "Veterinario eliminado exitosamente.", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);

                    // Actualizar la tabla
                    llenarTablaVeterinarios();

                    logger.info("Veterinario con cédula {} eliminado exitosamente", cedula);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No se pudo eliminar el veterinario. Verifique que no tenga registros asociados.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                logger.error("Error al eliminar veterinario con cédula {}: ", cedula, e);
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar el veterinario: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        int filaSeleccionada = tablaVeterinarios.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un veterinario para editar.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener la cédula del veterinario seleccionado
        int cedula = (Integer) tablaVeterinarios.getValueAt(filaSeleccionada, 0);

        try {

            GUIRegistroVeterinario ventanaEdicion = new GUIRegistroVeterinario(cedula);
            ventanaEdicion.setVisible(true);
            this.dispose();

            logger.info("Solicitada edición de veterinario con cédula: {}", cedula);

        } catch (Exception e) {
            logger.error("Error al abrir ventana de edición: ", e);
            JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de edición: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        GUIMenuPrincipal controlador = new GUIMenuPrincipal();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed
    
    public void llenarTablaVeterinarios() {
        try {
            VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
            DireccionVeterinarioDAO direccionDAO = new DireccionVeterinarioDAO();

            List<VeterinarioDTO> veterinarios = veterinarioDAO.seleccionarTodosLosVeterinarios();

            DefaultTableModel modelo = (DefaultTableModel) tablaVeterinarios.getModel();
            modelo.setRowCount(0); 

            for (VeterinarioDTO veterinario : veterinarios) {
                List<DireccionVeterinarioDTO> direcciones = direccionDAO.obtenerDireccionesPorCedula(veterinario.getCedula());

                String calle = "";
                String colonia = "";
                String numero = "";

                if (!direcciones.isEmpty()) {
                    DireccionVeterinarioDTO direccion = direcciones.get(0); 
                    calle = direccion.getCalle() != null ? direccion.getCalle() : "";
                    colonia = direccion.getColonia() != null ? direccion.getColonia() : "";
                    numero = direccion.getNumero() != null ? direccion.getNumero() : "";
                }

                Object[] fila = {
                    veterinario.getCedula(),
                    veterinario.getNombreCompleto(),
                    veterinario.getTelefono(),
                    veterinario.getNombreDeUsuario(),
                    calle,
                    colonia,
                    numero
                };
                modelo.addRow(fila);
            }

            logger.info("Tabla de veterinarios actualizada con {} registros", veterinarios.size());

        } catch (SQLException e) {
            logger.error("Error al llenar tabla de veterinarios: ", e);
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los datos: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarSeleccionTabla() {
    tablaVeterinarios.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            boolean haySeleccion = tablaVeterinarios.getSelectedRow() != -1;
            botonEliminar.setEnabled(haySeleccion);
            botonEditar.setEnabled(haySeleccion);
        }
    });
    
    botonEliminar.setEnabled(false);
    botonEditar.setEnabled(false);
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVeterinarios;
    // End of variables declaration//GEN-END:variables
}
