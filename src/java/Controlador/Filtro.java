package Controlador;
import java.io.*;

public class Filtro implements FilenameFilter{
    private String id;
    public Filtro(String id){
        this.id=id;
    }
    public boolean accept(File dir, String name){
        return name.startsWith(id);
    }
}