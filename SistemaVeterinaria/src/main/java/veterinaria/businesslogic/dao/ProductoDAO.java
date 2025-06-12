package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.ProductoDTO;
import veterinaria.dataaccess.DBConnection;

public class ProductoDAO {

    private static final Logger logger = LogManager.getLogger(ProductoDAO.class);

    public boolean insertarProducto(ProductoDTO producto) {
        String sql = "INSERT INTO producto (nombre, existencia, marca, precio, tipo, especie) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setObject(2, producto.getExistencia());
            pstmt.setString(3, producto.getMarca());
            pstmt.setBigDecimal(4, producto.getPrecio());
            pstmt.setString(5, producto.getTipo());
            pstmt.setString(6, producto.getEspecie());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al insertar producto: ", e);
            return false;
        }
    }

    public ProductoDTO seleccionarProductoPorId(int idProducto) {
        String sql = "SELECT * FROM producto WHERE idProducto = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductoDTO(
                            rs.getInt("idProducto"),
                            rs.getString("nombre"),
                            (Integer) rs.getObject("existencia"),
                            rs.getString("marca"),
                            rs.getBigDecimal("precio"),
                            rs.getString("tipo"),
                            rs.getString("especie")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar producto por ID: ", e);
        }
        return null;
    }

    public static List<ProductoDTO> seleccionarTodosLosProductos() {
        List<ProductoDTO> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaProductos.add(new ProductoDTO(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        (Integer) rs.getObject("existencia"),
                        rs.getString("marca"),
                        rs.getBigDecimal("precio"),
                        rs.getString("tipo"),
                        rs.getString("especie")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todos los productos: ", e);
        }
        return listaProductos;
    }

    public boolean actualizarProducto(ProductoDTO producto) {
        String sql = "UPDATE producto SET nombre = ?, existencia = ?, marca = ?, precio = ?, tipo = ?, especie = ? WHERE idProducto = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setObject(2, producto.getExistencia());
            pstmt.setString(3, producto.getMarca());
            pstmt.setBigDecimal(4, producto.getPrecio());
            pstmt.setString(5, producto.getTipo());
            pstmt.setString(6, producto.getEspecie());
            pstmt.setInt(7, producto.getIdProducto());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar producto: ", e);
            return false;
        }
    }

    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM producto WHERE idProducto = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar producto: ", e);
            return false;
        }
    }
    
    public List<ProductoDTO> buscarProductosPorNombre(String nombre) {
        List<ProductoDTO> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaProductos.add(new ProductoDTO(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        (Integer) rs.getObject("existencia"),
                        rs.getString("marca"),
                        rs.getBigDecimal("precio"),
                        rs.getString("tipo"),
                        rs.getString("especie")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error al buscar productos por nombre: ", e);
        }
        return listaProductos;
    }
}