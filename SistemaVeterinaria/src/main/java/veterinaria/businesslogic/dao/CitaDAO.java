package veterinaria.businesslogic.dao;

import veterinaria.businesslogic.dto.CitaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import veterinaria.businesslogic.dto.ConsultarCitaDTO;
import veterinaria.dataaccess.DBConnection;

public class CitaDAO {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(CitaDAO.class);

    public CitaDAO() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException ex) {
            logger.error("Error al conectarse a la BD", ex);
        }
    }

    public boolean insertarCita(CitaDTO cita) {
        String sql = "INSERT INTO cita (motivoDeConsulta, "
                + "estadoDeCita, idMascota, idDueno, idAgenda, "
                + "idFechaHora) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cita.getMotivoDeConsulta());
            stmt.setString(2, cita.getEstadoDeCita());
            stmt.setInt(3, cita.getIdMascota());
            stmt.setInt(4, cita.getIdDueno());
            stmt.setInt(5, cita.getIdAgenda());
            stmt.setInt(6, cita.getIdFechaHora());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error en CitaDAO.insertarCita", e);
            return false;
        }
    }

    public boolean actualizarCita(CitaDTO cita) {
        String sql = "UPDATE cita SET tratamiento = ?, motivoDeConsulta = ?, "
                + "estadoDeCita = ?, idMascota = ?, idDueno = ?, "
                + "idAgenda = ?, idFechaHora, "
                + "idEnfemedad = ? WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cita.getTratamiento());
            stmt.setString(2, cita.getMotivoDeConsulta());
            stmt.setString(3, cita.getEstadoDeCita());
            stmt.setInt(4, cita.getIdMascota());
            stmt.setInt(5, cita.getIdDueno());
            stmt.setInt(6, cita.getIdAgenda());
            stmt.setInt(7, cita.getIdEnfermedad());
            stmt.setInt(8, cita.getIdFechaHora());

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
                        rs.getInt("idMascota"),
                        rs.getInt("idDueno"),
                        rs.getInt("idAgenda"),
                        rs.getInt("idFechaHora"),
                        rs.getInt("idEnfermedad")
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
                        rs.getInt("idMascota"),
                        rs.getInt("idDueno"),
                        rs.getInt("idAgenda"),
                        rs.getInt("idFechaHora"),
                        rs.getInt("idEnfermedad")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error en CitaDAO.obtenerTodasLasCitas", e);
        }
        return lista;
    }
    
    public List<CitaDTO> buscarPorIdDueno(int idDueno) {
        String query = "SELECT * FROM Cita WHERE idDueno = ?";
        List<CitaDTO> citas = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDueno);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (citas == null) {
                    citas = new ArrayList<>();
                }
                CitaDTO cita = new CitaDTO();
                cita.setEstadoDeCita(result.getString("estadoDeCita"));
                cita.setIdAgenda(result.getInt("idAgenda"));
                cita.setIdCita(result.getInt("idCita"));
                cita.setIdDueno(result.getInt("idDueno"));
                cita.setIdFechaHora(result.getInt("idFechaHora"));
                cita.setIdMascota(result.getInt("idMascota"));
                cita.setMotivoDeConsulta(result.getString("motivoDeConsulta"));
                cita.setTratamiento(result.getString("tratamiento"));
                cita.setIdEnfermedad(result.getInt("idEnfermedad"));
                
                citas.add(cita);
            }
        } catch (SQLException e) {
            logger.error("Error al recopilar citas: ", e);
        }
        
        return citas;
    }
    
    public List<ConsultarCitaDTO> obtenerCitasFiltradas() {
        String query = "SELECT " +
                "v.nombreCompleto AS nombreVeterinario, " +
                "m.nombre AS nombreMascota, " +
                "d.nombreCompleto AS nombreDueno, " +
                "c.motivoDeConsulta, " +
                "c.idCita, " +
                "c.tratamiento, " +
                "c.estadoDeCita, " +
                "f.fecha, " +
                "h.hora " +
                "FROM Cita c " +
                "JOIN Mascota m ON c.idMascota = m.idMascota " +
                "JOIN Dueno d ON m.idDuenio = d.idDuenio " +
                "JOIN Veterinario v ON c.idAgenda = v.agendaID " +
                "JOIN FechaHora fh ON c.idFechaHora = fh.idFechaHora " +
                "JOIN Fecha f ON fh.idFecha = f.idFecha " +
                "JOIN Hora h ON fh.idHora = h.idHora";
        try (Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            List<ConsultarCitaDTO> consultas = new ArrayList<>();
            while (result.next()) {
                ConsultarCitaDTO consulta = new ConsultarCitaDTO();
                consulta.setEstadoCita(result.getString("estadoDeCita"));
                consulta.setMotivoConsulta(result.getString("motivoDeConsulta"));
                consulta.setNombreDueno(result.getString("nombreDueno"));
                consulta.setNombreMascota(result.getString("nombreMascota"));
                consulta.setNombreVeterinario(result.getString("nombreVeterinario"));
                consulta.setTratamiento(result.getString("tratamiento"));
                consulta.setFecha(result.getDate("fecha"));
                consulta.setIdCita(result.getInt("idCita"));
                
                consultas.add(consulta);
            }
            
            return consultas;
            
        } catch (SQLException ex) {
            logger.error("Error al obtener todas las citas filtradas: ", ex);
        }
        
        return null;
    }
    
    public ConsultarCitaDTO obtenerCitaFiltradaPorId(int id) {
        String query = "SELECT " +
                "v.nombreCompleto AS nombreVeterinario, " +
                "m.nombre AS nombreMascota, " +
                "d.nombreCompleto AS nombreDueno, " +
                "c.motivoDeConsulta, " +
                "c.idCita, " +
                "c.tratamiento, " +
                "c.estadoDeCita, " +
                "f.fecha, " +
                "h.hora " +
                "FROM Cita c " +
                "JOIN Mascota m ON c.idMascota = m.idMascota " +
                "JOIN Dueno d ON m.idDuenio = d.idDuenio " +
                "JOIN Veterinario v ON c.idAgenda = v.agendaID " +
                "JOIN FechaHora fh ON c.idFechaHora = fh.idFechaHora " +
                "JOIN Fecha f ON fh.idFecha = f.idFecha " +
                "JOIN Hora h ON fh.idHora = h.idHora " +
                "WHERE c.idCita = ?";
        ConsultarCitaDTO cita = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                cita = new ConsultarCitaDTO();
                cita.setEstadoCita("estadoDeCita");
                cita.setFecha(result.getDate("fecha"));
                cita.setIdCita(result.getInt("idCita"));
                cita.setMotivoConsulta(result.getString("motivoDeConsulta"));
                cita.setTratamiento("tratamiento");
                cita.setNombreDueno(result.getString("nombreDueno"));
                cita.setNombreMascota(result.getString("nombreMascota"));
                cita.setNombreVeterinario(result.getString("nombreVeterianario"));
            }            
        } catch (SQLException e) {
            logger.error("Error al recuperar cita filtrada por id: ", e);
        }
        
        return cita;
    }
    
    public int concluirCita(CitaDTO cita) {
        String query = "UPDATE Cita "
                + "SET motivoDeConsulta = ?, tratamiento = ?, estadoDeCita = ? "
                + "WHERE idCita = ?";
        int filasAfectadas = 0;
        try (Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cita.getMotivoDeConsulta());
            statement.setString(2, cita.getTratamiento());
            statement.setString(3, "Concluida");
            statement.setInt(4, cita.getIdCita());
            filasAfectadas = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error al concluir cita: ", e);
        }
        return filasAfectadas;
    } 
    
    public int insertarCitaEnfermedad(int idCita, int idEnfermedad) {
        String query = "INSERT INTO cita_enfermedad (idCita, idEnfermedad) "
                + "VALUES (?, ?)";
        int filasAfectadas = 0;
        try (Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idCita);
            statement.setInt(2, idEnfermedad);
            filasAfectadas = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error("Error al insertar en cita enfermedad: ", ex);
        }
        return filasAfectadas;
    }
}