/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import Controlador.Filtro;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Stein
 */
@ManagedBean
@RequestScoped
public class MBSubirImagenObjeto {

    private String nombreLibro;
    private String ruta;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public MBSubirImagenObjeto() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        nombreLibro = (String) session.getAttribute("nombreLibro");
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    private String preparaRuta() {
        String rutaAux = null;
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = (String) servletContext.getRealPath("") + File.separatorChar;
            realPath = realPath + "resources" + File.separatorChar + "Imagenes" + File.separatorChar + "PortadasLibros" + File.separatorChar;
            File fichero = new File(realPath);
            String[] listaArchivos = fichero.list(new Filtro(nombreLibro));
            if (listaArchivos[0] != null) {
                rutaAux = listaArchivos[0];
            }
        } catch (Exception e) {

        }
        return rutaAux;
    }

    public void handleFileUpload(FileUploadEvent event) {
        int punto = event.getFile().getFileName().lastIndexOf(".");
        String foto = nombreLibro + event.getFile().getFileName().substring(punto);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("") + File.separatorChar;
        int build = realPath.indexOf("build");
        realPath = realPath.substring(0, build) + "web" + File.separatorChar;
        realPath = realPath + "resources" + File.separatorChar + "Imagenes" + File.separatorChar + "PortadasLibros" + File.separatorChar + foto;
        try {
            copyFile(realPath, event.getFile().getInputstream());
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("No se pudo cambiar la foto de perfil");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        FacesMessage message = new FacesMessage("Foto de perfil cambiada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
