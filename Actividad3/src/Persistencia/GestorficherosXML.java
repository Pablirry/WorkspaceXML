package Persistencia;

import Logica.Alumno;
import Logica.Clase;
import Logica.Coche;
import Logica.MiExcepcion;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestorficherosXML implements Gestorficheros {

    @Override
    public void leerAlumnos(String nombrefichero, List<Alumno> listaAlumnos) throws MiExcepcion {

        File file = new File(nombrefichero);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String dni = element.getElementsByTagName("dni").item(0).getTextContent();
                String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                String apellidos = element.getElementsByTagName("apellidos").item(0).getTextContent();
                String direccion = element.getElementsByTagName("direccion").item(0).getTextContent();
                String telefono = element.getElementsByTagName("telefono").item(0).getTextContent();
                String fechaNac = element.getElementsByTagName("fechaNac").item(0).getTextContent();
                String numeroTarjeta = element.getElementsByTagName("numeroTarjeta").item(0).getTextContent();
                Alumno alumno = new Alumno(dni, nombre, apellidos, direccion, telefono, fechaNac, numeroTarjeta);
                listaAlumnos.add(alumno);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void leerCoches(String nombrefichero, List<Coche> listaCoches) throws MiExcepcion {

        File file = new File(nombrefichero);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("coche");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String matricula = element.getElementsByTagName("matricula").item(0).getTextContent();
                String modelo = element.getElementsByTagName("modelo").item(0).getTextContent();
                String marca = element.getElementsByTagName("marca").item(0).getTextContent();
                int km = Integer.parseInt(element.getElementsByTagName("km").item(0).getTextContent());
                Coche coche = new Coche(matricula, modelo, marca, km);
                listaCoches.add(coche);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void leerClases(String nombrefichero, List<Clase> listaClases, List<Alumno> listaAlumnos)
            throws MiExcepcion {

        File file = new File(nombrefichero);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("clase");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element eClase = (Element) nodeList.item(i);
                int codClase = Integer.parseInt(eClase.getElementsByTagName("codClase").item(0).getTextContent());
                String matricula = eClase.getElementsByTagName("matricula").item(0).getTextContent();
                String dniAlumno = eClase.getElementsByTagName("dniAlumno").item(0).getTextContent();
                String dia = eClase.getElementsByTagName("dia").item(0).getTextContent();
                String horaInicio = eClase.getElementsByTagName("horaInicio").item(0).getTextContent();
                String horaFin = eClase.getElementsByTagName("horaFin").item(0).getTextContent();
                Clase clase = new Clase(codClase, matricula, dniAlumno, dia, horaInicio, horaFin);
                listaClases.add(clase);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void escribirCoche(String nombrefichero, List<Coche> listaCoche) throws MiExcepcion {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element rootElement = doc.createElement("coches");
            doc.appendChild(rootElement);

            for (Coche coche : listaCoche) {
                Element cocheElement = doc.createElement("coche");

                Element matricula = doc.createElement("matricula");
                matricula.appendChild(doc.createTextNode(coche.getMatricula()));
                cocheElement.appendChild(matricula);

                Element modelo = doc.createElement("modelo");
                modelo.appendChild(doc.createTextNode(coche.getModelo()));
                cocheElement.appendChild(modelo);

                Element marca = doc.createElement("marca");
                marca.appendChild(doc.createTextNode(coche.getMarca()));
                cocheElement.appendChild(marca);

                Element km = doc.createElement("km");
                km.appendChild(doc.createTextNode(String.valueOf(coche.getKm())));
                cocheElement.appendChild(km);

                rootElement.appendChild(cocheElement);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombrefichero));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void escribirClase(String nombrefichero, List<Clase> listaClases) throws MiExcepcion {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element rootElement = doc.createElement("clases");
            doc.appendChild(rootElement);

            for (Clase clase : listaClases) {
                Element claseElement = doc.createElement("clase");

                Element codClase = doc.createElement("codClase");
                codClase.appendChild(doc.createTextNode(String.valueOf(clase.getCodClase())));
                claseElement.appendChild(codClase);

                Element matricula = doc.createElement("matricula");
                matricula.appendChild(doc.createTextNode(clase.getMatricula()));
                claseElement.appendChild(matricula);

                Element dniAlumno = doc.createElement("dniAlumno");
                dniAlumno.appendChild(doc.createTextNode(clase.getDni()));
                claseElement.appendChild(dniAlumno);

                Element dia = doc.createElement("dia");
                dia.appendChild(doc.createTextNode(clase.getDia()));
                claseElement.appendChild(dia);

                Element horaInicio = doc.createElement("horaInicio");
                horaInicio.appendChild(doc.createTextNode(clase.getHoraInicio()));
                claseElement.appendChild(horaInicio);

                Element horaFin = doc.createElement("horaFin");
                horaFin.appendChild(doc.createTextNode(clase.getHoraFin()));
                claseElement.appendChild(horaFin);

                rootElement.appendChild(claseElement);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombrefichero));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}