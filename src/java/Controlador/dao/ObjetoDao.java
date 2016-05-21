/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

import Modelo.Objeto;
import java.util.List;
import org.hibernate.Transaction;

/**
 *
 * @author stein
 */
public class ObjetoDao extends AbstractDao{
    public Objeto Buscar(int idLibro) throws DataAccessLayerException{
        return (Objeto)super.find(Objeto.class, idLibro,"idlibro");
    }
    public void Actualizar(Objeto o) throws DataAccessLayerException{
       super.update(o);
    }
    public void Eliminar(Objeto o) throws DataAccessLayerException{
        super.delete(o);
    }
    public void Guardar(Objeto o) throws DataAccessLayerException{
        super.save(o);
    }
    public List<Objeto> obtenerTodos(){
        return super.findAll(Objeto.class);
    }
    public Objeto Buscar(String nombreLibro) throws DataAccessLayerException{
        return (Objeto)super.find(Objeto.class, nombreLibro,"idlibro");
    }
    
}
