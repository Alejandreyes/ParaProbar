/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

import Modelo.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author stein
 */
public class UsuarioDao extends AbstractDao{
    public Usuario Buscar(int idusuario) throws DataAccessLayerException{
        return (Usuario)super.find(Usuario.class, idusuario,"idusuario") ;
    }
    public void Actualizar(Usuario o) throws DataAccessLayerException{
       super.update(o);
    }
    public void Eliminar(Usuario o) throws DataAccessLayerException{
        super.delete(o);
    }
    public void Guardar(Usuario o) throws DataAccessLayerException{
        super.save(o);
    }
    public List<Usuario> obtenerTodos(){
        return super.findAll(Usuario.class);
    }
    public List findSome(String nom, String con) throws DataAccessLayerException {
        return findLogin(Usuario.class,nom,con);
    }
    public List findLogin(Class clazz, String nom, String con) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName() + " where nombreusuario = :code and contrasenia = :pass");
            query.setParameter("code", nom);
            query.setParameter("pass", con);
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }
    
}
