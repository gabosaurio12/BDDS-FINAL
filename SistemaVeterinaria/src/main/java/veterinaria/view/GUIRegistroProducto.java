package veterinaria.view;

import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dao.ProductoDAO;
import veterinaria.businesslogic.dto.ProductoDTO;

/**
 *
 * @author User
 */
public class GUIRegistroProducto extends javax.swing.JFrame {
    
    private static final Logger logger = LogManager.getLogger(GUIRegistroProducto.class);
    private ProductoDTO productoAEditar; 
    private boolean modoEdicion;
    
    public GUIRegistroProducto() {
        initComponents();
        this.productoAEditar = null;
        this.modoEdicion = false;
        configurarVentana();
    }
    
    public GUIRegistroProducto(ProductoDTO producto) {
        initComponents();
        this.productoAEditar = producto;
        this.modoEdicion = true;
        configurarVentana();
        cargarDatosProducto();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        textExistencia = new javax.swing.JTextField();
        textMarca = new javax.swing.JTextField();
        textPrecio = new javax.swing.JTextField();
        textTipo = new javax.swing.JTextField();
        textEspecie = new javax.swing.JTextField();
        botonRegistrar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Registro de Productos");

        jLabel2.setText("Nombre");

        jLabel3.setText("Existencia");

        jLabel4.setText("Marca");

        jLabel5.setText("Precio");

        jLabel6.setText("Tipo");

        jLabel7.setText("Especie");

        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
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
                        .addGap(128, 128, 128)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textTipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botonRegistrar)
                                        .addGap(125, 125, 125)
                                        .addComponent(botonCancelar))
                                    .addComponent(textEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegistrar)
                    .addComponent(botonCancelar))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        try {
            if (!validarCampos()) {
                return;
            }
            
            ProductoDTO producto = crearProductoDesdeFormulario();
            
            ProductoDAO productoDAO = new ProductoDAO();
            boolean exito;
            
            if (modoEdicion) {
                producto.setIdProducto(productoAEditar.getIdProducto());
                exito = productoDAO.actualizarProducto(producto);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el producto.");
                    return;
                }
            } else {
                exito = productoDAO.insertarProducto(producto);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Producto registrado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el producto.");
                    return;
                }
            }
            
            if (modoEdicion) {
                this.dispose();
            }
            
        } catch (Exception ex) {
            logger.error("Error en botonRegistrarActionPerformed", ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void configurarVentana() {
        if (modoEdicion) {
            setTitle("Editar Producto");
            botonRegistrar.setText("Actualizar");
        } else {
            setTitle("Registrar Producto");
            botonRegistrar.setText("Registrar");
        }
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void cargarDatosProducto() {
        if (productoAEditar != null) {
            textNombre.setText(productoAEditar.getNombre());
            textExistencia.setText(productoAEditar.getExistencia() != null ? 
                                 productoAEditar.getExistencia().toString() : "");
            textMarca.setText(productoAEditar.getMarca());
            textPrecio.setText(productoAEditar.getPrecio() != null ? 
                             productoAEditar.getPrecio().toString() : "");
            textTipo.setText(productoAEditar.getTipo());
            textEspecie.setText(productoAEditar.getEspecie());
        }
    }
    
    private boolean validarCampos() {
        if (textNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del producto es obligatorio.");
            textNombre.requestFocus();
            return false;
        }
        
        if (!textExistencia.getText().trim().isEmpty()) {
            try {
                Integer.parseInt(textExistencia.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La existencia debe ser un número entero válido.");
                textExistencia.requestFocus();
                return false;
            }
        }
        
        if (!textPrecio.getText().trim().isEmpty()) {
            try {
                new java.math.BigDecimal(textPrecio.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
                textPrecio.requestFocus();
                return false;
            }
        }
        
        return true;
    }
    
    private ProductoDTO crearProductoDesdeFormulario() {
        
        ProductoDTO producto = new ProductoDTO();
        
        producto.setNombre(textNombre.getText().trim());
        
        String existenciaText = textExistencia.getText().trim();
        if (!existenciaText.isEmpty()) {
            producto.setExistencia(Integer.parseInt(existenciaText));
        } else {
            producto.setExistencia(null);
        }
        
        producto.setMarca(textMarca.getText().trim());
        
        String precioText = textPrecio.getText().trim();
        if (!precioText.isEmpty()) {
            producto.setPrecio(new java.math.BigDecimal(precioText));
        } else {
            producto.setPrecio(null);
        }
        
        producto.setTipo(textTipo.getText().trim());
        producto.setEspecie(textEspecie.getText().trim());
        
        return producto;
    
    }
    
    private void limpiarCampos() {
        textNombre.setText("");
        textExistencia.setText("");
        textMarca.setText("");
        textPrecio.setText("");
        textTipo.setText("");
        textEspecie.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField textEspecie;
    private javax.swing.JTextField textExistencia;
    private javax.swing.JTextField textMarca;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textPrecio;
    private javax.swing.JTextField textTipo;
    // End of variables declaration//GEN-END:variables
}
