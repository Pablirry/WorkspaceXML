package Ejemplo;

import java.io.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		
		leerDom();

	}

	private static void leerDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			File file = new File("Ejemplo1/coches.xml");
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

}
