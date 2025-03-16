package clinicaanimales;

/**
 *
 * @author Pablo
 */
public class Animal {
    String codAnimal;
    String nombre;
    String raza;

    public String getCodAnimal() {
        return codAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public String getFecha() {
        return fecha;
    }
    String fecha;

    public Animal(String codAnimal, String nombre, String raza, String fecha) {
        this.codAnimal = codAnimal;
        this.nombre = nombre;
        this.raza = raza;
        this.fecha = fecha;
    }
    public Animal(String linea) throws MiExcepcion{
        String[] trozos = linea.split(";");
        if(4 != trozos.length){
            throw new MiExcepcion("ERROR linea mal formada ");
        }
        else{
            this.codAnimal=trozos[0];
            this.nombre=trozos[1];
            this.raza=trozos[2];
            this.fecha=trozos[3];
        }
    }
    
    public String serialize(){
        String linea = codAnimal+";"+nombre+";"+raza+";"+fecha+"\n";
        return linea;
    }
    @Override
    public String toString() {
        return "Animal{" + "codAnimal=" + codAnimal + ", nombre=" + nombre + ", raza=" + raza + ", fecha=" + fecha + '}';
    }
    
}
