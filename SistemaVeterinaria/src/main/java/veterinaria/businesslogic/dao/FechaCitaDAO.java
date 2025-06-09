package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.FechaCitaDTO;
import veterinaria.dataaccess.DBConnection;

public class FechaCitaDAO {

    private final Logger logger = LogManager.getLogger(FechaCitaDAO.class);

    public boolean insertarFechaCita(FechaCitaDTO fechaCita) {
        String sql = "INSERT INTO fechascitas (idCita, dia, mes, anio, hora) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, fechaCita.getIdCita());
            pstmt.setObject(2, fechaCita.getDia());
            pstmt.setObject(3, fechaCita.getMes());
            pstmt.setObject(4, fechaCita.getAnio());
            pstmt.setTime(5, fechaCita.getHora());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al insertar fecha de cita: ", e);
            return false;
        }
    }

    public FechaCitaDTO seleccionarFechaCitaPorId(int idCita) {
        String sql = "SELECT * FROM fechascitas WHERE idCita = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idCita);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new FechaCitaDTO(
                            rs.getInt("idCita"),
                            (Integer) rs.getObject("dia"),
                            (Integer) rs.getObject("mes"),
                            (Integer) rs.getObject("anio"),
                            rs.getTime("hora")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar fecha de cita por ID: ", e);
        }
        return null;
    }

    public List<FechaCitaDTO> seleccionarTodasLasFechasCitas() {
        List<FechaCitaDTO> listaFechasCitas = new ArrayList<>();
        String sql = "SELECT * FROM fechascitas";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaFechasCitas.add(new FechaCitaDTO(
                        rs.getInt("idCita"),
                        (Integer) rs.getObject("dia"),
                        (Integer) rs.getObject("mes"),
                        (Integer) rs.getObject("anio"),
                        rs.getTime("hora")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todas las fechas de citas: ", e);
        }
        return listaFechasCitas;
    }

    public boolean actualizarFechaCita(FechaCitaDTO fechaCita) {
        String sql = "UPDATE fechascitas SET dia = ?, mes = ?, anio = ?, hora = ? WHERE idCita = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, fechaCita.getDia());
            pstmt.setObject(2, fechaCita.getMes());
            pstmt.setObject(3, fechaCita.getAnio());
            pstmt.setTime(4, fechaCita.getHora());
            pstmt.setInt(5, fechaCita.getIdCita());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar fecha de cita: ", e);
            return false;
        }
    }

    public boolean eliminarFechaCita(int idCita) {
        String sql = "DELETE FROM fechascitas WHERE idCita = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idCita);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar fecha de cita: ", e);
            return false;
        }
    }
}