package veterinaria.view;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import veterinaria.businesslogic.dao.DireccionDuenoDAO;
import veterinaria.businesslogic.dao.DuenoDAO;
import veterinaria.businesslogic.dao.ProductoDAO;
import veterinaria.businesslogic.dto.DireccionDuenoDTO;
import veterinaria.businesslogic.dto.DuenoDTO;
import veterinaria.businesslogic.dto.ProductoDTO;


/**
 *
 * @author User
 */
public class GUICRUDProducto extends javax.swing.JFrame {

    private static final Logger logger = LogManager.getLogger(GUICRUDProducto.class);
    public GUICRUDProducto() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        botonRegistrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonRegresar = new javax.swing.JButton();
        textNombreBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Productos");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Existencia", "Marca", "Precio", "Especie"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

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

        jLabel2.setText("Nombre:");

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(textNombreBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonBuscar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(botonRegistrar)
                                .addComponent(botonEliminar)
                                .addComponent(botonEditar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonRegresar)
                                .addGap(6, 6, 6)))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombreBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(botonEditar)
                        .addGap(18, 18, 18)
                        .addComponent(botonEliminar)
                        .addGap(162, 162, 162)
                        .addComponent(botonRegresar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        GUIRegistroProducto controlador = new GUIRegistroProducto();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea eliminar este producto?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int idProducto = (Integer) tablaProductos.getValueAt(filaSeleccionada, 0);

            ProductoDAO productoDAO = new ProductoDAO();
            boolean eliminado = productoDAO.eliminarProducto(idProducto);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.");
                llenarTablaProductos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto.");
            }

        } catch (Exception ex) {
            logger.error("Error al eliminar producto", ex);
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + ex.getMessage());
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        int filaSeleccionada = tablaProductos.getSelectedRow();
    
        if (filaSeleccionada != -1) {
            try {
                int idProducto = (Integer) tablaProductos.getValueAt(filaSeleccionada, 0);

                ProductoDAO productoDAO = new ProductoDAO();
                ProductoDTO producto = productoDAO.seleccionarProductoPorId(idProducto);

                if (producto == null) {
                    JOptionPane.showMessageDialog(this, "No se pudo cargar la información del producto.");
                    return;
                }

                GUIRegistroProducto ventanaEdicion = new GUIRegistroProducto(producto);
                ventanaEdicion.setVisible(true);
                this.dispose(); 

            } catch (Exception ex) {
                logger.error("Error en botonEditarActionPerformed", ex);
                JOptionPane.showMessageDialog(this, "Error al cargar datos del producto: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto para editar.");
        }

    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        GUIMenuPrincipal controlador = new GUIMenuPrincipal();
        controlador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
            String nombreBusqueda = textNombreBuscar.getText().trim();

        // Validar que el campo no esté vacío
        if (nombreBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre para buscar.");
            textNombreBuscar.requestFocus();
            return;
        }

        try {
            ProductoDAO productoDAO = new ProductoDAO();
            List<ProductoDTO> productosEncontrados = productoDAO.buscarProductosPorNombre(nombreBusqueda);

            if (productosEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron productos que contengan: " + nombreBusqueda);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Se encontraron " + productosEncontrados.size() + " producto(s).");
            }

            llenarTablaConProductos(productosEncontrados);

        } catch (Exception ex) {
            logger.error("Error al buscar productos", ex);
            JOptionPane.showMessageDialog(this, "Error al realizar la búsqueda: " + ex.getMessage());
        }
    }//GEN-LAST:event_botonBuscarActionPerformed
    
    public void llenarTablaProductos() {
        String[] columnas = {"ID", "Nombre", "Existencia", "Marca", "Precio", "Especie"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            ProductoDAO productoDAO = new ProductoDAO();
            List<ProductoDTO> productos = productoDAO.seleccionarTodosLosProductos();

            for (ProductoDTO producto : productos) {

                    Object[] fila = {
                        producto.getIdProducto(),
                        producto.getNombre(),
                        producto.getExistencia(),
                        producto.getMarca(),
                        producto.getPrecio(),
                        producto.getEspecie()
                        
                    };
                    modelo.addRow(fila);
                }

            tablaProductos.setModel(modelo);
            configurarSeleccionTabla();

        } catch (Exception ex) {
            logger.error("Error al llenar tabla de Productos", ex);
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage());
        }
    }
    
    private void configurarSeleccionTabla() {
        
        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean haySeleccion = tablaProductos.getSelectedRow() != -1;
                botonEliminar.setEnabled(haySeleccion);
                botonEditar.setEnabled(haySeleccion);
            }
        });

        botonEliminar.setEnabled(false);
        botonEditar.setEnabled(false);
    }
    
    private void llenarTablaConProductos(List<ProductoDTO> productos) {
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0);

        for (ProductoDTO producto : productos) {
            Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getExistencia(),
                producto.getMarca(),
                producto.getPrecio(),
                producto.getTipo(),
                producto.getEspecie()
            };
            model.addRow(fila);
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField textNombreBuscar;
    // End of variables declaration//GEN-END:variables
}
