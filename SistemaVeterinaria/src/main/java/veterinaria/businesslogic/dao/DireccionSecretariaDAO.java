package veterinaria.businesslogic.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.DireccionSecretariaDTO;
import veterinaria.dataaccess.DBConnection;

public class DireccionSecretariaDAO {
    private static final Logger logger = LogManager.getLogger(DireccionSecretariaDAO.class);

    public boolean insertarDireccionSecretaria(DireccionSecretariaDTO direccionSecretaria) {
        String sql = "INSERT INTO direccion_secretaria (numeroDeCasa, calle, colonia, numeroDeINE) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, direccionSecretaria.getNumeroDeCasa());
            pstmt.setString(2, direccionSecretaria.getCalle());
            pstmt.setString(3, direccionSecretaria.getColonia());
            pstmt.setObject(4, direccionSecretaria.getNumeroDeINE());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al insertar dirección de secretaria: ", e);
            return false;
        }
    }

    public DireccionSecretariaDTO seleccionarDireccionSecretariaPorNumeroDeCasa(int numeroDeCasa) {
        String sql = "SELECT * FROM direccion_secretaria WHERE numeroDeCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, numeroDeCasa);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new DireccionSecretariaDTO(
                            rs.getInt("numeroDeCasa"),
                            rs.getString("calle"),
                            rs.getString("colonia"),
                            (Integer) rs.getObject("numeroDeINE")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar dirección de secretaria por número de casa: ", e);
        }
        return null;
    }

    public List<DireccionSecretariaDTO> seleccionarTodasLasDireccionesSecretaria() {
        List<DireccionSecretariaDTO> listaDirecciones = new ArrayList<>();
        String sql = "SELECT * FROM direccion_secretaria";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaDirecciones.add(new DireccionSecretariaDTO(
                        rs.getInt("numeroDeCasa"),
                        rs.getString("calle"),
                        rs.getString("colonia"),
                        (Integer) rs.getObject("numeroDeINE")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todas las direcciones de secretaria: ", e);
        }
        return listaDirecciones;
    }

    public boolean actualizarDireccionSecretaria(DireccionSecretariaDTO direccionSecretaria) {
        String sql = "UPDATE direccion_secretaria SET calle = ?, colonia = ?, numeroDeINE = ? WHERE numeroDeCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, direccionSecretaria.getCalle());
            pstmt.setString(2, direccionSecretaria.getColonia());
            pstmt.setObject(3, direccionSecretaria.getNumeroDeINE());
            pstmt.setInt(4, direccionSecretaria.getNumeroDeCasa());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar dirección de secretaria: ", e);
            return false;
        }
    }

    public boolean eliminarDireccionSecretaria(int numeroDeCasa) {
        String sql = "DELETE FROM direccion_secretaria WHERE numeroDeCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, numeroDeCasa);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar dirección de secretaria: ", e);
            return false;
        }
    }
}