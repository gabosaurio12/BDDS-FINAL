package veterinaria.businesslogic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.dataaccess.DBConnection;

public class AgendaDAO {
    
    private static final Logger logger = LogManager.getLogger(AgendaDAO.class);
    
    public List<String> obtenerCitasDisponibles(Date fecha, int idAgenda) {
        String query = "SELECT " +
                "h.hora " +
                "FROM Fecha f " +
                "JOIN FechaHora fh ON f.idFecha = fh.idFecha " +
                "JOIN Hora h ON fh.idHora = h.idHora " +
                "WHERE fh.idAgenda = ? AND f.fecha = ? AND disponible = 1;";
        List<String> horas = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
                ) {
            statement.setInt(1, idAgenda);
            statement.setDate(2, fecha);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (horas == null) {
                    horas = new ArrayList<>();
                }
                horas.add(result.getString("hora"));
            }
           
            result.close();
        } catch (SQLException e) {
            logger.error("Error al recuperar horas disponibles: ", e);
        }
        return horas;
    }
}
