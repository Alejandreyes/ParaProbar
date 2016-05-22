/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

import Modelo.Objeto;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stein
 */
public class ObjetoDao extends AbstractDao{
    public Objeto Buscar(int idLibro) throws DataAccessLayerException{
        Objeto tmp= null;
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Objeto where idlibro = ?");
            consulta.setInt(1, idLibro);
            ResultSet rs = consulta.executeQuery();
            Objeto o = new Objeto();
            
            while (rs.next()){
               o.setIdlibro(rs.getInt("idlibro"));
               o.setNombrelibro(rs.getString("nombrelibro"));
               o.setAnio(rs.getInt("anio"));
               o.setAutor(rs.getString("autor"));
               o.setEdicion(rs.getInt("edicion"));
               o.setGenero(rs.getString("genero"));
               o.setSinopsis(rs.getString("sinopsis"));
               o.setNumpaginas(rs.getInt("numpaginas"));
               UsuarioDao dao = new UsuarioDao();
               Usuario us =dao.Buscar(rs.getInt("idusuario"));
               o.setUsuario(us);
               tmp=o;
            }
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
        return tmp;
    }
    public void Actualizar(Objeto o) throws DataAccessLayerException{
       try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("update objeto set nombrelibro = ? , autor = ? , "
                    + "edicion = ?, anio = ? ,genero = ?, sinopsis = ?, numpaginas = ? where idlibro = ?");
            consulta.setString(1, o.getNombrelibro());
            consulta.setString(2, o.getAutor());
            consulta.setInt(3, o.getEdicion());
            consulta.setInt(4, o.getAnio());
            consulta.setString(5, o.getGenero());
            consulta.setString(6, o.getSinopsis());
            consulta.setInt(7, o.getNumpaginas());
            consulta.setInt(8, o.getIdlibro());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public void Eliminar(Objeto o) throws DataAccessLayerException{
         try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("delete from Objeto where idlibro = ?");
            consulta.setInt(1, o.getIdlibro());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public void Guardar(Objeto o) throws DataAccessLayerException{
         try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("insert into Objeto (idlibro,nombrelibro,autor,edicion,anio,genero,sinopsis,numpaginas,idusuario) "
                    + "values (?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, o.getIdlibro());
            consulta.setString(2, o.getNombrelibro());
            consulta.setString(3, o.getAutor());
            consulta.setInt(4, o.getEdicion());
            consulta.setInt(5, o.getAnio());
            consulta.setString(6, o.getGenero());
            consulta.setString(7, o.getSinopsis());
            consulta.setInt(8, o.getNumpaginas());
            consulta.setInt(9, o.getUsuario().getIdusuario());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public List<Objeto> obtenerTodos(){
       ArrayList<Objeto> objeto = new ArrayList();
       try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Objeto");
           ResultSet rs= consulta.executeQuery();
           while (rs.next()){
               Objeto o = new Objeto();
               o.setIdlibro(rs.getInt("idlibro"));
               o.setNombrelibro(rs.getString("nombrelibro"));
               o.setAnio(rs.getInt("anio"));
               o.setAutor(rs.getString("autor"));
               o.setEdicion(rs.getInt("edicion"));
               o.setGenero(rs.getString("genero"));
               o.setSinopsis(rs.getString("sinopsis"));
               o.setNumpaginas(rs.getInt("numpaginas"));
               UsuarioDao dao = new UsuarioDao();
               Usuario us =dao.Buscar(rs.getInt("idusuario"));
               o.setUsuario(us);
               objeto.add(o);
            } 
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }
       finally {
            desconectar();
        }
        return objeto;
    }
    public Objeto Buscar(String nombreLibro) throws DataAccessLayerException{
        Objeto tmp= null;
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Objeto where nombrelibro = ?");
            consulta.setString(1, nombreLibro);
            ResultSet rs = consulta.executeQuery();
            Objeto o = new Objeto();
            while (rs.next()){
               o.setIdlibro(rs.getInt("idlibro"));
               o.setNombrelibro(rs.getString("nombrelibro"));
               o.setAnio(rs.getInt("anio"));
               o.setAutor(rs.getString("autor"));
               o.setEdicion(rs.getInt("edicion"));
               o.setGenero(rs.getString("genero"));
               o.setSinopsis(rs.getString("sinopsis"));
               o.setNumpaginas(rs.getInt("numpaginas"));
               UsuarioDao dao = new UsuarioDao();
               Usuario us =dao.Buscar(rs.getInt("idusuario"));
               o.setUsuario(us);
               tmp=o;
            }
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }
        finally {
            desconectar();
        }
        return tmp;
    }
}
