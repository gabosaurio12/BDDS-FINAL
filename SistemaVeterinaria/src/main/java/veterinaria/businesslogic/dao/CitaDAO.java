package veterinaria.businesslogic.dao;

import veterinaria.businesslogic.dto.CitaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import veterinaria.dataaccess.DBConnection;

public class CitaDAO {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(CitaDAO.class);

    public CitaDAO(DBConnection connection) {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException ex) {
            logger.error("Error al conectarse a la BD", ex);
        }
    }

    public boolean insertarCita(CitaDTO cita) {
        String sql = "INSERT INTO cita (idCita, tratamiento, motivoDeConsulta, estadoDeCita, numeroDeINE, idMascota, cedula) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cita.getIdCita());
            stmt.setString(2, cita.getTratamiento());
            stmt.setString(3, cita.getMotivoDeConsulta());
            stmt.setString(4, cita.getEstadoDeCita());
            stmt.setInt(5, cita.getNumeroDeINE());
            stmt.setInt(6, cita.getIdMascota());
            stmt.setInt(7, cita.getCedula());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error en CitaDAO.insertarCita", e);
            return false;
        }
    }

    public boolean actualizarCita(CitaDTO cita) {
        String sql = "UPDATE cita SET tratamiento = ?, motivoDeConsulta = ?, estadoDeCita = ?, numeroDeINE = ?, idMascota = ?, cedula = ? WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cita.getTratamiento());
            stmt.setString(2, cita.getMotivoDeConsulta());
            stmt.setString(3, cita.getEstadoDeCita());
            stmt.setInt(4, cita.getNumeroDeINE());
            stmt.setInt(5, cita.getIdMascota());
            stmt.setInt(6, cita.getCedula());
            stmt.setInt(7, cita.getIdCita());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error en CitaDAO.actualizarCita", e);
            return false;
        }
    }

    public boolean eliminarCita(int idCita) {
        String sql = "DELETE FROM cita WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCita);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error CitaDAO.eliminarCita", e);
            return false;
        }
    }

    public CitaDTO obtenerCitaPorId(int idCita) {
        String sql = "SELECT * FROM cita WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCita);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CitaDTO(
                    rs.getInt("idCita"),
                    rs.getString("tratamiento"),
                    rs.getString("motivoDeConsulta"),
                    rs.getString("estadoDeCita"),
                    rs.getInt("numeroDeINE"),
                    rs.getInt("idMascota"),
                    rs.getInt("cedula")
                );
            }
        } catch (SQLException e) {
            logger.error("Error en CitaDAO.obtenerCitaPorId", e);
        }
        return null;
    }

    public List<CitaDTO> obtenerTodasLasCitas() {
        List<CitaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new CitaDTO(
                    rs.getInt("idCita"),
                    rs.getString("tratamiento"),
                    rs.getString("motivoDeConsulta"),
                    rs.getString("estadoDeCita"),
                    rs.getInt("numeroDeINE"),
                    rs.getInt("idMascota"),
                    rs.getInt("cedula")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error en CitaDAO.obtenerTodasLasCitas", e);
        }
        return lista;
    }
}