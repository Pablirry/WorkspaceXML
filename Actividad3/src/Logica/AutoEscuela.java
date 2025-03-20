/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Persistencia.GestorficherosXML;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AutoEscuela {
    String ficheroAlumno;
    String ficheroCoche;
    String ficheroClases;
    
    List<Alumno> listaAlumnos;
    List<Coche> listaCoche;
    List<Clase> listaClases;
            

    public AutoEscuela(String ficheroAlumnos, String ficheroCoches, String ficheroClases) {
        this.ficheroAlumno=ficheroAlumnos;
        this.ficheroClases=ficheroClases;
        this.ficheroCoche=ficheroCoches;
        
        listaAlumnos = new LinkedList<Alumno>();
        listaCoche = new LinkedList<Coche>();
        listaClases=new LinkedList<Clase>();
    }

    public void leerficheros() {
        GestorficherosXML gestor = new GestorficherosXML();
        try {
            gestor.leerAlumnos(ficheroAlumno, listaAlumnos);
            gestor.leerCoches(ficheroCoche, listaCoche);
            gestor.leerClases(ficheroClases,listaClases,listaAlumnos);
            
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void escribirficheros() {
    	GestorficherosXML gestor = new GestorficherosXML();
        try {
            String ficheroDestino = ficheroAlumno+"2";
            gestor.leerAlumnos(ficheroDestino, listaAlumnos);
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String listarDatos() {
        String texto ="\n lista alumnos \n";
        for(int i=0;i<listaAlumnos.size();i++){
            texto +=listaAlumnos.get(i).toString();
        }
        texto +="\n lista coches \n";
        for(int i=0;i<listaCoche.size();i++){
            texto +=listaCoche.get(i).toString();
        }
        texto +="\n lista clases \n";
        for(int i=0;i<listaClases.size();i++){
            texto +=listaClases.get(i).toString();
        }
        return texto;
    }

    public boolean listarClasesPorAlumno(String dni) {
        return true;
    }

    public boolean listarClasesPorcoche(String matricula) {
       return true;
    }

    public void nuevaclase(String matricula, String dni, String dia, String horaIni, String horaFin) {
        if(buscarMatricula(matricula) == true && 
                buscarAlumno(dni) == true)
        {
           int codigoNuevo = buscarCodClase();
           Clase c = new Clase(codigoNuevo,matricula,dni,dia,horaIni,horaFin);
           listaClases.add(c);
        }
        else if (buscarMatricula(matricula) == false){
            System.out.println("ERROR matricula no encontrada");
        }
        else System.out.println("ERROR dni no encontrado");
    }

    private boolean buscarMatricula(String matricula) {
      boolean encontrado =false;
      for(int i=0;((i<listaCoche.size()) && (encontrado == false));i++){
          Coche c = listaCoche.get(i);
          if(c.getMatricula().compareTo(matricula)==0){
              encontrado=true;
          }
      }
      return encontrado;
    }

    private boolean buscarAlumno(String dni) {
       boolean encontrado =false;
      for(int i=0;((i<listaAlumnos.size()) && (encontrado == false));i++){
          Alumno a = listaAlumnos.get(i);
          if(a.getDni().compareTo(dni)==0){
              encontrado=true;
          }
      }
      return encontrado;      
    }

    private int buscarCodClase() {
      int cod =-1;
      if(listaClases.isEmpty()){
          cod=1;
      }
      else{
        int valorMayor=listaClases.get(0).getCodClase();
        // buscamos el mayor valor
        for(int i=1;i<listaClases.size();i++){
            Clase c = listaClases.get(i);
            if(c.getCodClase()>valorMayor){
                valorMayor = c.getCodClase();
            }

        }
        cod = valorMayor+1;
      }
      return cod;
      
    }
    
    
    
}
