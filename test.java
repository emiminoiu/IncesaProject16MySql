package test;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class test {

	
	
	public static void main(String[] args) {
	  
		Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventx?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            //String sql = "SELECT * FROM eventx.events";
          //  Statement statement = con.createStatement();
            //ResultSet resultSet = statement.executeQuery(sql);
            //ResultSetMetaData metaData = resultSet.getMetaData();
          
            con.createStatement()
            .execute("CREATE TABLE books(\n" +
                 " id integer primary key auto_increment,\n" +
                 " book_id varchar(25) not null unique,\n" +
                 " author varchar(50) not null,\n" +
                 " title varchar(250) null,\n" +
                 " genre varchar(25)  null,\n" +
                 " price varchar(250) null,\n" +
                 " publish_date varchar(250)  null,\n" +
                 " description varchar(250)  null\n" +
                 ")");
         
            	File fXmlFile = new File("C:\\Users\\Minoiu Emi\\eclipse-workspace\\books.txt");
            	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            	org.w3c.dom.Document doc = (org.w3c.dom.Document) dBuilder.parse(fXmlFile);
            			
            	//optional, but recommended
            	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            	((org.w3c.dom.Document) doc).getDocumentElement().normalize();

            	System.out.println("Root element :" + ((org.w3c.dom.Document) doc).getDocumentElement().getNodeName());
            			
            	NodeList nList = ((org.w3c.dom.Document) doc).getElementsByTagName("book");
            			
            	System.out.println("----------------------------");

            	for (int temp = 0; temp < nList.getLength(); temp++) {

            		Node nNode = nList.item(temp);
            				
            		System.out.println("\nCurrent Element :" + nNode.getNodeName());
            				
            		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            			Element eElement = (Element) nNode;
                        String book_id = eElement.getAttribute("id");
            			String author =   eElement.getElementsByTagName("author").item(0).getTextContent();
            			String title =  eElement.getElementsByTagName("title").item(0).getTextContent();
            			String genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
            			String price = eElement.getElementsByTagName("price").item(0).getTextContent();
            			String publish_date = eElement.getElementsByTagName("publish_date").item(0).getTextContent();
            			String description = eElement.getElementsByTagName("description").item(0).getTextContent();
            			System.out.println("Book id : " + eElement.getAttribute("id"));
            			System.out.println("Author : " + eElement.getElementsByTagName("author").item(0).getTextContent());
            			System.out.println("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
            			System.out.println("Genre : " + eElement.getElementsByTagName("genre").item(0).getTextContent());
            			System.out.println("Price : " + eElement.getElementsByTagName("price").item(0).getTextContent());
            			System.out.println("Publish_Date : " + eElement.getElementsByTagName("publish_date").item(0).getTextContent());
            			System.out.println("Description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
            		    String sql = "insert into `books`(`book_id`,`author`,`title`,`genre`,`price`,`publish_date`,`description`) values (" + book_id  + ",'" + author + "','" + title + "','" + genre + "','" + price + "','" + publish_date + "','" + description + "'" +")";           		  
            		    Statement statement = con.createStatement();
            	        statement.executeUpdate(sql);
            		}
            	}
               
              
    	    } catch (Exception e) {
            System.out.println(e);
    	    }
       
       
       	
	
	  
	}}

