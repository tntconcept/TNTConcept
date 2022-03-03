/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.xml;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.autentia.tnt.manager.report.ReportManager;


public class UtilitiesXML {
    
	private  static Log log = LogFactory.getLog(UtilitiesXML.class);
    /**
     * Este metodo busca un fichero de tipo XML en el classpath crea un objeto 
     * de tipo org.w3c.dom.Document.
     * @param fichero: El nombre del fichero a procesar.
     * @return
     * @throws Exception
     */
    public static Document file2Document(String fichero) throws Exception {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();      
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      URL urlfichero = loader.getResource(fichero);
      Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(urlfichero.openStream()));
      return XMLDoc;
    }
    
    
    /**
     * Este metodo convierte un objeto de tipo org.w3c.dom.NodeList a List
     * @param nodes
     * @return
     * @throws Exception
     */
    public static List nodes2String (NodeList nodes) throws Exception {
    	List<String> nodeText = new ArrayList<String>();
        for(int i=0;i<nodes.getLength();i++) {
            Node node = (Node)nodes.item(i);
            nodeText.add(normalizeName(getTexto(node)));
        }
        return nodeText;
    }


    
    /**
     * Este metodo convierte un objeto de tipo org.w3c.dom.NodeList a List
     * Este metodo solo saca el atributo especificado con nombre
     * @param nodo
     * @return
     * @throws Exception
     */
    public static List printAttribute (String nombre,NodeList nodes) throws Exception {
        List<String> nodeText = new ArrayList<String>();
        for(int i=0;i<nodes.getLength();i++) {
            Node node = (Node)nodes.item(i);
            nodeText.add(normalizeName(giveAttributeNode(nombre,node)));
        }
        return nodeText;
    }

    /**
     * Devuelve el texto de un nodo: <tag>TEXTO</tag>
     * @param n
     * @return
     */
    public static String getTexto (Node n) {
        NodeList nl = n.getChildNodes();
        Node act = null;
        
        for (int i=0;i<nl.getLength();i++) {
            act = nl.item(i);
            if (act == null) 
            	return null;
            if ((act.getNodeType() == Node.CDATA_SECTION_NODE)||(act.getNodeType() == Node.TEXT_NODE)) 
            	return act.getNodeValue();
        }
        return "";
      }
    
    /**
     * Devuelve el valor del atributo "nombre" de un nodo
     * @param nombre
     * @param nodo
     * @return
     */
    public static String giveAttributeNode(String name, Node node) {
        NamedNodeMap map = node.getAttributes();
        String value = null;
        if(map!=null) {
            Node nodoAt = map.getNamedItem(name);
            if(nodoAt!=null)
                value = nodoAt.getNodeValue();
        }
        return value;
    }
    
    /**
     * Este metodo normaliza un texto pasado por parametro
     * @param nombre
     * @return
     */
    public static String normalizeName(String name) {
        return name.replace('.',' ');
    }
    
    
    /**
     * Esta funcion retorna en un List todos los ficheros de la carpeta report
     * @return
     */
    public static List filesFromFolder(String path) {
    	File[] filesList = null;
        List list = new ArrayList();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        File f = null;
		try {
			f = new File(loader.getResource(path).toURI());
		} catch (URISyntaxException e) {
			log.error("Error en filesFromFolder", e);
		}
        if(f!=null && f.isDirectory()) {
    		filesList = f.listFiles();
    		for(File file : filesList) {
				int i = file.getAbsolutePath().lastIndexOf(".");
				String format = file.getAbsolutePath().substring(i+1);
    			if(file.isFile() && (format.equals("jrxml"))) {
    				list.add(path+File.separator+file.getName());
    			} 
    		}
    	}
    	return list;
    }
    /**
     * Esta funcion recoge un path y devuelve el nombre del fichero referido sin extension
     * @return
     */
    public static String cleanReport(String path) {
    	String pathCleaned = "";
    	
    	pathCleaned = path.substring(path.indexOf("Informe"));
    	pathCleaned = pathCleaned.replaceFirst(".jrxml","");
    	return pathCleaned;
    }
}
