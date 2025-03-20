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

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error al leer ficheros: " + e.getMessage());
        }
    }

    private void leerPrestamos(Document doc) {
        NodeList nodosPrestamos = doc.getElementsByTagName("prestamo");
        for (int i = 0; i < nodosPrestamos.getLength(); i++) {
            Node nPrestamo = nodosPrestamos.item(i);
            if (nPrestamo.getNodeType() == Node.ELEMENT_NODE) {
                Element ePrestamo = (Element) nPrestamo;
                try {
                    int id = Integer.parseInt(ePrestamo.getAttribute("id"));
                    int idUsuario = Integer.parseInt(getElement(ePrestamo, "idUsuario"));
                    int idLibro = Integer.parseInt(getElement(ePrestamo, "idLibro"));
                    String fechaInicio = getElement(ePrestamo, "fechaInicio");
                    String fechaFin = getElement(ePrestamo, "fechaFin");

                    Prestamo prestamo = new Prestamo(id, idUsuario, idLibro, fechaInicio, fechaFin);
                    prestamos.add(prestamo);
                } catch (Exception e) {
                    System.err.println("Error al leer prestamo: " + e.getMessage());
                }
            }
        }
    }

    private void leerUsuarios(Document doc) {
        NodeList nodosUsuarios = doc.getElementsByTagName("usuario");
        for (int i = 0; i < nodosUsuarios.getLength(); i++) {
            Node nUsuario = nodosUsuarios.item(i);
            if (nUsuario.getNodeType() == Node.ELEMENT_NODE) {
                Element eUsuario = (Element) nUsuario;
                try {
                    int id = Integer.parseInt(eUsuario.getAttribute("id"));
                    String nombre = getElement(eUsuario, "nombre");
                    String nacionalidad = getElement(eUsuario, "nacionalidad");

                    Usuario usuario = new Usuario(id, nombre, nacionalidad);
                    usuarios.add(usuario);
                } catch (Exception e) {
                    System.err.println("Error al leer usuario: " + e.getMessage());
                }
            }
        }
    }

    private void leerLibros(Document doc) {
        NodeList nodosLibros = doc.getElementsByTagName("libro");
        for (int i = 0; i < nodosLibros.getLength(); i++) {
            Node nLibro = nodosLibros.item(i);
            if (nLibro.getNodeType() == Node.ELEMENT_NODE) {
                Element eLibro = (Element) nLibro;
                try {
                    int id = Integer.parseInt(eLibro.getAttribute("id"));
                    String titulo = getElement(eLibro, "titulo");
                    String genero = getElement(eLibro, "genero");
                    int ano = Integer.parseInt(getElement(eLibro, "ano"));
                    String autor = getElement(eLibro, "autor");

                    Libro libro = new Libro(id, titulo, genero, ano, autor);
                    libros.add(libro);
                } catch (Exception e) {
                    System.err.println("Error al leer libro: " + e.getMessage());
                }
            }
        }
    }

    private String getElement(Element element, String tagName) throws Exception {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            throw new Exception("Elemento " + tagName + " no encontrado");
        }
        return nodeList.item(0).getTextContent();
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
                eLibro.setAttribute("id", String.valueOf(libro.getId()));

                Element eTitulo = doc.createElement("titulo");
                eTitulo.setTextContent(libro.getTitulo());

                Element eGenero = doc.createElement("genero");
                eGenero.setTextContent(libro.getGenero());

                Element eAno = doc.createElement("ano");
                eAno.setTextContent(String.valueOf(libro.getAno()));

                Element eAutor = doc.createElement("autor");
                eAutor.setTextContent(libro.getAutor());

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
                eUsuario.setAttribute("id", String.valueOf(usuario.getId()));

                Element eNombre = doc.createElement("nombre");
                eNombre.setTextContent(usuario.getNombre());

                Element eNacionalidad = doc.createElement("nacionalidad");
                eNacionalidad.setTextContent(usuario.getNacionalidad());

                eUsuario.appendChild(eNombre);
                eUsuario.appendChild(eNacionalidad);

                usuariosElement.appendChild(eUsuario);
            }
            raiz.appendChild(usuariosElement);

            Element prestamosElement = doc.createElement("prestamos");
            for (Prestamo prestamo : prestamos) {
                Element ePrestamo = doc.createElement("prestamo");
                ePrestamo.setAttribute("id", String.valueOf(prestamo.getId()));

                Element eIdLibro = doc.createElement("idLibro");
                eIdLibro.setTextContent(String.valueOf(prestamo.getIdLibro()));

                Element eIdUsuario = doc.createElement("idUsuario");
                eIdUsuario.setTextContent(String.valueOf(prestamo.getIdUsuario()));

                Element eFechaInicio = doc.createElement("fechaInicio");
                eFechaInicio.setTextContent(prestamo.getFechaInicio());

                Element eFechaFin = doc.createElement("fechaFin");
                eFechaFin.setTextContent(prestamo.getFechaFin());

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
        } catch (ParserConfigurationException | TransformerException e) {
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

    public void anadirLibro(Libro libro) throws Exception {
        if (existeLibro(libro.getId())) {
            throw new Exception("El libro con ID " + libro.getId() + " ya existe.");
        }
        libros.add(libro);
    }

    public void anadirUsuario(Usuario usuario) throws Exception {
        if (existeUsuario(usuario.getId())) {
            throw new Exception("El usuario con ID " + usuario.getId() + " ya existe.");
        }
        usuarios.add(usuario);
    }

    public void anadirPrestamo(Prestamo prestamo) throws Exception {
        if (existePrestamo(prestamo.getId())) {
            throw new Exception("El préstamo con ID " + prestamo.getId() + " ya existe.");
        }
        prestamos.add(prestamo);
    }

    public boolean existeLibro(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean existeUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean existePrestamo(int id) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void listarLibros() {
        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitulo() + ", Género: "
                    + libro.getGenero() + ", Año: " + libro.getAno() + ", Autor: " + libro.getAutor());
        }
    }

    public void listarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Nacionalidad: "
                    + usuario.getNacionalidad());
        }
    }

    public void listarPrestamos() {
        for (Prestamo prestamo : prestamos) {
            System.out.println("ID: " + prestamo.getId() + ", ID Libro: " + prestamo.getIdLibro() + ", ID Usuario: "
                    + prestamo.getIdUsuario() + ", Fecha Inicio: " + prestamo.getFechaInicio() + ", Fecha Fin: "
                    + prestamo.getFechaFin());
        }
    }

    public void listarPrestamosActivos() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Prestamo prestamo : prestamos) {
            LocalDate fechaFin = LocalDate.parse(prestamo.getFechaFin(), formater);
            if (fechaFin.isAfter(fechaActual)) {
                System.out.println("ID: " + prestamo.getId() + ", ID Libro: " + prestamo.getIdLibro() + ", ID Usuario: "
                        + prestamo.getIdUsuario() + ", Fecha Inicio: " + prestamo.getFechaInicio() + ", Fecha Fin: "
                        + prestamo.getFechaFin());
            }
        }
    }
}
