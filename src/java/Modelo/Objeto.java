package Modelo;

public class Objeto  implements java.io.Serializable {


     private int idlibro;
     private Usuario usuario;
     private String nombrelibro;
     private String autor;
     private Integer edicion;
     private Integer anio;
     private String genero;
     private String sinopsis;
     private Integer numpaginas;
     
    public Objeto() {
    }

	
    public Objeto(int idlibro, Usuario usuario, String nombrelibro) {
        this.idlibro = idlibro;
        this.usuario = usuario;
        this.nombrelibro = nombrelibro;
        
    }
    
   
    public int getIdlibro() {
        return this.idlibro;
    }
    
    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getNombrelibro() {
        return this.nombrelibro;
    }
    
    public void setNombrelibro(String nombrelibro) {
        this.nombrelibro = nombrelibro;
    }
    public String getAutor() {
        return this.autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Integer getEdicion() {
        return this.edicion;
    }
    
    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }
    public Integer getAnio() {
        return this.anio;
    }
    
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public String getGenero() {
        return this.genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getSinopsis() {
        return this.sinopsis;
    }
    
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public Integer getNumpaginas() {
        return this.numpaginas;
    }
    
    public void setNumpaginas(Integer numpaginas) {
        this.numpaginas = numpaginas;
    }
    
    @Override
    public int hashCode() {
        int num = (nombrelibro.hashCode() + 17);
        num = (num + usuario.hashCode() + 17 ) * 31;
        return num ; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        String libro = "id Libro = "+ idlibro;
        libro += "/n nombre Libro =" + nombrelibro;
        libro += "\n =" + autor;
        libro += "\n nombre Libro =" + edicion;
        libro += "\n nombre Libro =" + numpaginas;
        libro += "\n nombre Libro =" + sinopsis;
        libro += "\n usuario =" + usuario;
        
        return libro; //To change body of generated methods, choose Tools | Templates.
    }
    



}


