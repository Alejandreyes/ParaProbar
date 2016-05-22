package Controlador.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public abstract class AbstractDao {
    private Connection conexion;
    public void conectar() throws SQLException{
        try {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/BaulV2";
            String user = "postgres";
            String password = "postgres";
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user , password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    public void desconectar(){
        if (conexion != null){
            try {
                if (!conexion.isClosed()){
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

 
}