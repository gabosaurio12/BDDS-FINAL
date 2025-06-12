package veterinaria.businesslogic.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.DuenoDTO;
import veterinaria.dataaccess.DBConnection;

public class DuenoDAO {
    private static final Logger logger = LogManager.getLogger(DuenoDAO.class);
    public static final String ID_DUENIO = "idDuenio";
    public static final String TELEFONO = "telefono";
    public static final String NOMBRE_COMPLETO = "nombreCompleto";
    public static final String EMAIL = "email";
    
    public int insertar(DuenoDTO dueno) throws SQLException {
        String sql = "INSERT INTO dueno (telefono, nombreCompleto, email, ine, "
                + "username, password) VALUES (?, ?, ?, ?, ?, SHA2(?, 256))";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, dueno.getTelefono());
            stmt.setString(2, dueno.getNombreCompleto());
            stmt.setString(3, dueno.getEmail());
            stmt.setString(4, dueno.getIne());
            String[] username = dueno.getEmail().split("@");
            stmt.setString(5, username[0]);
            stmt.setString(6, dueno.getTelefono());

            int filasAfectadas = stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    dueno.setIdDuenio(rs.getInt(1));
                }
            } catch (SQLException e) {
                logger.error("Error al obtener clave generada en DuenoDAO.insertar", e);
            }

            return filasAfectadas;
        } catch (SQLException e) {
            logger.error("Error en DuenoDAO.insertar", e);
        }
        
        return 1;
    }

    public int actualizar(DuenoDTO dueno) throws SQLException {
        String sql = "UPDATE dueno SET telefono = ?, nombreCompleto = ?, email = ? WHERE idDuenio = ?";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, dueno.getTelefono());
            stmt.setString(2, dueno.getNombreCompleto());
            stmt.setString(3, dueno.getEmail());
            stmt.setInt(4, dueno.getIdDuenio());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error en DuenoDAO.actualizar", e);
            throw e;
        }
    }

    public int eliminar(int idDuenio) throws SQLException {
        String sql = "DELETE FROM dueno WHERE idDuenio = ?";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, idDuenio);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error en DuenoDAO.eliminar", e);
        }
        
        return 1;
    }

    public List<DuenoDTO> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM dueno";
        List<DuenoDTO> lista = new ArrayList<>();
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                DuenoDTO dueno = new DuenoDTO();
                dueno.setIdDuenio(rs.getInt(ID_DUENIO));
                dueno.setTelefono(rs.getString(TELEFONO));
                dueno.setNombreCompleto(rs.getString(NOMBRE_COMPLETO));
                dueno.setEmail(rs.getString(EMAIL));
                lista.add(dueno);
            }
        } catch (SQLException e) {
            logger.error("Error en DuenoDAO.obtenerTodos", e);
        }
        return lista;
    }

    public DuenoDTO obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM dueno WHERE idDuenio = ?";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DuenoDTO dueno = new DuenoDTO();
                    dueno.setIdDuenio(rs.getInt("idDuenio"));
                    dueno.setTelefono(rs.getString("telefono"));
                    dueno.setNombreCompleto(rs.getString("nombreCompleto"));
                    dueno.setEmail(rs.getString("email"));
                    dueno.setIne(rs.getString("ine"));
                    dueno.setUsername("username");
                    dueno.setPassword("password");
                    
                    return dueno;
                }
            } catch (SQLException e) {
                logger.error("Error al obtener resultados en DuenoDAO.obtenerPorId", e);
            }
        } catch (SQLException e) {
            logger.error("Error en DuenoDAO.obtenerPorId", e);
        }
        return null;
    }
    
    public int obtenerDuenoPorTelefono(String telefono) {
        String query = "SELECT idDuenio FROM Dueno WHERE telefono = ?;";
        int id = 0;
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, telefono);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException e) {
                logger.error("Error al obtener resultados en DuenoDAO.obtenerDuenoPorTelefono", e);
            }
        } catch (SQLException e) {
            logger.error("Error en DuenoDAO.obtenerDuenoPorTelefono", e);
        }
        
        return id;
    }
    
    
}