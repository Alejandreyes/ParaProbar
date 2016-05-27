/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.dao;

import MB.MBPrestar;
import Modelo.Solicitar;
import java.util.List;

/**
 *
 * @author rodrigorojo
 */
public class Prueba {
    static SolicitudDao pd = new SolicitudDao();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
        MBPrestar p = new MBPrestar();
        p.rechazarPrestamo(-1864380845);
        
       
        
    }
    
}
