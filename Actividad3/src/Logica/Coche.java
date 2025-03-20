/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Coche {
    String matricula;
    String modelo;

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getKm() {
        return km;
    }
    String marca;
    int km;

    public Coche(String matricula, String modelo, String marca, int km) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.km = km;
    }

    public Coche(String linea) throws MiExcepcion{
        String [] trozos=linea.split(";");
        if(trozos.length!=4) throw new MiExcepcion("ERROR liena mal formada");
        matricula = trozos[0];
        modelo = trozos[1];
        marca=trozos[2];
        km = Integer.parseInt(trozos[3]);
        
    }
    @Override
    public String toString() {
        return "Coche{" + "matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", km=" + km + "]\n";
    }
    
    public String serialize() {
        return matricula + ";" + modelo + ";" + marca + ";" + km + "\n";
    }
    
    
}
