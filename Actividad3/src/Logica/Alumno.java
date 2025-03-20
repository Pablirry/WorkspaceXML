/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Alumno {

    String dni;
    String nombre;
    String apellidos;
    String direccion;
    String telefono;
    String fechaNac;
    String numeroTarjeta;

    public Alumno(String dni, String nombre, String apellidos, String direccion,
            String telefono, String fechaNac, String numeroTarjeta) throws MiExcepcion {
        if (dniCorrecto(dni) == false) {
            throw new MiExcepcion("Error dni mal formado ");
        }
        if (telefonoCorrecto(telefono) == false) {
            throw new MiExcepcion("Error telefono no correcto");
        }
        if (numeroTarjetacorrecto(numeroTarjeta) == false) {
            throw new MiExcepcion("error en el numero de tarjeta");
        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.numeroTarjeta = numeroTarjeta;
    }

    public Alumno(String linea) throws MiExcepcion{
        String [] trozos = linea.split(";");
        // trozos[0] = dni 
        // trozos[4] = telefono
        // trozos[6] = numero de tarjeta
        
        if(trozos.length != 7){
            System.out.println("linea "+linea.length()+":"+linea);
            throw new MiExcepcion("error linea mal formada");
        }
        String dni = trozos[0];
        String telefonoString = trozos[4];
        String numTarjeta = trozos[6];
        if (dniCorrecto(dni) == false) {
            throw new MiExcepcion("Error dni mal formado ");
        }
        if (telefonoCorrecto(telefonoString) == false) {
            throw new MiExcepcion("Error telefono no correcto");
        }
        if (numeroTarjetacorrecto(numTarjeta) == false) {
            throw new MiExcepcion("error en el numero de tarjeta");
        }   
        
        this.dni = dni;
        this.nombre = trozos[1];
        this.apellidos = trozos[2];
        this.direccion = trozos[3];
        this.telefono = telefono;
        this.fechaNac = trozos[5];
        this.numeroTarjeta = numTarjeta;

    }
    public boolean dniCorrecto(String dni) {
        //dni = ddddddddX
        if (dni.length() != 9) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            char caracter = dni.charAt(i);
            if (Character.isDigit(caracter) == false) {
                return false;
            }
        }
        return true;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    @Override
    public String toString() {
        return "Alumno{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + ", telefono=" + telefono + ", fechaNac=" + fechaNac + ", numeroTarjeta=" + numeroTarjeta + "}\n";
    }

    public boolean telefonoCorrecto(String stringTelefono) {
        //ddddddddd
        if (stringTelefono.length() != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            char caracter = stringTelefono.charAt(i);
            if (Character.isDigit(caracter) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean numeroTarjetacorrecto(String numero) {
        //dddd dddd dddd dddd 
        String [] trozos = numero.split(" ");
        if(trozos.length != 4) return false;
        for(int i=0;i<trozos.length;i++){
            if(trozos[i].length()!=4) return false;
            for(int j = 0;j<trozos[i].length();j++){
                char caracter = trozos[i].charAt(j);
                if (Character.isDigit(caracter) == false) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String serialice(){
        return dni+";"+nombre+";"+apellidos+";"+direccion+";"+telefono+";"
                                               +fechaNac+";"+numeroTarjeta+"\n";
    }
}
