package veterinaria.businesslogic.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.EnfermedadDTO;
import veterinaria.dataaccess.DBConnection;

public class EnfermedadDAO {

    private final Logger logger = LogManager.getLogger(EnfermedadDAO.class);

    public boolean insertarEnfermedad(EnfermedadDTO enfermedad) {
        String sql = "INSERT INTO enfermedad (idEnfermedad, nombreDeEnfermedad, clasificacionDeEspecie) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, enfermedad.getIdEnfermedad());
            stmt.setString(2, enfermedad.getNombreDeEnfermedad());
            stmt.setString(3, enfermedad.getClasificacionDeEspecie());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error en insertarEnfermedad: ", e);
            return false;
        }
    }

    public boolean actualizarEnfermedad(EnfermedadDTO enfermedad) {
        String sql = "UPDATE enfermedad SET nombreDeEnfermedad = ?, clasificacionDeEspecie = ? WHERE idEnfermedad = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, enfermedad.getNombreDeEnfermedad());
            stmt.setString(2, enfermedad.getClasificacionDeEspecie());
            stmt.setInt(3, enfermedad.getIdEnfermedad());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error en actualizarEnfermedad: ", e);
            return false;
        }
    }

    public boolean eliminarEnfermedad(int idEnfermedad) {
        String sql = "DELETE FROM enfermedad WHERE idEnfermedad = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEnfermedad);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error en eliminarEnfermedad: ", e);
            return false;
        }
    }

    public EnfermedadDTO obtenerEnfermedadPorId(int idEnfermedad) {
        String sql = "SELECT * FROM enfermedad WHERE idEnfermedad = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEnfermedad);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EnfermedadDTO(
                    rs.getInt("idEnfermedad"),
                    rs.getString("nombreDeEnfermedad"),
                    rs.getString("clasificacionDeEspecie")
                );
            }
        } catch (SQLException e) {
            logger.error("Error en obtenerEnfermedadPorId: ", e);
        }
        return null;
    }

    public List<EnfermedadDTO> obtenerTodasLasEnfermedades() {
        List<EnfermedadDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM enfermedad";
        try (Connection connection = DBConnection.getInstance().getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new EnfermedadDTO(
                    rs.getInt("idEnfermedad"),
                    rs.getString("nombreDeEnfermedad"),
                    rs.getString("clasificacionDeEspecie")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error en obtenerTodasLasEnfermedades: ", e);
        }
        return lista;
    }
}