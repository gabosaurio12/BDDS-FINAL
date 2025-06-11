package veterinaria.view;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import veterinaria.businesslogic.dao.DireccionDuenoDAO;
import veterinaria.businesslogic.dao.DuenoDAO;
import veterinaria.businesslogic.dto.DireccionDuenoDTO;
import veterinaria.businesslogic.dto.DuenoDTO;


/**
 *
 * @author User
 */
public class GUICRUDDueno extends javax.swing.JFrame {

    private static final Logger logger = LogManager.getLogger(GUICRUDDueno.class);
    public GUICRUDDueno() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDuenios = new javax.swing.JTable();
        botonRegistrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Dueños de mascotas");

        tablaDuenios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Telefono", "Correo", "Calle", "Colonia", "Numero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaDuenios);

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
        GUIRegistroDueno controlador = new GUIRegistroDueno();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        int filaSeleccionada = tablaDuenios.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un dueño para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea eliminar este dueño y todas sus direcciones?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            String telefono = (String) tablaDuenios.getValueAt(filaSeleccionada, 1);

            DuenoDAO duenoDAO = new DuenoDAO();
            DireccionDuenoDAO direccionDAO = new DireccionDuenoDAO();

            int idDuenio = duenoDAO.obtenerDuenoPorTelefono(telefono);

            if (idDuenio == 0) {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el dueño seleccionado.");
                return;
            }

            boolean direccionesEliminadas = direccionDAO.eliminarDireccionesPorDuenio(idDuenio);
            if (!direccionesEliminadas) {
                logger.warn("No se pudieron eliminar todas las direcciones del dueño ID: " + idDuenio);
            }
            
            int filasAfectadas = duenoDAO.eliminar(idDuenio);
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Dueño eliminado exitosamente.");
                llenarTablaDuenos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el dueño.");
            }

        } catch (Exception ex) {
            logger.error("Error al eliminar dueño", ex);
            JOptionPane.showMessageDialog(this, "Error al eliminar el dueño: " + ex.getMessage());
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        int filaSeleccionada = tablaDuenios.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {
                String telefono = (String) tablaDuenios.getValueAt(filaSeleccionada, 1);

                DuenoDAO duenoDAO = new DuenoDAO();
                DireccionDuenoDAO direccionDAO = new DireccionDuenoDAO();

                int idDuenio = duenoDAO.obtenerDuenoPorTelefono(telefono);

                if (idDuenio == 0) {
                    JOptionPane.showMessageDialog(this, "No se pudo encontrar el dueño seleccionado.");
                    return;
                }

                DuenoDTO dueno = duenoDAO.obtenerPorId(idDuenio);

                if (dueno == null) {
                    JOptionPane.showMessageDialog(this, "No se pudo cargar la información del dueño.");
                    return;
                }

                List<DireccionDuenoDTO> direcciones = direccionDAO.obtenerDireccionesPorDuenio(idDuenio);
                DireccionDuenoDTO direccion = null;
                if (!direcciones.isEmpty()) {
                    direccion = direcciones.get(0); // Tomamos la primera dirección
                }

                GUIRegistroDueno controlador = new GUIRegistroDueno(dueno, direccion);
                controlador.setVisible(true);
                this.dispose();

            } catch (Exception ex) {
                logger.error("Error en GUICRUDDueno.botonEditarActionPerformed", ex);
                JOptionPane.showMessageDialog(this, "Error al cargar datos del dueño: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un dueño para editar.");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        GUIMenuPrincipal controlador = new GUIMenuPrincipal();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed
    
    public void llenarTablaDuenos() {
        String[] columnas = {"Nombre", "Teléfono", "Correo", "Calle", "Colonia", "Número"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            DuenoDAO duenoDAO = new DuenoDAO();
            DireccionDuenoDAO direccionDAO = new DireccionDuenoDAO();
            List<DuenoDTO> duenos = duenoDAO.obtenerTodos();

            for (DuenoDTO dueno : duenos) {
                List<DireccionDuenoDTO> direcciones = direccionDAO.obtenerDireccionesPorDuenio(dueno.getIdDuenio());

                if (!direcciones.isEmpty()) {
                    for (DireccionDuenoDTO direccion : direcciones) {
                        Object[] fila = {
                            dueno.getNombreCompleto(),
                            dueno.getTelefono(),
                            dueno.getEmail(),
                            direccion.getCalle(),
                            direccion.getColonia(),
                            direccion.getNumero() // Ahora es String
                        };
                        modelo.addRow(fila);
                    }
                } else {
                    Object[] fila = {
                        dueno.getNombreCompleto(),
                        dueno.getTelefono(),
                        dueno.getEmail(),
                        "",
                        "",
                        ""
                    };
                    modelo.addRow(fila);
                }
            }

            tablaDuenios.setModel(modelo);
            configurarSeleccionTabla();

        } catch (Exception ex) {
            logger.error("Error al llenar tabla de dueños", ex);
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage());
        }
    }
    
    private void configurarSeleccionTabla() {
    tablaDuenios.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            boolean haySeleccion = tablaDuenios.getSelectedRow() != -1;
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
    private javax.swing.JTable tablaDuenios;
    // End of variables declaration//GEN-END:variables
}
