package veterinaria.businesslogic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veterinaria.dataaccess.DBConnection;

public class LoginDAO {
    
    private static final Logger logger = LogManager.getLogger(LoginDAO.class);

    public int getUsuarioID(String nombre, String contrasena) {
        String query = "SELECT  BuscarUsuario(?, SHA2(?, 256))";
        int encontrado = 0;
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, contrasena);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                encontrado = result.getInt(1);
            }
            
        } catch (SQLException e) {
            logger.error("Error al buscar usuario: ", e);
        }
        
        return encontrado;
    }
    
}
