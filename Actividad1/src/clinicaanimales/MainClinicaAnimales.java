/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clinicaanimales;

import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class MainClinicaAnimales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String rutafichero = System.getProperty("user.dir")+"\\src\\ficheros\\animales.xml";
        String rutaficheroDestino = System.getProperty("user.dir")+"\\src\\ficheros\\animales.xml"; // Save back to the same file
        System.out.println("ruta : "+rutafichero);

        Clinica clinica = new Clinica();
        try {
            clinica.leerFicheroXML(rutafichero);
            clinica.listarAnimales();
            clinica.añadirAnimal();
            clinica.listarAnimales();
            clinica.escribirFicheroXML(rutaficheroDestino); // Save changes back to the original file
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(MainClinicaAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
