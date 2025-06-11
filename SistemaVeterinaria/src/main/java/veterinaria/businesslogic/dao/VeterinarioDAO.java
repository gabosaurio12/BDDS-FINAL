package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.VeterinarioDTO;
import veterinaria.dataaccess.DBConnection;

public class VeterinarioDAO {

    private static final Logger logger = LogManager.getLogger(VeterinarioDAO.class);
    
    private String generarNombreDeUsuario() {
        String sql = "SELECT nombreDeUsuario FROM veterinario WHERE nombreDeUsuario LIKE 'VET-%' ORDER BY CAST(SUBSTRING(nombreDeUsuario, 5) AS UNSIGNED) DESC LIMIT 1";
        
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String ultimoNombre = rs.getString("nombreDeUsuario");
                    String numeroStr = ultimoNombre.substring(4); 
                    int ultimoNumero = Integer.parseInt(numeroStr);
                    int siguienteNumero = ultimoNumero + 1;
                    
                    return String.format("VET-%03d", siguienteNumero);
                } else {
                    return "VET-001";
                }
            }
        } catch (SQLException | NumberFormatException e) {
            logger.error("Error al generar nombre de usuario, usando VET-001 por defecto: ", e);
            return "VET-001";
        }
    }

    public boolean insertarVeterinario(VeterinarioDTO veterinario) {
        String nombreUsuarioGenerado = generarNombreDeUsuario();
        String sqlAgenda = "INSERT INTO Agenda () VALUES ()";
        String sqlInsert = "INSERT INTO veterinario (cedula, nombreCompleto, telefono, nombreDeUsuario, agendaID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmtAgenda = connection.prepareStatement(sqlAgenda, Statement.RETURN_GENERATED_KEYS)) {

            pstmtAgenda.executeUpdate();
            ResultSet generatedKeys = pstmtAgenda.getGeneratedKeys();
            int agendaId = -1;
            if (generatedKeys.next()) {
                agendaId = generatedKeys.getInt(1);
            } else {
                logger.error("Error al insertar veterinario: No se pudo obtener el ID de la agenda.");
                return false;
            }

            try (PreparedStatement pstmtInsert = connection.prepareStatement(sqlInsert)) {
                pstmtInsert.setInt(1, veterinario.getCedula());
                pstmtInsert.setString(2, veterinario.getNombreCompleto());
                pstmtInsert.setString(3, veterinario.getTelefono());
                pstmtInsert.setString(4, nombreUsuarioGenerado);
                pstmtInsert.setInt(5, agendaId);

                int filasAfectadas = pstmtInsert.executeUpdate();

                if (filasAfectadas > 0) {
                    logger.info("Veterinario insertado exitosamente con nombre de usuario: {}", nombreUsuarioGenerado);
                    veterinario.setNombreDeUsuario(nombreUsuarioGenerado);
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Error al insertar veterinario: ", e);
        }
        return false;
    }


    public VeterinarioDTO seleccionarVeterinarioPorCedula(int cedula) {
        String sql = "SELECT * FROM veterinario WHERE cedula = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cedula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new VeterinarioDTO(
                            rs.getInt("cedula"),
                            rs.getString("nombreCompleto"),
                            rs.getString("telefono"),
                            rs.getString("nombreDeUsuario"),
                            rs.getInt("idAgenda")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar veterinario por cédula: ", e);
        }
        return null;
    }

    public List<VeterinarioDTO> seleccionarTodosLosVeterinarios() {
        List<VeterinarioDTO> listaVeterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listaVeterinarios.add(new VeterinarioDTO(
                        rs.getInt("cedula"),
                        rs.getString("nombreCompleto"),
                        rs.getString("telefono"),
                        rs.getString("nombreDeUsuario"),
                        rs.getInt("agendaId"))
                );
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todos los veterinarios: ", e);
        }
        return listaVeterinarios;
    }

    public boolean actualizarVeterinario(VeterinarioDTO veterinario) {
    String sql = "UPDATE veterinario SET nombreCompleto = ?, telefono = ? WHERE cedula = ?";
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {

        pstmt.setString(1, veterinario.getNombreCompleto());
        pstmt.setString(2, veterinario.getTelefono());
        pstmt.setInt(3, veterinario.getCedula()); 

        int filasAfectadas = pstmt.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        logger.error("Error al actualizar veterinario: ", e);
        return false;
    }
}


    public boolean eliminarVeterinario(int cedula) {
        String sql = "DELETE FROM veterinario WHERE cedula = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cedula);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar veterinario: ", e);
            return false;
        }
    }
}