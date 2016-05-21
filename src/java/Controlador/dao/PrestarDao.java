/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

import Modelo.Objeto;
import Modelo.Prestamo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author stein
 */
public class PrestarDao extends AbstractDao{
    public Prestamo Buscar(String nombreLibro) throws DataAccessLayerException{
        Prestamo returnValue = null;
        session.getTransaction().commit();
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            returnValue = (Prestamo) session.createQuery("from "+"Prestar"+" where "+"id"+"= " + nombreLibro+" and  "  ).uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
    public void Actualizar(Prestamo o) throws DataAccessLayerException{
       super.update(o);
    }
    public void Eliminar(Prestamo o) throws DataAccessLayerException{
        super.delete(o);
    }
    public void Guardar(Prestamo o) throws DataAccessLayerException{
        super.save(o);
    }
    public List<Prestamo>obtenerTodos(){
        return super.findAll(Prestamo.class);
    }
    public  boolean disponible(Objeto obj){
        Boolean disponible = false;
        super.startOperation();
        try {
            Query d = session.createQuery("from "+"Prestamo"+" where "+"idlibro"+" = " +obj.getIdlibro()+"");
            disponible = (d.list().isEmpty());
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return disponible;
    }
    
}
