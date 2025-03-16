package Logic;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Model.*;

public class Biblioteca {

    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    public void LeerFicheroXML(String rutaBiblioteca) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(rutaBiblioteca);

            doc.getDocumentElement().normalize();

            leerLibros(doc);
            leerUsuarios(doc);
            leerPrestamos(doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerPrestamos(Document doc) {
        NodeList nodosPrestamos = doc.getElementsByTagName("prestamo");
        for (int i = 0; i < nodosPrestamos.getLength(); i++) {
            Node nPrestamo = nodosPrestamos.item(i);

            Element ePrestamo = (Element) nPrestamo;

            String id = ePrestamo.getElementsByTagName("id").item(0).getTextContent();
            String idUsuario = ePrestamo.getElementsByTagName("idUsuario").item(0).getTextContent();
            String idLibro = ePrestamo.getElementsByTagName("idLibro").item(0).getTextContent();
            String fechaInicio = ePrestamo.getElementsByTagName("fechaInicio").item(0).getTextContent();
            String fechaFin = ePrestamo.getElementsByTagName("fechaFin").item(0).getTextContent();

            Prestamo prestamo = new Prestamo(id, idUsuario, idLibro, fechaInicio, fechaFin);
            prestamos.add(prestamo);
        }
    }

    private void leerUsuarios(Document doc) {
        NodeList nodosUsuarios = doc.getElementsByTagName("usuario");
        for (int i = 0; i < nodosUsuarios.getLength(); i++) {
            Node nUsuario = nodosUsuarios.item(i);

            Element eUsuario = (Element) nUsuario;

            String id = eUsuario.getElementsByTagName("id").item(0).getTextContent();
            String nombre = eUsuario.getElementsByTagName("nombre").item(0).getTextContent();
            String nacionalidad = eUsuario.getElementsByTagName("nacionalidad").item(0).getTextContent();

            Usuario usuario = new Usuario(id, nombre, nacionalidad);
            usuarios.add(usuario);
        }
    }

    private void leerLibros(Document doc) {
        NodeList nodosLibros = doc.getElementsByTagName("libro");
        for (int i = 0; i < nodosLibros.getLength(); i++) {
            Node nLibro = nodosLibros.item(i);

            Element eLibro = (Element) nLibro;

            String id = eLibro.getElementsByTagName("id").item(0).getTextContent();
            String titulo = eLibro.getElementsByTagName("titulo").item(0).getTextContent();
            String genero = eLibro.getElementsByTagName("genero").item(0).getTextContent();
            String ano = eLibro.getElementsByTagName("ano").item(0).getTextContent();
            String autor = eLibro.getElementsByTagName("autor").item(0).getTextContent();

            Libro libro = new Libro(id, titulo, genero, ano, autor);
            libros.add(libro);
        }
    }

    public void escribirFicheroXML(String rutaBiblioteca) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element raiz = doc.createElement("biblioteca");

            Element librosElement = doc.createElement("libros");
            for (Libro libro : libros) {
                Element eLibro = doc.createElement("libro");

                Element eId = doc.createElement("id");
                eId.setTextContent(libro.getId());

                Element eTitulo = doc.createElement("titulo");
                eTitulo.setTextContent(libro.getTitulo());

                Element eGenero = doc.createElement("genero");
                eGenero.setTextContent(libro.getGenero());

                Element eAno = doc.createElement("ano");
                eAno.setTextContent(libro.getAno());

                Element eAutor = doc.createElement("autor");
                eAutor.setTextContent(libro.getAutor());

                eLibro.appendChild(eId);
                eLibro.appendChild(eTitulo);
                eLibro.appendChild(eGenero);
                eLibro.appendChild(eAno);
                eLibro.appendChild(eAutor);

                librosElement.appendChild(eLibro);
            }
            raiz.appendChild(librosElement);

            Element usuariosElement = doc.createElement("usuarios");
            for (Usuario usuario : usuarios) {
                Element eUsuario = doc.createElement("usuario");

                Element eId = doc.createElement("id");
                eId.setTextContent(usuario.getId());

                Element eNombre = doc.createElement("nombre");
                eNombre.setTextContent(usuario.getNombre());

                Element eNacionalidad = doc.createElement("nacionalidad");
                eNacionalidad.setTextContent(usuario.getNacionalidad());

                eUsuario.appendChild(eId);
                eUsuario.appendChild(eNombre);
                eUsuario.appendChild(eNacionalidad);

                usuariosElement.appendChild(eUsuario);
            }
            raiz.appendChild(usuariosElement);

            Element prestamosElement = doc.createElement("prestamos");
            for (Prestamo prestamo : prestamos) {
                Element ePrestamo = doc.createElement("prestamo");

                Element eId = doc.createElement("id");
                eId.setTextContent(prestamo.getId());

                Element eIdLibro = doc.createElement("idLibro");
                eIdLibro.setTextContent(prestamo.getIdLibro());

                Element eIdUsuario = doc.createElement("idUsuario");
                eIdUsuario.setTextContent(prestamo.getIdUsuario());

                Element eFechaInicio = doc.createElement("fechaInicio");
                eFechaInicio.setTextContent(prestamo.getFechaInicio());

                Element eFechaFin = doc.createElement("fechaFin");
                eFechaFin.setTextContent(prestamo.getFechaFin());

                ePrestamo.appendChild(eId);
                ePrestamo.appendChild(eIdLibro);
                ePrestamo.appendChild(eIdUsuario);
                ePrestamo.appendChild(eFechaInicio);
                ePrestamo.appendChild(eFechaFin);

                prestamosElement.appendChild(ePrestamo);
            }
            raiz.appendChild(prestamosElement);

            doc.appendChild(raiz);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(rutaBiblioteca);
            t.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void anadirLibro(Libro libro) {
        libros.add(libro);
    }

    public void anadirUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void anadirPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public void listarLibros() {
        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitulo() + ", Género: " + libro.getGenero() + ", Año: " + libro.getAno() + ", Autor: " + libro.getAutor());
        }
    }

    public void listarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Nacionalidad: " + usuario.getNacionalidad());
        }
    }

    public void listarPrestamos() {
        for (Prestamo prestamo : prestamos) {
            System.out.println("ID: " + prestamo.getId() + ", ID Libro: " + prestamo.getIdLibro() + ", ID Usuario: " + prestamo.getIdUsuario() + ", Fecha Inicio: " + prestamo.getFechaInicio() + ", Fecha Fin: " + prestamo.getFechaFin());
        }
    }

    public void listarPrestamosActivos() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Prestamo prestamo : prestamos) {
            LocalDate fechaFin = LocalDate.parse(prestamo.getFechaFin(), formater);
            if (fechaFin.isAfter(fechaActual)) {
                System.out.println("ID: " + prestamo.getId() + ", ID Libro: " + prestamo.getIdLibro() + ", ID Usuario: " + prestamo.getIdUsuario() + ", Fecha Inicio: " + prestamo.getFechaInicio() + ", Fecha Fin: " + prestamo.getFechaFin());
            }
        }
    }
}