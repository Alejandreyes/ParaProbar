package Controlador.dao;

import Modelo.Solicitar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolicitudDao extends AbstractDao{
    
    public Solicitar Buscar(int idPrestamo) throws DataAccessLayerException {
        Solicitar returnValue = null;
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Solicitar where idsolicitud = ?");
            consulta.setInt(1, idPrestamo);
            ResultSet rs = consulta.executeQuery();
            
            if (rs.next()){
                returnValue.setIdsolicitud(idPrestamo);
                UsuarioDao usdao= new UsuarioDao();
                ObjetoDao objdao= new ObjetoDao();
                returnValue.setUsuarioByIdconsumidor(usdao.Buscar(rs.getInt("idprestador")));
                returnValue.setUsuarioByIdprestador(usdao.Buscar(rs.getInt("idconsumidor")));
                returnValue.setObjeto(objdao.Buscar(rs.getInt("idlibro")));
                returnValue.setTiemposolicitado(rs.getInt("tiemposolicitado"));
                returnValue.setMedida(rs.getString("medida"));
                       
            }
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
        return returnValue;
    }
    public void Actualizar(Solicitar o) throws DataAccessLayerException {
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("update solicitar set  tiemposolicitado = ?, medida = ? where idsolicitud = ?");
            consulta.setInt(5, o.getTiemposolicitado());
            consulta.setString(6, o.getMedida());
            consulta.setInt(7, o.getIdsolicitud());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }

    public void Eliminar(Solicitar o) throws DataAccessLayerException {
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("delete from Prestamo where idsolicitud = ?");
            consulta.setInt(1, o.getIdsolicitud());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }

    public void Guardar(Solicitar o) throws DataAccessLayerException {
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("insert into Solicitar (idsolicitud,idprestador,idconsumidor,idlibro,fechasolicitud,tiemposolicitado,medida) "
                    + "values (?,?,?,?,?,?,?)");
            consulta.setInt(1, o.getIdsolicitud());
            consulta.setInt(2, o.getUsuarioByIdprestador().getIdusuario());
            consulta.setInt(3, o.getUsuarioByIdconsumidor().getIdusuario());
            consulta.setInt(4, o.getObjeto().getIdlibro());
            Date d= new Date(o.getFechasolicitud().getTime());
            consulta.setDate(5,d);
            consulta.setInt(6, o.getTiemposolicitado());
            consulta.setString(7, o.getMedida());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    
}
