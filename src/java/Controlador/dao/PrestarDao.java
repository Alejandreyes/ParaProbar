/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

import Modelo.Objeto;
import Modelo.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stein
 */
public class PrestarDao extends AbstractDao {

    public Prestamo Buscar(int idPrestamo) throws DataAccessLayerException {
        Prestamo returnValue = null;
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Prestamo where idprestamo = ?");
            consulta.setInt(1, idPrestamo);
            ResultSet rs = consulta.executeQuery();
            
            if (rs.next()){
                returnValue.setIdprestamo(idPrestamo);
                UsuarioDao usdao= new UsuarioDao();
                ObjetoDao objdao= new ObjetoDao();
                returnValue.setUsuarioByIdconsumidor(usdao.Buscar(rs.getInt("idprestador")));
                returnValue.setUsuarioByIdprestador(usdao.Buscar(rs.getInt("idconsumidor")));
                returnValue.setObjeto(objdao.Buscar(rs.getInt("idlibro")));
                returnValue.setCalificacionprestador(rs.getInt("calificacionprestador"));
                returnValue.setCalificaconsumidor(rs.getInt("calificaconsumidor"));
                returnValue.setOpinionsobreprestador(rs.getString("opinionsobreprestador"));
                returnValue.setOpinionsobreconsumidor(rs.getString("opinionsobreconsumidor"));
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
    public void Actualizar(Prestamo o) throws DataAccessLayerException {
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("update usuario set  calificacionprestador = ? , "
                    + "calificaconsumidor = ?, opinionsobreprestador = ? ,opinionsobreconsumidor = ?,tiemposolicitado = ?, medida = ? where idprestamo = ?");
            consulta.setInt(1, o.getCalificacionprestador());
            consulta.setInt(2, o.getCalificaconsumidor());
            consulta.setString(3, o.getOpinionsobreprestador());
            consulta.setString(4, o.getOpinionsobreconsumidor());
            consulta.setInt(5, o.getTiemposolicitado());
            consulta.setString(6, o.getMedida());
            consulta.setInt(7, o.getIdprestamo());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }

    public void Eliminar(Prestamo o) throws DataAccessLayerException {
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("delete from Prestamo where idprestamo = ?");
            consulta.setInt(1, o.getIdprestamo());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }

    public void Guardar(Prestamo o) throws DataAccessLayerException {
        try {
            this.conectar();
            Connection con =this.getConexion();
    //        PreparedStatement consulta = con.prepareStatement("insert into Prestamo (idprestamo,idprestador,idconsumidor,idlibro,fechaprestamo,tiemposolicitado,medida) "
    //                + "values (?,?,?,?,?,?,?)");
            PreparedStatement consulta = con.prepareStatement("insert into Prestamo (idprestamo,idprestador,idconsumidor,idlibro,fechaprestamo) "
                    + "values (?,?,?,?,?)");
            consulta.setInt(1, o.getIdprestamo());
            consulta.setInt(2, o.getUsuarioByIdprestador().getIdusuario());
            consulta.setInt(3, o.getUsuarioByIdconsumidor().getIdusuario());
            consulta.setInt(4, o.getObjeto().getIdlibro());
            Date d= new Date(o.getFechaprestamo().getTime());
            consulta.setDate(5,d);
            //consulta.setInt(6, o.getTiemposolicitado());
            //consulta.setString(7, o.getMedida());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public boolean disponible(Objeto obj) {
        try {
            this.conectar();
            Connection con = this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Prestamo"+ " where " + "idlibro" + " = " + obj.getIdlibro() + "");
            ResultSet rs = consulta.executeQuery();
            ArrayList e = new ArrayList();
            while(rs.next()) {
                e.add(new Object());
            }
            
            if (e.isEmpty()){
                return true;
            }else {
                return false;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(PrestarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }
        return false;
    }

}
