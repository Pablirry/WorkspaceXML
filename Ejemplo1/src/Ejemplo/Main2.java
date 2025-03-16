package Ejemplo;

import java.io.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main2 {

	public static void main(String[] args) {
		
		leerDom();

	}

	private static void leerDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			File file = new File("coches.xml");
			Document doc = db.parse(file);

			doc.getDocumentElement().normalize();

			NodeList coches = doc.getElementsByTagName("coche");

			for (int i = 0; i < coches.getLength(); i++) {
				
				Node nodoCoche = coches.item(i);
				
				Element eCoche = (Element) nodoCoche;
				
				String id = eCoche.getAttribute("id");
				System.out.println("Coche: " +id);
				
				Element eMarca = (Element) eCoche.getElementsByTagName("marca").item(0);
				System.out.println("Marca: " +eMarca.getTextContent());
				
				Element eModelo = (Element) eCoche.getElementsByTagName("modelo").item(0);
				System.out.println("Modelo: " + eModelo.getTextContent());
				
				Element eCilindrada = (Element) eCoche.getElementsByTagName("cilindrada").item(0);
				System.out.println("Cilindrada: " +eCilindrada.getTextContent());
				System.out.println();
				
				
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
