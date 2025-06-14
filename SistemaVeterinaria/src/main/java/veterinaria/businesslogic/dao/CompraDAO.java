package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.CompraDTO;
import veterinaria.dataaccess.DBConnection;

public class CompraDAO {
    private static final Logger logger = LogManager.getLogger(CompraDAO.class);

    public boolean insertarCompra(CompraDTO compra) {
        String sql = "INSERT INTO compra (idDuenio, idProducto) VALUES (?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, compra.getIdDuenio());
            pstmt.setInt(2, compra.getIdProducto());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al insertar compra: ", e);
            return false;
        }
    }

    public CompraDTO seleccionarCompraPorIds(int idDuenio, int idProducto) {
        String sql = "SELECT * FROM compra WHERE idDuenio = ? AND idProducto = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDuenio);
            pstmt.setInt(2, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CompraDTO(
                            rs.getInt("idDuenio"),
                            rs.getInt("idProducto")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar compra por IDs", e);
        }
        return null;
    }

    public List<CompraDTO> seleccionarTodasLasCompras() {
        List<CompraDTO> listaCompras = new ArrayList<>();
        String sql = "SELECT * FROM compra";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaCompras.add(new CompraDTO(
                        rs.getInt("idDuenio"),
                        rs.getInt("idProducto")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todas las compras", e);
        }
        return listaCompras;
    }

    public boolean eliminarCompra(int idDuenio, int idProducto) {
        String sql = "DELETE FROM compra WHERE idDuenio = ? AND idProducto = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDuenio);
            pstmt.setInt(2, idProducto);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar compra", e);
            return false;
        }
    }
    
    public int transaccionCompra(int idDueno, int idProducto) {
        String transaccion = "INSERT INTO Compra (idDueno, idProducto) VALUES (?, ?);";
        int filasAfectadas = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(transaccion);
            statement.setInt(1, idDueno);
            statement.setInt(2, idProducto);
            filasAfectadas = statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("Error la hacer transaccion en CompraDAO.transaccion", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("Error la hacer rollback en CompraDAO.transaccion", e);
                }
            }
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Error al cerrar recursos", e);
            }
        }
        
        return filasAfectadas;   
    }
}