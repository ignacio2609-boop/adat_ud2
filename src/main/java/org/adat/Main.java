package org.adat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        AlumnoDAO brand = new AlumnoDAO(conn);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("./src/main/resources/alumnos.xml"));
            Element root = doc.getDocumentElement();
            NodeList alumnos = root.getElementsByTagName("alumno");
            for (int i = 0; i < alumnos.getLength(); i++) {
                brand.insert(readXML(i));
                System.out.println("Alumno " + i + " insertado correctamente");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static String readXML(int i) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("./src/main/resources/alumnos.xml"));
            Element root = doc.getDocumentElement();
            NodeList alumnos = root.getElementsByTagName("alumno");
            Element alumno = (Element) alumnos.item(i);
            String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
            String apellido = alumno.getElementsByTagName("apellido").item(0).getTextContent();
            String curso = alumno.getElementsByTagName("curso").item(0).getTextContent();
            String dni = alumno.getElementsByTagName("dni").item(0).getTextContent();

            return "('" + nombre + "', '" + apellido + "', '" + curso + "', '" + dni + "')";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}