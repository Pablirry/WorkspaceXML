/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Clase {

    public int getCodClase() {
        return codClase;
    }
    int codClase;
    String matricula;
    String dni;
    String dia;
    String horaInicio;
    String horaFin;

    public String getMatricula() {
        return matricula;
    }

    public String getDni() {
        return dni;
    }

    public String getDia() {
        return dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public Clase(int codClase, String matricula, String dni, String dia, String horaInicio, String horaFin) {
        this.codClase = codClase;
        this.matricula = matricula;
        this.dni = dni;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    

       public Clase(String linea) throws MiExcepcion{
        String [] trozos=linea.split(";");
        if(trozos.length!=6) throw new MiExcepcion("ERROR liena mal formada");
        this.codClase = Integer.parseInt(trozos[0]);
        this.matricula = trozos[1];
        this.dni = trozos[2];
        this.dia =trozos[3];
        this.horaInicio = trozos[4];
        this.horaFin = trozos[5];
        
    }

    @Override
    public String toString() {
        return "Clase{" + "codClase=" + codClase + ", matricula=" + matricula + ", dni=" + dni + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + "]\n";        
    }
    
    public String serialize() {
        return codClase + ";" + matricula + ";" + dni + ";" + horaInicio + ";" + horaFin + "\n";        
    }
    
    
}
