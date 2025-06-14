package veterinaria.businesslogic.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.MascotaDTO;
import veterinaria.dataaccess.DBConnection;

public class MascotaDAO {
    private static final Logger logger = LogManager.getLogger(MascotaDAO.class);
    
    public int insertar(MascotaDTO mascota) {
        String sql = "INSERT INTO mascota (nombre, fechaDeNacimiento, color, peso, idDuenio, especie) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, mascota.getNombre());
            stmt.setDate(2, mascota.getFechaDeNacimiento());
            stmt.setString(3, mascota.getColor());
            stmt.setDouble(4, mascota.getPeso());
            stmt.setInt(5, mascota.getIdDuenio());
            stmt.setString(6, mascota.getEspecie());

            int filas = stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    mascota.setIdMascota(rs.getInt(1));
                }
            } catch (SQLException e) {
                logger.error("Error al insertar en MascotaDAO.insertar", e);
            }
            
            return filas;
        } catch (SQLException e) {
            logger.error("Error en MascotaDAO.insertar", e);
        }
        return 0;
    }

    public int actualizar(MascotaDTO mascota) {
        String sql = "UPDATE mascota SET nombre = ?, fechaDeNacimiento = ?, color = ?, peso = ?, idDuenio = ?, especie = ? WHERE idMascota = ?";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, mascota.getNombre());
            stmt.setDate(2, mascota.getFechaDeNacimiento());
            stmt.setString(3, mascota.getColor());
            stmt.setDouble(4, mascota.getPeso());
            stmt.setInt(5, mascota.getIdDuenio());
            stmt.setString(6, mascota.getEspecie());
            stmt.setInt(7, mascota.getIdMascota());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error en MascotaDAO.actualizar", e);
        }
        return 0;
    }

    public int eliminar(int idMascota) throws SQLException {
        String sql = "DELETE FROM mascota WHERE idMascota = ?";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, idMascota);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error en MascotaDAO.eliminar", e);
        }
        
        return 1;
    }

    public List<MascotaDTO> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM mascota";
        List<MascotaDTO> lista = new ArrayList<>();
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                MascotaDTO mascota = new MascotaDTO();
                mascota.setIdMascota(rs.getInt("idMascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setFechaDeNacimiento(rs.getDate("fechaDeNacimiento"));
                mascota.setColor(rs.getString("color"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setIdDuenio(rs.getInt("idDuenio"));
                mascota.setEspecie(rs.getString("especie"));
                lista.add(mascota);
            }
        } catch (SQLException e) {
            logger.error("Error en MascotaDAO.obtenerTodos", e);
        }
        return lista;
    }

    public MascotaDTO obtenerPorId(int idMascota) throws SQLException {
        String sql = "SELECT * FROM mascota WHERE idMascota = ?";
        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, idMascota);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MascotaDTO mascota = new MascotaDTO();
                    mascota.setIdMascota(rs.getInt("idMascota"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setFechaDeNacimiento(rs.getDate("fechaDeNacimiento"));
                    mascota.setColor(rs.getString("color"));
                    mascota.setPeso(rs.getDouble("peso"));
                    mascota.setIdDuenio(rs.getInt("idDuenio"));
                    mascota.setEspecie("especie");
                    return mascota;
                }
            } catch (SQLException e) {
                logger.error("Error al obtener resultados en MascotaDAO.obtenerPorID", e);
            }
        } catch (SQLException e) {
            logger.error("Error en MascotaDAO.obtenerPorID", e);
        }
        return null;
    }
}