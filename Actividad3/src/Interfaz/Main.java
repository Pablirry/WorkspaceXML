
package Interfaz;

import Logica.AutoEscuela;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {
    AutoEscuela escuela;
    Scanner teclado = new Scanner(System.in);
    
    Main(){
        String rutaficheros = System.getProperty("user.dir")+"\\src\\Ficheros\\";
        String ficheroAlumnos = rutaficheros+"alumnos.xml";
        String ficheroCoches = rutaficheros+"coches.xml";
        String ficheroClases = rutaficheros+"clases.xml";
        escuela = new AutoEscuela(ficheroAlumnos,ficheroCoches,ficheroClases);
    }
    
    void opcionMenu(){
        int opcion = -1;
        while(0 != opcion){
            System.out.println("1.- Cargar ficheros");
            System.out.println("2.- Escribir ficheros");
            System.out.println("3.- Nueva clase");
            System.out.println("4.- Listar clases por Alumno");
            System.out.println("5.- Listar clases por Coche");
            System.out.println("0.- SALIR ");
            System.out.print("opcion :");
            opcion = teclado.nextInt();
            procesarOpcion(opcion);            
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Main mainGestor = new Main();
        mainGestor.opcionMenu();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                escuela.leerficheros();
                System.out.println("\n\n");
                System.out.println(escuela.listarDatos());
                break;
            case 2:
                escuela.escribirficheros();
                break;  
            case 3:
                nuevaClase();
                System.out.println("\n\n");
                System.out.println(escuela.listarDatos());
                break;       
            case 4:
                System.out.println("Introduce dni");
                String dni = teclado.nextLine();
                System.out.println(escuela.listarClasesPorAlumno(dni));
                break;                
            case 5:
                System.out.println("Introduce matricula");
                String matricula = teclado.nextLine();
                System.out.println(escuela.listarClasesPorcoche(matricula));
                break;                   
            default:
                throw new AssertionError();
        }
    }

    private void nuevaClase() {
        System.out.println("Matricula : ");
        String matricula = teclado.next();
        System.out.println("Dni : ");
        String dni = teclado.next();
        System.out.println("Dia ");
        String dia = teclado.next();
        System.out.println("Hora inicio ");
        String horaIni = teclado.next();
        System.out.println("Hora fin ");
        String horaFin = teclado.next();
        escuela.nuevaclase(matricula,dni,dia,horaIni,horaFin);
    }
    
}
