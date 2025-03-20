/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.Alumno;
import Logica.Clase;
import Logica.Coche;
import Logica.MiExcepcion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Usuario
 */
public class GestosFicherosCSV  implements Gestorficheros{
    @Override
    public void leerAlumnos(String nombrefichero, List<Alumno> listaAlumnos) throws MiExcepcion{
        FileReader file;
        try {
            file = new FileReader(nombrefichero);
            BufferedReader buffer = new BufferedReader(file);
            
            // leer lacabecera
            String linea = buffer.readLine();
            if (linea.split(";").length!=7){
                throw new MiExcepcion("EEROR cabecera mal formada \n");
            }
            
            linea=buffer.readLine();
            while(linea!=null){
                Alumno a = new Alumno(linea);
                listaAlumnos.add(a);
                linea=buffer.readLine();
            }
            buffer.close();
        
        //
        } catch (FileNotFoundException ex) {
            throw new MiExcepcion("EEROR ficheron o encontrado \n");
        } catch (IOException ex) {
            throw new MiExcepcion("EEROR operaciones readline \n");
        }
    }
    
    @Override
     public void leerCoches(String nombrefichero, List<Coche> listaCoches) throws MiExcepcion{
         FileReader file;
        try {
            file = new FileReader(nombrefichero);
            BufferedReader buffer = new BufferedReader(file);
            
            // leer lacabecera
            String linea = buffer.readLine();
            linea=buffer.readLine();
            while(linea!=null){
                Coche c = new Coche(linea);
                listaCoches.add(c);
                linea=buffer.readLine();
            }
            buffer.close();
        
        //
        } catch (FileNotFoundException ex) {
            throw new MiExcepcion("EEROR ficheron o encontrado \n");
        } catch (IOException ex) {
            throw new MiExcepcion("EEROR operaciones readline \n");
        }
     }
  
    @Override
      public void leerClases(String nombrefichero, List<Clase> listaClases,
                                List<Alumno> listaAlumnos) throws MiExcepcion{
          FileReader file;
        try {
            file = new FileReader(nombrefichero);
            BufferedReader buffer = new BufferedReader(file);
            
            // leer lacabecera
            String linea = buffer.readLine();
            linea=buffer.readLine();
            while(linea!=null){
                Clase c = new Clase(linea);
                if(true == buscarAlumno(c.getDni(),listaAlumnos))
                listaClases.add(c);
                linea=buffer.readLine();
            }
            buffer.close();
        
        //
        } catch (FileNotFoundException ex) {
            throw new MiExcepcion("EEROR ficheron o encontrado \n");
        } catch (IOException ex) {
            throw new MiExcepcion("EEROR operaciones readline \n");
        }
      }
      
      
      public void escribirAlumnos(String nombrefichero, List<Alumno> listaAlumnos) throws MiExcepcion{
        try {
            FileWriter file = new FileWriter(nombrefichero);
            BufferedWriter buffer = new BufferedWriter(file);
            
            String cabecera ="dni;nombre;apellidos;direccion;telefono;fechaNac;numeroTarjeta\n";
            buffer.write(cabecera);
            
            for(int i=0;i<listaAlumnos.size();i++){
                Alumno a = listaAlumnos.get(i);
                String linea = a.serialice();
                buffer.write(linea);
            }
            buffer.close();
        } catch (IOException ex) {
            throw new MiExcepcion("EEROR escritura del fichero \n");
        }
      }
      
    @Override
     public void escribirCoche(String nombrefichero, List<Coche> listaCoche) throws MiExcepcion{
         try {
            FileWriter file = new FileWriter(nombrefichero);
            BufferedWriter buffer = new BufferedWriter(file);
            
            String cabecera ="matricula;modelo;marca;km\n";
            buffer.write(cabecera);
            
            for(int i=0;i<listaCoche.size();i++){
                Coche c = listaCoche.get(i);
                String linea = c.serialize();
                buffer.write(linea);
            }
            buffer.close();
        } catch (IOException ex) {
            throw new MiExcepcion("EEROR escritura del fichero \n");
        } 
     }
     
    @Override
     public void escribirClase(String nombrefichero, List<Clase> listaClases) throws MiExcepcion{
         try {
            FileWriter file = new FileWriter(nombrefichero);
            BufferedWriter buffer = new BufferedWriter(file);
            
            String cabecera ="CodClase;matricula;alumno;dia;horaInicio;HoraFin\n";
            buffer.write(cabecera);
            
            for(int i=0;i<listaClases.size();i++){
                Clase c = listaClases.get(i);
                String linea = c.serialize();
                buffer.write(linea);
            }
            buffer.close();
        } catch (IOException ex) {
            throw new MiExcepcion("EEROR escritura del fichero \n");
        } 
     
     
     }

    private boolean buscarAlumno(String dni, List<Alumno> listaAlumnos) {
        boolean encontrado = false;
        for(int i=0;i<listaAlumnos.size() && false == encontrado;i++){
            Alumno a = listaAlumnos.get(i);
            if(a.getDni().compareTo(dni) == 0){
                encontrado = true;
            }
        }
        return encontrado;
    }
}
