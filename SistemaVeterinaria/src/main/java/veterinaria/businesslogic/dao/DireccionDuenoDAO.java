package veterinaria.businesslogic.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.businesslogic.dto.DireccionDuenoDTO;
import veterinaria.dataaccess.DBConnection;

public class DireccionDuenoDAO {
    private static final Logger logger = LogManager.getLogger(DireccionDuenoDAO.class);

    public static final String ID_CASA = "idCasa";
    public static final String CALLE = "calle";
    public static final String COLONIA = "colonia";
    public static final String NUMERO = "numero";
    public static final String ID_DUENIO = "idDuenio";

    public boolean insertarDireccion(DireccionDuenoDTO direccion) throws SQLException {
        String sql = "INSERT INTO direccion_dueno (calle, colonia, numeroDeCasa, idDuenio) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, direccion.getCalle());
            stmt.setString(2, direccion.getColonia());
            stmt.setString(3, direccion.getNumero());
            stmt.setInt(4, direccion.getIdDuenio());

            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        direccion.setIdCasa(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            logger.error("Error al insertar dirección", e);
            return false;
        }
    }

    public boolean actualizarDireccion(DireccionDuenoDTO direccion) throws SQLException{
        String sql = "UPDATE direccion_dueno SET calle = ?, colonia = ?, numero = ?, idDuenio = ? WHERE idCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, direccion.getCalle());
            stmt.setString(2, direccion.getColonia());
            stmt.setString(3, direccion.getNumero());
            stmt.setInt(4, direccion.getIdDuenio());
            stmt.setInt(5, direccion.getIdCasa());

            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            logger.error("Error al actualizar dirección", e);
            return false;
        }
    }

    public boolean eliminarDireccion(int idCasa)throws SQLException {
        String sql = "DELETE FROM direccion_dueno WHERE idCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idCasa);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            logger.error("Error al eliminar dirección", e);
            return false;
        }
    }

    public DireccionDuenoDTO obtenerDireccionPorId(int idCasa)throws SQLException {
        String sql = "SELECT * FROM direccion_dueno WHERE idCasa = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idCasa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DireccionDuenoDTO(
                        rs.getInt(ID_CASA),
                        rs.getString(CALLE),
                        rs.getString(COLONIA),
                        rs.getString(NUMERO),
                        rs.getInt(ID_DUENIO)
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener dirección por ID", e);
        }
        return null;
    }


    public List<DireccionDuenoDTO> obtenerDireccionesPorDuenio(int idDuenio) throws SQLException{
        List<DireccionDuenoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM direccion_dueno WHERE idDuenio = ?";
        
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idDuenio);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new DireccionDuenoDTO(
                        rs.getInt(ID_CASA),
                        rs.getString(CALLE),
                        rs.getString(COLONIA),
                        rs.getString(NUMERO),
                        rs.getInt(ID_DUENIO)
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener direcciones por dueño", e);
        }
        return lista;
    }


    public List<DireccionDuenoDTO> obtenerTodasLasDirecciones() throws SQLException{
        List<DireccionDuenoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM direccion_dueno";
        
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new DireccionDuenoDTO(
                    rs.getInt(ID_CASA),
                    rs.getString(CALLE),
                    rs.getString(COLONIA),
                    rs.getString(NUMERO),
                    rs.getInt(ID_DUENIO)
                ));
            }
        } catch (SQLException e) {
            logger.error("Error al obtener todas las direcciones", e);
        }
        return lista;
    }

    
    public boolean eliminarDireccionesPorDuenio(int idDuenio)throws SQLException {
        String sql = "DELETE FROM direccion_dueno WHERE idDuenio = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idDuenio);
            return stmt.executeUpdate() >= 0; 
            
        } catch (SQLException e) {
            logger.error("Error al eliminar direcciones por dueño", e);
            return false;
        }
    }
    
    public List<DireccionDuenoDTO> obtenerDireccionesPorDuenio(String idDuenio)throws SQLException {
    try {
        return obtenerDireccionesPorDuenio(Integer.parseInt(idDuenio));
    } catch (NumberFormatException e) {
        logger.error("Error al convertir idDuenio a entero: " + idDuenio, e);
        return new ArrayList<>();
    }
}
}
