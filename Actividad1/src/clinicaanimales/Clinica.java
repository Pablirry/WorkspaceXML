package clinicaanimales;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 *
 * @author Pablo
 */
public class Clinica {
    List<Animal> listaAnimales;

    public Clinica() {
        listaAnimales = new LinkedList<Animal>();
    }

    public void añadirAnimal() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("cod Animal :");
        String codAnimal = teclado.nextLine();
        System.out.println("nombre : ");
        String nombre = teclado.nextLine();
        System.out.println("raza : ");
        String raza = teclado.nextLine();
        System.out.println("fecha nac ");
        String fechaNac = teclado.nextLine();

        Animal animal = new Animal(codAnimal, nombre, raza, fechaNac);
        insertarAnimal(animal);
    }

    public void leerFichero(String nombrefichero) throws MiExcepcion {
        try {
            FileReader fileFichero = new FileReader(nombrefichero);
            BufferedReader buffer = new BufferedReader(fileFichero);
            // leemos la cabecera
            String linea = buffer.readLine();
            // leemos primer registro de animales
            linea = buffer.readLine();
            while (null != linea) {
                Animal animal = new Animal(linea);
                insertarAnimal(animal);

                linea = buffer.readLine();
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            throw new MiExcepcion("Error fichero no encontrado");
        } catch (IOException ex) {
            throw new MiExcepcion("ERROR entrada salida ");
        }
    }

    public void leerFicheroXML(String nombrefichero) throws Exception {
        DocumentBuilderFactory dbf;
        dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(nombrefichero));

        doc.getDocumentElement().normalize();

        NodeList nodosAnimales = doc.getElementsByTagName("animal");
        for (int i = 0; i < nodosAnimales.getLength(); i++) {
            Node nAnimal = nodosAnimales.item(i);

            Element eAnimal = (Element) nAnimal;

            String codAnimal = eAnimal.getElementsByTagName("CodAnimal").item(0).getTextContent();
            String nombre = eAnimal.getElementsByTagName("Nombre").item(0).getTextContent();
            String raza = eAnimal.getElementsByTagName("Raza").item(0).getTextContent();
            String fechaNac = "";
            if (eAnimal.getElementsByTagName("FechaNac").getLength() == 1) {
                fechaNac = eAnimal.getElementsByTagName("FechaNac").item(0).getTextContent();
            }
            Animal a = new Animal(codAnimal, nombre, raza, fechaNac);
            listaAnimales.add(a);
        }
    }

    public void escribirFicheroXML(String nombrefichero) throws Exception {
        DocumentBuilderFactory dbf;
        dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element raiz = doc.createElement("animales");

        for (int i = 0; i < listaAnimales.size(); i++) {
            Animal a = listaAnimales.get(i);

            Element eAnimal = doc.createElement("animal");
            eAnimal.setAttribute("perro", "cierto");

            Element eCodAnimal = doc.createElement("CodAnimal");
            eCodAnimal.setTextContent(a.getCodAnimal());

            Element eNombre = doc.createElement("Nombre");
            eNombre.setTextContent(a.getNombre());

            Element eRaza = doc.createElement("Raza");
            eRaza.setTextContent(a.getRaza());

            eAnimal.appendChild(eCodAnimal);
            eAnimal.appendChild(eNombre);
            eAnimal.appendChild(eRaza);

            if (!a.getFecha().isEmpty()) {
                Element eFecha = doc.createElement("FechaNac");
                eFecha.setTextContent(a.getFecha());
                eAnimal.appendChild(eFecha);
            }

            raiz.appendChild(eAnimal);
        }

        doc.appendChild(raiz);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(nombrefichero));
        t.transform(source, result);
    }

    public void insertarAnimal(Animal animal) {
        listaAnimales.add(animal);
    }

    public void escribirFichero(String nombrefichero) throws MiExcepcion {
        try {
            FileWriter file = new FileWriter(nombrefichero);
            BufferedWriter buffer = new BufferedWriter(file);

            String cabecera = "CodAnimal;Nombre;Raza;FechaNac\n";
            buffer.write(cabecera);

            for (int i = 0; i < listaAnimales.size(); i++) {
                Animal a = listaAnimales.get(i);
                String linea = a.serialize();
                buffer.write(linea);
            }
            buffer.close();

        } catch (IOException ex) {
            throw new MiExcepcion("ERROR en escritura fichero ");
        }
    }

    public void listarAnimales() {
        System.out.printf("%-10s %-15s %-20s %-15s%n", "codAnimal", "nombre", "raza", "fechaNac");
        for (Animal animal : listaAnimales) {
            System.out.printf("%-10s %-15s %-20s %-15s%n", animal.getCodAnimal(), animal.getNombre(), animal.getRaza(),
                    animal.getFecha());
        }
    }

}
