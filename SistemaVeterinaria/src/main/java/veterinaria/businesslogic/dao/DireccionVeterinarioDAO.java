package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.DireccionVeterinarioDTO;
import veterinaria.dataaccess.DBConnection;

public class DireccionVeterinarioDAO {
    private final Logger logger = LogManager.getLogger(DireccionVeterinarioDAO.class);

    public boolean insertarDireccionVeterinario(DireccionVeterinarioDTO direccionVeterinario) throws SQLException {
        String sql = "INSERT INTO direccion_veterinario (calle, colonia, numeroDeCasa, cedula) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, direccionVeterinario.getCalle());
            pstmt.setString(2, direccionVeterinario.getColonia());
            pstmt.setString(3, direccionVeterinario.getNumero()); // Corregido: es String, no int
            pstmt.setObject(4, direccionVeterinario.getCedula());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        direccionVeterinario.setIdCasa(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            logger.error("Error al insertar dirección de veterinario", e);
            return false;
        }
    }

    public DireccionVeterinarioDTO seleccionarDireccionVeterinarioPorId(int idCasa) throws SQLException {
        String sql = "SELECT * FROM direccion_veterinario WHERE idCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, idCasa);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new DireccionVeterinarioDTO(
                            rs.getInt("idCasa"),
                            rs.getString("numeroDeCasa"),
                            rs.getString("calle"),
                            rs.getString("colonia"),
                            (Integer) rs.getObject("cedula")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar dirección de veterinario por número", e);
        }
        return null;
    }

    public List<DireccionVeterinarioDTO> seleccionarTodasLasDireccionesVeterinario() throws SQLException {
        List<DireccionVeterinarioDTO> listaDirecciones = new ArrayList<>();
        String sql = "SELECT * FROM direccion_veterinario";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaDirecciones.add(new DireccionVeterinarioDTO(
                        rs.getInt("idCasa"),
                        rs.getString("numeroDeCasa"),        
                        rs.getString("calle"),
                        rs.getString("colonia"),
                        (Integer) rs.getObject("cedula")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todas las direcciones de veterinario", e);
        }
        return listaDirecciones;
    }

    public boolean actualizarDireccionVeterinario(DireccionVeterinarioDTO direccionVeterinario) throws SQLException {
        String sql = "UPDATE direccion_veterinario SET calle = ?, colonia = ?, numeroDeCasa = ?, cedula = ? WHERE idCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, direccionVeterinario.getCalle());
            pstmt.setString(2, direccionVeterinario.getColonia());
            pstmt.setInt(3, direccionVeterinario.getIdCasa());
            pstmt.setObject(4, direccionVeterinario.getCedula());
            pstmt.setInt(5, direccionVeterinario.getIdCasa()); 

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error al actualizar dirección de veterinario", e);
            return false;
        }
    }

    public List<DireccionVeterinarioDTO> obtenerDireccionesPorCedula(int cedula) throws SQLException {
        List<DireccionVeterinarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM direccion_veterinario WHERE cedula = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new DireccionVeterinarioDTO(
                        rs.getInt("idCasa"),
                        rs.getString("numeroDeCasa"),
                        rs.getString("calle"),
                        rs.getString("colonia"),
                        (Integer) rs.getObject("cedula")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener direcciones por cédula", e);
        }
        return lista;
    }
}