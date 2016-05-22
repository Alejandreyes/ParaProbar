/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

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
public class UsuarioDao extends AbstractDao{
    public Usuario Buscar(int idusuario) throws DataAccessLayerException{
       Usuario tmp= null;
       try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from usuario where idusuario = ?");
            consulta.setInt(1, idusuario);
            ResultSet rs = consulta.executeQuery();
            Usuario o = new Usuario();
            while (rs.next()){
               o.setIdusuario(rs.getInt("idusuario"));
               o.setNombreusuario(rs.getString("nombreusuario"));
               o.setContrasenia(rs.getString("contrasenia"));
               o.setNombre(rs.getString("nombre"));
               o.setApellidos(rs.getString("apellidos"));
               o.setCorreo(rs.getString("correo"));
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
    public void Actualizar(Usuario o) throws DataAccessLayerException{
       try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("update usuario set  contrasenia = ? , "
                    + "nombre = ?, apellidos = ? ,correo = ? where idusuario = ?");
            consulta.setString(1, o.getContrasenia());
            consulta.setString(2, o.getNombre());
            consulta.setString(3, o.getApellidos());
            consulta.setString(4, o.getCorreo());
            consulta.setInt(5, o.getIdusuario());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public void Eliminar(Usuario o) throws DataAccessLayerException{
        try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("delete from Usuario where idusuario = ?");
            consulta.setInt(1, o.getIdusuario());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public void Guardar(Usuario o) throws DataAccessLayerException{
         try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("insert into usuario (idusuario,nombreusuario,contrasenia,nombre,apellidos,correo) "
                    + "values (?,?,?,?,?,?)");
            consulta.setInt(1, o.getIdusuario());
            consulta.setString(2, o.getNombreusuario());
            consulta.setString(3, o.getContrasenia());
            consulta.setString(4, o.getNombre());
            consulta.setString(5, o.getApellidos());
            consulta.setString(6, o.getCorreo());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }finally {
            desconectar();
        }
    }
    public List<Usuario> obtenerTodos(){
        ArrayList<Usuario>  usuarios= new ArrayList();
       try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Objeto");
           ResultSet rs= consulta.executeQuery();
           while (rs.next()){
               Usuario o = new Usuario();
               o.setIdusuario(rs.getInt("idusuario"));
               o.setNombreusuario(rs.getString("nombreusuario"));
               o.setContrasenia(rs.getString("contrasenia"));
               o.setNombre(rs.getString("nombre"));
               o.setApellidos(rs.getString("apellidos"));
               o.setCorreo(rs.getString("correo"));
               usuarios.add(o);
            } 
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }
       finally {
            desconectar();
        }
        return usuarios;
    }
    public List findSome(String nom, String con) throws DataAccessLayerException {
        return findLogin(nom,con);
    }
    public List findLogin(String nom, String cont) {
        
        
        ArrayList<Usuario>  usuarios= new ArrayList();
       try {
            this.conectar();
            Connection con =this.getConexion();
            PreparedStatement consulta = con.prepareStatement("Select * from Usuario nombreusuario = ? and contrasenia = ? ");
            consulta.setString(1, nom);
            consulta.setString(2, cont);
            ResultSet rs= consulta.executeQuery();
           while (rs.next()){
               Usuario o = new Usuario();
               o.setIdusuario(rs.getInt("idusuario"));
               o.setNombreusuario(rs.getString("nombreusuario"));
               o.setContrasenia(rs.getString("contrasenia"));
               o.setNombre(rs.getString("nombre"));
               o.setApellidos(rs.getString("apellidos"));
               o.setCorreo(rs.getString("correo"));
               usuarios.add(o);
            } 
        } catch (SQLException ex) {
            throw new DataAccessLayerException (ex);
        }
       finally {
            desconectar();
        }
        return usuarios;
        
    }
    
}
