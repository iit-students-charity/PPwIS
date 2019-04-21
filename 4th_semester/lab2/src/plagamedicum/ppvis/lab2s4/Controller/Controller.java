package plagamedicum.ppvis.lab2s4.Controller;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import plagamedicum.ppvis.lab2s4.model.Model;
import plagamedicum.ppvis.lab2s4.model.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public ObservableList<Student> getStudentList(){
        return model.getStudentList();
    }

    public int getExamNumber(){
        return model.getExamNumber();
    }

    public void newDoc(int examNumber, int entitiesNumber){
        this.model = new Model(examNumber, entitiesNumber);
    }

    /*public ArrayList<Student> openDoc(){


    }*/

    public void saveDoc(File file) {
        Element  root;
        Document doc;
        DocumentBuilderFactory docBuilderFactory;
        DocumentBuilder docBuilder;
        TransformerFactory transformerFactory;
        Transformer transformer;
        DOMSource source;
        StreamResult streamResult;

        try{
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            root = doc.createElement("student");
            doc.appendChild(root);

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);
        } catch (Exception  exception){
            exception.printStackTrace();
            return;
        }
    }
}
