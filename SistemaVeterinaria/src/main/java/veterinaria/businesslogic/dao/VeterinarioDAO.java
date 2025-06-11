package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.VeterinarioDTO;
import veterinaria.dataaccess.DBConnection;

public class VeterinarioDAO {

    private static final Logger logger = LogManager.getLogger(VeterinarioDAO.class);

    public int insertarVeterinario(VeterinarioDTO veterinario) {
        int filasAfectadas = 1;
        String queryAgenda = "INSERT INTO Agenda VALUES ()";
        
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(queryAgenda, RETURN_GENERATED_KEYS)) {
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                int agenda = result.getInt(1);
                String insertQuery = "INSERT INTO veterinario (cedula, "
                        + "nombreCompleto, telefono, nombreDeUsuario, "
                        + "contrasenia, agendaID) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                insertStatement.setInt(1, veterinario.getCedula());
                insertStatement.setString(2, veterinario.getNombreCompleto());
                insertStatement.setObject(3, veterinario.getTelefono());
                insertStatement.setString(4, veterinario.getNombreDeUsuario());
                insertStatement.setString(5, veterinario.getContrasenia());
                insertStatement.setInt(6, agenda);
                filasAfectadas = insertStatement.executeUpdate();
            }            
            
        } catch (SQLException e) {
            logger.error("Error al insertar veterinario: ", e);
            filasAfectadas = 0;
        }
        
        return filasAfectadas;
    }

    public VeterinarioDTO seleccionarVeterinarioPorCedula(int cedula) {
        String sql = "SELECT * FROM veterinarios WHERE cedula = ?";
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
                            rs.getString("contrasenia"),
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
                        rs.getString("contrasenia"),
                        rs.getInt("agendaId"))
                );
            }
        } catch (SQLException e) {
            logger.error("Error al seleccionar todos los veterinarios: ", e);
        }
        return listaVeterinarios;
    }

    public boolean actualizarVeterinario(VeterinarioDTO veterinario) {
        String sql = "UPDATE veterinarios SET nombreCompleto = ?, telefono = ?, nombreDeUsuario = ?, contrasenia = ? WHERE cedula = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, veterinario.getNombreCompleto());
            pstmt.setObject(2, veterinario.getTelefono());
            pstmt.setString(3, veterinario.getNombreDeUsuario());
            pstmt.setString(4, veterinario.getContrasenia());
            pstmt.setInt(5, veterinario.getCedula());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar veterinario: ", e);
            return false;
        }
    }

    public boolean eliminarVeterinario(int cedula) {
        String sql = "DELETE FROM veterinarios WHERE cedula = ?";
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