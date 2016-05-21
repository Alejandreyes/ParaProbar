/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import Controlador.dao.DataAccessLayerException;
import Controlador.dao.UsuarioDao;
import Modelo.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Stein
 */
@ManagedBean
@SessionScoped
public class MBUsuario {
    private int idUsuario;
     private String nombreusuario;
     private String contrasenia;
     private String nombre;
     private String apellidos;
     private String correo;
    public MBUsuario() {
    }

    public String getNombreusuario() {
        return nombreusuario;
    }
    
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
      public String valida() {
          try{
              UsuarioDao user = new UsuarioDao();
        //List<Usuario> listUser = user.findAll();
        List<Usuario> listUser = user.findSome(nombreusuario, contrasenia);
        Usuario tmp;

        for (Usuario tmpUser : listUser) {
            if (nombreusuario != null && nombreusuario.equals(tmpUser.getNombreusuario()) && contrasenia != null && contrasenia.equals(tmpUser.getContrasenia())) {
                tmp = tmpUser;
                this.setIdUsuario(tmp.getIdusuario());
                this.setNombre(tmp.getNombre());
                this.setApellidos(tmp.getApellidos());
                this.setCorreo(tmp.getCorreo());
                return "index?faces-redirect=true";
            } 

        }
              
              
          }catch (DataAccessLayerException e){
              FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro invalido", "Nombre de usuario o contraseña incorrectos"));
            
          }
        
        return "SignInIH";
    }

    public String salir() {
        nombreusuario = null;
        contrasenia = null;
        nombre = null;
        apellidos = null;
        correo = null;
        return "index?faces-redirect=true";
    }
    public String signUp(){
        try{
             
            Usuario temp = new Usuario(idUsuario,nombreusuario, contrasenia, nombre, apellidos, correo);
            idUsuario = temp.hashCode();
            temp.setIdusuario(idUsuario);
            UsuarioDao dao= new UsuarioDao();
        dao.Guardar(temp);
        return "index?faces-redirect=true";
            
        }catch(DataAccessLayerException e){
            FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro invalido", "Nombre de usuario ya exite en la base"));
            
        }
        return "SignUpIH";
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    
}
