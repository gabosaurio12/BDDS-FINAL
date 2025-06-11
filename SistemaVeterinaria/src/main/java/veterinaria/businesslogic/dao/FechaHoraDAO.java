package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.dataaccess.DBConnection;

public class FechaHoraDAO {
    
    private static final Logger logger = LogManager.getLogger(FechaHoraDAO.class);

    public int getFechaIdPorFechaHora(Date fecha, String hora, int agenda) {
        String query = "SELECT fh.idFechaHora " +
                "FROM FechaHora fh " +
                "JOIN Fecha f " +
                "ON fh.idFecha = f.idFecha " +
                "JOIN Hora h " +
                "ON fh.idHora = h.idHora " +
                "JOIN Agenda a " +
                "ON fh.idAgenda = a.idAgenda " +
                "WHERE f.fecha = ? " +
                "AND h.hora = ? " +
                "AND a.idAgenda = ?;";
        
        int fechaId = 0;
        try (Connection connection = DBConnection.getInstance().getConnection();
               PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, fecha);
            statement.setString(2, hora);
            statement.setInt(3, agenda);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fechaId = result.getInt(1);
            }
            
        } catch (SQLException ex) {
        logger.error("Error al recuperar id de FechaHora", ex);
        }
        
        return fechaId;
    }
    
}
