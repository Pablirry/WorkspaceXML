/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Logica.Alumno;
import Logica.Clase;
import Logica.Coche;
import Logica.MiExcepcion;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface Gestorficheros {
     public void leerAlumnos(String nombrefichero, List<Alumno> listaAlumnos) 
                                                            throws MiExcepcion;
    
     public void leerCoches(String nombrefichero, List<Coche> listaCoches) 
                                                            throws MiExcepcion;
     
     public void leerClases(String nombrefichero, List<Clase> listaClases,
                                 List<Alumno> listaAlumnos) throws MiExcepcion;
      
     public void escribirCoche(String nombrefichero, List<Coche> listaCoche) 
                                                            throws MiExcepcion;
     
     public void escribirClase(String nombrefichero, List<Clase> listaClases) 
                            throws MiExcepcion;
}
