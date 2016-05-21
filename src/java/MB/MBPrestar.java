/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import Controlador.dao.ObjetoDao;
import Controlador.dao.PrestarDao;
import Controlador.dao.UsuarioDao;
import Modelo.Objeto;
import Modelo.Prestamo;
import Modelo.Usuario;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Stein
 */
@ManagedBean
@RequestScoped
public class MBPrestar {

    @ManagedProperty(value="#{mBUsuario}")
    MBUsuario mBUsuario;
    private Objeto objeto;
    private String nombreObjeto;
    private String nombreUsuario;
    private Usuario usuarioByNombreprestador;
    private Usuario usuarioByNombreconsumidor;
    private Date fechaprestamo;
    private Integer calificacionprestador;
    private Integer calificaconsumidor;
    private Usuario us;
    
    public MBPrestar() {
    }
    
    public MBUsuario getmBUsuario() {
        return mBUsuario;
    }

    public void setmBUsuario(MBUsuario mBUsuario) {
        this.mBUsuario = mBUsuario;
    }


    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public Usuario getUsuarioByNombreprestador() {
        return usuarioByNombreprestador;
    }

    public void setUsuarioByNombreprestador(Usuario usuarioByNombreprestador) {
        this.usuarioByNombreprestador = usuarioByNombreprestador;
    }

    public Usuario getUsuarioByNombreconsumidor() {
        return usuarioByNombreconsumidor;
    }

    public void setUsuarioByNombreconsumidor(Usuario usuarioByNombreconsumidor) {
        this.usuarioByNombreconsumidor = usuarioByNombreconsumidor;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Integer getCalificacionprestador() {
        return calificacionprestador;
    }

    public void setCalificacionprestador(Integer calificacionprestador) {
        this.calificacionprestador = calificacionprestador;
    }

    public Integer getCalificaconsumidor() {
        return calificaconsumidor;
    }

    public void setCalificaconsumidor(Integer calificaconsumidor) {
        this.calificaconsumidor = calificaconsumidor;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public void solicitarPrestamo(){
        //System.out.println("aquiiiiiiiii " +nombreObjeto);
        ObjetoDao objd = new ObjetoDao();
        
        int i = nombreObjeto.hashCode()*13;
        Objeto obj = objd.Buscar(i);
        objeto = obj;
        System.out.println("Objetooooo "+objeto.getNombrelibro());
        Date date = new Date();
        Prestamo prst = new Prestamo();
        PrestarDao prstd = new PrestarDao();
        
        UsuarioDao usdao = new UsuarioDao();
        Usuario us1 = usdao.Buscar(mBUsuario.getIdUsuario()); //el de la sesion iniciada
        
        System.out.println("|----------|------------|--------|--"); 
        System.out.println("nombUs: "+ us1.getNombreusuario() );
        System.out.println("Usuario:"+objeto.getUsuario().getNombreusuario());
        System.out.println("NombLib"+objeto.getNombrelibro());
  
        prst.setUsuarioByIdconsumidor(us1);
        prst.setUsuarioByIdprestador(obj.getUsuario());
        prst.setFechaprestamo(date);
        
        prstd.Guardar(prst);
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
}
