package Ejemplo;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {

		leerDom();
		escribirDom();

	}

	private static void leerDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			File file = new File("coches.xml");
			Document doc = db.parse(file);

			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("coche");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;

					String id = e.getAttribute("id");
					String marca = e.getElementsByTagName("marca").item(0).getTextContent();
					String modelo = e.getElementsByTagName("modelo").item(0).getTextContent();
					String cilindrada = e.getElementsByTagName("cilindrada").item(0).getTextContent();

					System.out.println("Coche " + id);
					System.out.println("Marca: " + marca);
					System.out.println("Modelo: " + modelo);
					System.out.println("Cilindrada: " + cilindrada);
					System.out.println();
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void escribirDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("concesionario");
			doc.appendChild(root);

			agregarCoche(doc, root, "5", "seat", "ibiza", "1.4");
			agregarCoche(doc, root, "6", "seat", "leon", "1.6");
			agregarCoche(doc, root, "7", "seat", "ateca", "2.0");

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("nuevo_coches.xml"));

			t.transform(source, result);
			System.out.println("Fichero creado");

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void agregarCoche(Document doc, Element root, String id, String marca, String modelo,
			String cilindrada) {

		Element coche = doc.createElement("coche");
		coche.setAttribute("id", id);

		Element marcaElement = doc.createElement("marca");
		marcaElement.appendChild(doc.createTextNode(marca));
		coche.appendChild(marcaElement);

		Element modeloElement = doc.createElement("modelo");
		modeloElement.appendChild(doc.createTextNode(modelo));
		coche.appendChild(modeloElement);

		Element cilindradaElement = doc.createElement("cilindrada");
		cilindradaElement.appendChild(doc.createTextNode(cilindrada));
		coche.appendChild(cilindradaElement);

		root.appendChild(coche);

	}
}
