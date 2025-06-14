/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package veterinaria.view;

import java.util.List;
import javax.swing.JOptionPane;
import veterinaria.businesslogic.dao.CitaDAO;
import veterinaria.businesslogic.dao.EnfermedadDAO;
import veterinaria.businesslogic.dto.CitaDTO;
import veterinaria.businesslogic.dto.ConsultarCitaDTO;
import veterinaria.businesslogic.dto.EnfermedadDTO;

/**
 *
 * @author gabosaurio
 */
public class GUIAtenderCita extends javax.swing.JFrame {

    private int cita;
    /**
     * Creates new form AtenderCita
     */
    
    public GUIAtenderCita() {
        initComponents();
    }
    
    public GUIAtenderCita(int cita) {
        this.cita = cita;
        initComponents();
        setData();
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
        jLabel2 = new javax.swing.JLabel();
        labelNombreMascota = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelNombreDueno = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaMotivo = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTratamiento = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        comboEnfermedad1 = new javax.swing.JComboBox<>();
        comboEnfermedad2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cita");

        jLabel2.setText("Mascota");

        labelNombreMascota.setText("NombreMascota");

        jLabel4.setText("Dueño");

        labelNombreDueno.setText("NombreDueño");

        jLabel3.setText("Motivo de la cita");

        areaMotivo.setColumns(20);
        areaMotivo.setRows(5);
        jScrollPane1.setViewportView(areaMotivo);

        jLabel5.setText("Tratamiento");

        areaTratamiento.setColumns(20);
        areaTratamiento.setRows(5);
        jScrollPane2.setViewportView(areaTratamiento);

        jLabel6.setText("Enfermedad");

        jLabel7.setText("Enfermedad");

        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNombreDueno)
                                    .addComponent(labelNombreMascota)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(comboEnfermedad1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(44, 44, 44)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(comboEnfermedad2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(botonCancelar)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(botonGuardar))))))))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombreMascota)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelNombreDueno))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(comboEnfermedad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(comboEnfermedad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        GUIMenuPrincipal controlador = new GUIMenuPrincipal();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setIdCita(cita);
        citaDTO.setTratamiento(areaTratamiento.getText());
        citaDTO.setMotivoDeConsulta(areaMotivo.getText());
        CitaDAO citaDAO = new CitaDAO();
        
        int filasAfectadasEnfermedad1 = 0;
        EnfermedadDTO enfermedad1 = (EnfermedadDTO) comboEnfermedad1.getSelectedItem();
        if (!enfermedad1.equals("Selecciona una enfermedad")) {
            filasAfectadasEnfermedad1 = citaDAO.insertarCitaEnfermedad(cita, enfermedad1.getIdEnfermedad());
        } else {
            JOptionPane.showMessageDialog(this, "¡Seleccione una enfermedad!");

        }
        
        int filasAfectadasEnfermedad2 = 0;
        EnfermedadDTO enfermedad2 = (EnfermedadDTO) comboEnfermedad2.getSelectedItem();
        if (!enfermedad2.equals("Selecciona una enfermedad")) {
            filasAfectadasEnfermedad2 = citaDAO.insertarCitaEnfermedad(cita, enfermedad2.getIdEnfermedad());
        } else {
            JOptionPane.showMessageDialog(this, "¡Seleccione una enfermedad!");

        }
        
        if (citaDAO.concluirCita(citaDTO) != 0) {
            if (!enfermedad1.equals("Selecciona una enfermedad") && filasAfectadasEnfermedad1 != 0) {
                if (!enfermedad2.equals("Selecciona una enfermedad") && filasAfectadasEnfermedad2 != 0) {
                    JOptionPane.showMessageDialog(this, "¡Se concluyó la cita!");
                }
                else {
                                JOptionPane.showMessageDialog(this, "¡Se concluyó la cita!");

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error al concluir cita");
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void setData() {
        ConsultarCitaDTO citaFiltrada = new CitaDAO().obtenerCitaFiltradaPorId(cita);
        labelNombreMascota.setText(citaFiltrada.getNombreMascota());
        labelNombreDueno.setText(citaFiltrada.getNombreDueno());
        areaMotivo.setText(citaFiltrada.getMotivoConsulta());
        setComboEnfermedades();
    }
    
    private void setComboEnfermedades() {
        List<EnfermedadDTO> enfermedades = new EnfermedadDAO().obtenerTodasLasEnfermedades();
        EnfermedadDTO enfermedadVacia = new EnfermedadDTO();
        enfermedadVacia.setNombreDeEnfermedad("Selecciona una enfermedad");
        comboEnfermedad1.addItem(enfermedadVacia);
        comboEnfermedad2.addItem(enfermedadVacia);
        for (EnfermedadDTO enfermedad : enfermedades) {
            comboEnfermedad1.addItem(enfermedad);
            comboEnfermedad2.addItem(enfermedad);
        }
    }
    
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIAtenderCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIAtenderCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIAtenderCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIAtenderCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIAtenderCita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaMotivo;
    private javax.swing.JTextArea areaTratamiento;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JComboBox<EnfermedadDTO> comboEnfermedad1;
    private javax.swing.JComboBox<EnfermedadDTO> comboEnfermedad2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNombreDueno;
    private javax.swing.JLabel labelNombreMascota;
    // End of variables declaration//GEN-END:variables
}
